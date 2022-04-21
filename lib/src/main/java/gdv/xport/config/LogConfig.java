/*
 * Copyright (c) 2017 by Oliver Boehm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 17.06.17 by oliver (ob@oasd.de)
 */
package gdv.xport.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.jdbc.JDBCDriver;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * In der Klasse LogConfig sind zusaetzliche Angaben zur Log-Konfiguration
 * abgelegt.
 */
public final class LogConfig {

    private static final Logger LOG;
    private static LogConfig instance;
    private final URI dbURI;

    static {
        JDBCDriver driver = new JDBCDriver();
        try {
            instance = new LogConfig();
        } catch (ConfigException ex) {
            LogManager.getLogger().fatal("Kann keine LogConfig-Instanz anlegen:", ex);
        }
        LOG = LogManager.getLogger(LogConfig.class);
        LOG.debug("{} ist registriert.", driver);
    }

    /**
     * Als Default-Configuration wird eine Inmemory-DB verwendet.   1
     */
    public LogConfig() {
        this(readDatabaseURL());
    }

    /**
     * Hierueber wird die URL fuer die Datenbank eingestellt, auf die dann
     * der JDBCAppender aus Log4J2 losgelassen wird. Gleichzeitig wird diese
     * Instanz fuer {@link #getConnection()} gespeichert. Dies ist zwar etwas
     * unschoen, weil es dadurch theoretisch zu Race-Conditions kommen kann,
     * aber normalerweise wird due URI nur einmal am Anfang eingestellt und
     * damit einmal instanziiert.
     *
     * @param uri z.B. "jdbc:hsqldb:mem:logdb" oder
     *            "jdbc:postgresql://192.168.99.100:3277/test?user=test&amp;password=test"
     */
    public LogConfig(URI uri) {
        this.dbURI = uri;
        createLogTable(uri);
        instance = this;
    }

    /**
     * Hierueber wird die URL fuer die Datenbank eingestellt und der angebene
     * <i>username</i> und <i>password</i> an die URL angehaengt.
     *
     * @param uri      DB-URI, z.B. "jdbc:postgresql://192.168.99.100:3277/test"
     * @param username DB-User, z.B. "test"
     * @param password DB-Passwort, z.B. "geheim"
     * @see #LogConfig(URI) 
     */
    public LogConfig(URI uri, String username, String password) {
        this(URI.create(uri + "?user=" + encode(username) + "&password=" + encode(password)));
    }

    private static String encode(String parameter) {
        try {
            return URLEncoder.encode(parameter, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LOG.warn("Cannot encode '{}' in UTF-8:", parameter, ex);
            return parameter;
        }
    }

    private static URI readDatabaseURL() {
        String dbURL = System.getenv("DATABASE_URL");
        if (dbURL != null) {
            return URI.create(dbURL);
        }
        return URI.create(System.getProperty("DATABASE_URL","jdbc:hsqldb:mem:logdb"));
    }

    /**
     * Diese Klasse liefert die letzte Instanz, die angelegt wurde. Darueber
     * laesst sich z.B. die passende JDBC-URL zu statischen
     * {@link #getConnection()}-Methode abfragen.
     *
     * @return z.B. "jdbc:hsqldb:mem:logdb"
     */
    public static LogConfig getLastInstance() {
        return instance;
    }

    /**
     * Zur Abfrage der JDBC-URL.
     *
     * @return z.B. "jdbc:hsqldb:mem:logdb"
     */
    public URI getDbURI() {
        return this.dbURI;
    }

    /**
     * Liefert eine DB-Connection fuer den JDBCAppender aus Log4J.
     * Falls die Log-Tabelle, auf die in log4j2.xml verwiesen wird, nicht
     * existiert, wird sie samt Spalten angelegt.
     * <p>
     * Anmerkung: Wegen des JDBCAppenders aus Log4J2 muss diese Methode
     * statisch sein. Dazu wird die letzte Instanz der LogConfig-Klasse
     * herangezogen, die ueber den Konstruktor abgespeichert wird.
     * </p>
     *
     * @return eine DB-Connection
     * @throws SQLException bei Problemen mit der Datenbank
     */
    public static Connection getConnection() throws SQLException {
        return instance.getDbConnection();
    }

    /**
     * Liefert eine DB-Connection fuer den JDBCAppender aus Log4J.
     * Falls die Log-Tabelle, auf die in log4j2.xml verwiesen wird, nicht
     * existiert, wird sie samt Spalten angelegt.
     * <p>
     * Anmerkung: im Gegensatz zu {@link #getConnection()} ist diese Methode
     * <i>nicht</i> statisch.
     * </p>
     *
     * @return eine DB-Connection
     * @throws SQLException bei Problemen mit der Datenbank
     */
    public Connection getDbConnection() throws SQLException {
        Connection connection = getConnection(dbURI);
        connection.setAutoCommit(true);
        return connection;
    }

    private static Connection getConnection(URI uri) throws SQLException {
        String scheme = uri.getScheme();
        if (uri.getScheme().startsWith("jdbc")) {
            return DriverManager.getConnection(uri.toString());
        }
        String[] userinfos = uri.getUserInfo().split(":");
        String path = uri.getPath();
        if (scheme.startsWith("postgres")) {
            scheme = "postgresql";
            path += "?sslmode=require";
        }
        String dbUrl = "jdbc:" + scheme + "://" + uri.getHost() + ':' + uri.getPort() + path;
        LOG.debug("Connect to '{}'.", dbUrl);
        return DriverManager.getConnection(dbUrl, userinfos[0], userinfos[1]);
    }

    private static void createLogTable(URI jdbcURL) {
        try (Connection connection = getConnection(jdbcURL);
                Statement stmt = connection.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS logbook (event_date TIMESTAMP, level CHAR(5), logger VARCHAR (255), " +
                            "message VARCHAR (65535), throwable VARCHAR (65535))");
        } catch (SQLException sex) {
            throw new ConfigException("cannot create logbook", sex);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + dbURI + ")";
    }

}
