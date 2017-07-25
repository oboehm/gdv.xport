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
import org.junit.Test;

import java.sql.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link LogConfig}.
 */
public final class LogConfigTest {

    private static final Logger LOG = LogManager.getLogger(LogConfigTest.class);
    private final LogConfig logConfig = new LogConfig();

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
     * Momentan dient der Test nur dazu, um das Verhalten der Anwendung
     * herauszufinden, wenn eine Postgres-DB im Spiel ist. Waehrend dieser
     * Phase wird dieser Test auch fehlschlagen.
     *
     * @throws SQLException, wenn die Verbindungsdaten nicht stimmen
     */
    @Test
    public void getGetPostgresConnection() throws SQLException {
        LogConfig psqlConfig = new LogConfig("jdbc:postgresql://localhost/logbook?user=logger&password=geheim");
        LOG.info("{} was successful craeted.", psqlConfig);
        readLogbook();
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

}
