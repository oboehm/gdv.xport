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
import org.junit.*;

import java.sql.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link LogConfig}. Standardmaessig wird eine In-Memory-DB
 * (HSQL) zum Testen verwendet. Moechte man z.B. eine MySQL oder PostgreSQL zum
 * Testen verwenden, kann man dies ueber die Environment-Variable DATABASE_URL
 * (DATABASE_URL=jdbc:postgresql://localhost/logbook?user=logger&amp;password=geheim)
 * einstellen.
 */
public final class LogConfigTest {

    private static final Logger LOG = LogManager.getLogger(LogConfigTest.class);

    /**
     * Test-Methode fuer {@link LogConfig#getConnection()}.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void testGetConnection() throws SQLException {
        try (Connection connection = LogConfig.getConnection();
             Statement stmt = connection.createStatement()) {
            assertThat(connection.isClosed(), is(Boolean.FALSE));
            LOG.info("Got connection {}.", connection);
        }
    }

    /**
     * Hier wird getestet, ob tatsaechlich etwas in die logbook-Tabelle
     * geschrieben wurde.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void testReadLogbook() throws SQLException {
        LOG.info("Reading logbook...");
        readLogbook();
    }

    private void readLogbook() throws SQLException {
        int n = 0;
        try (Connection connection = LogConfig.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM logbook");
            while (rs.next()) {
                n++;
                Timestamp ts = rs.getTimestamp("event_date");
                String level = rs.getString("level");
                String msg = rs.getString("message");
                LOG.debug("{}. entry: {} {} {}", n, ts, level, msg);
            }
        }
        assertThat(n, greaterThan(0));
        LOG.info("{} entries read from logbook.", n);
    }

    /**
     * Hier raeumen wir nach dem Test die Test-Log-Eintraege wieder auf.
     *
     * @throws SQLException bei JDBC-Problemen
     */
    @AfterClass
    public static void cleanLogBook() throws SQLException {
        try (Connection connection = LogConfig.getConnection();
                Statement stmt = connection.createStatement()) {
            int n = stmt.executeUpdate("DELETE FROM logbook WHERE logger like 'gdv.xport.config.LogConfig%'");
            LOG.info("{} entries were removed from logbook.", n);
        }
    }

}
