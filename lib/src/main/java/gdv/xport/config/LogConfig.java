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

import org.apache.logging.log4j.*;

import java.net.*;
import java.sql.*;

/**
 * In der Klasse LogConfig sind zusaetzliche Angaben zur Log-Konfiguration
 * abgelegt.
 */
public final class LogConfig {

    private static final Logger LOG = LogManager.getLogger(LogConfig.class);
    private static LogConfig instance = new LogConfig();
    private final URI dbURI;

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
     * @param uri z.B. "jdbc:hsqldb:mem:logdb"
     */
    public LogConfig(URI uri) {
        this.dbURI = uri;
        createLogTable(uri);
        instance = this;
        LOG.debug("LogConfig is created with '{}'.", uri);
    }

    private static URI readDatabaseURL() {
        String dbURL = System.getenv("DATABASE_URL");
        if (dbURL != null) {
            LOG.info("Read DATABASE_URL='{}' from environment.", dbURL);
            return URI.create(dbURL);
        }
        return URI.create(System.getProperty("DATABASE_URL","jdbc:hsqldb:mem:logdb"));
    }

    /**
     * Zur Abrage der JDBC-URL.
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
        Connection connection = getConnection(instance.getDbURI());
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

    private static void closeConnetion() {
        try {
            Connection connection = getConnection();
            connection.commit();
            connection.close();
        } catch (SQLException sex) {
            LOG.info("Cannot close connection ({}).", sex.getMessage());
            LOG.debug("Details:", sex);
        }
    }

    private static void createLogTable(URI jdbcURL) {
        try (Connection connection = getConnection(jdbcURL);
                Statement stmt = connection.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS logbook (event_date TIMESTAMP, level CHAR(5), logger VARCHAR (255), " +
                            "message VARCHAR (65535), throwable VARCHAR (65535))");
            LOG.debug("Table 'logbook' is created (update count = {}).", stmt.getUpdateCount());
        } catch (SQLException sex) {
            throw new ConfigException("cannot create logbook", sex);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + dbURI + ")";
    }

}
