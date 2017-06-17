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

import java.net.URI;
import java.sql.*;

/**
 * In der Klasse LogConfig sind zusaetzliche Angaben zur Log-Konfiguration
 * abgelegt.
 */
public final class LogConfig {

    private static final Logger LOG = LogManager.getLogger(LogConfig.class);
    private static LogConfig instance = new LogConfig();
    private final URI jdbcURI;

    /**
     * Als Default-Configuration wird eine Inmemory-DB verwendet.   1
     */
    public LogConfig() {
        this("jdbc:hsqldb:mem:logdb");
    }

    /**
     * Hierueber wird die URL fuer die Datenbank eingestellt, auf die dann
     * der JDBCAppender aus Log4J2 losgelassen wird. Gleichzeitig wird diese
     * Instanz fuer {@link #getConnection()} gespeichert. Dies ist zwar etwas
     * unschoen, weil es dadurch theoretisch zu Race-Conditions kommen kann,
     * aber normalerweise wird due URI nur einmal am Anfang eingestellt und
     * damit einmal instanziiert.
     *
     * @param jdbcURI z.B. "jdbc:hsqldb:mem:logdb"
     */
    public LogConfig(String jdbcURI) {
        this.jdbcURI = URI.create(jdbcURI);
        instance = this;
    }

    /**
     * Zur Abrage der JDBC-URL.
     *
     * @return z.B. "jdbc:hsqldb:mem:logdb"
     */
    public URI getJdbcURI() {
        return this.jdbcURI;
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
     */
    public static Connection getConnection() throws SQLException {
        String jdbcURL = instance.getJdbcURI().toString();
        boolean ok = JDBCDriver.driverInstance.acceptsURL(jdbcURL);
        LOG.debug("'{}' is {}accepted as JDBC URL.", jdbcURL, ok ? "" : "not ");
        Connection connection = DriverManager.getConnection(jdbcURL);
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS logbook (event_date TIMESTAMP, level CHAR(5), logger VARCHAR (255)," +
                    " message VARCHAR (255), throwable VARCHAR (100))");
        }
        return connection;
    }

}
