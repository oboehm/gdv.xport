/*
 * Copyright (c) 2018 by Oliver Boehm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 10.09.2018 by oboehm (ob@oasd.de)
 */
package gdv.xport.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.testcontainers.containers.PostgreSQLContainer;

import java.net.URI;
import java.sql.*;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit-Tests fuer {@link LogConfig}-Klasse. Zum Starten wird hier ein
 * Docker-Container mit einer Postgres-DB hochgefahren. Da das Hochfahren
 * des Docker-Containers etwas laenger dauert (ca. 5 - 10 Sek.), wurde dieser
 * Test auf 'Ignore' gesetzt, da er gegenueber {@link LogConfigTest} (noch)
 * keine neuen Erkenntnisse bringt.
 *
 * @author oboehm
 */
@Ignore // weil's zu lange dauert (s.o.)
public class LogConfigIT {

    private static final Logger LOG = LogManager.getLogger(LogConfigIT.class);
    private static LogConfig lastLogConfig = LogConfig.getLastInstance();
    private static LogConfig logConfig;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer().withUsername("sa").withPassword("");
    
    @BeforeClass
    public static void setUpLogConfig() {
        LOG.info("Setting up logConfig...");
        URI jdbcURI = URI.create(postgreSQLContainer.getJdbcUrl());
        logConfig = new LogConfig(jdbcURI, "sa", "");
        LOG.info("Setting up logConfig {} successful finished.", logConfig);
    }
    
    /**
     * Als Testen verwenden wir die DB-Connection, um einen Eintrag ins
     * Logbook zu schreiben und zu pruefen.
     * 
     * @throws SQLException bei SQL-Fehlern
     */
    @Test
    public void testWriteToLogbook() throws SQLException {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        try (Connection c = LogConfig.getConnection(); PreparedStatement stmt = c
                .prepareStatement("INSERT INTO logbook (event_date, level, logger, message) VALUES(?, ?, ?, ?)")) {
            stmt.setTimestamp(1, now);
            stmt.setString(2, "TEST");
            stmt.setString(3, "test.logger");
            stmt.setString(4, "hello world!");
            int updated = stmt.executeUpdate();
            assertEquals(1, updated);
        }
        int count = readCountOf("level = 'TEST'");
        assertThat(count, greaterThan(0));
    }

    private int readCountOf(String condition) throws SQLException {
        try (Connection c = logConfig.getDbConnection(); 
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM logbook WHERE " + condition)) {
            assertTrue(rs.next());
            return rs.getInt(1);
        }
    }

    /**
     * Hierueber setzen wir die Log-Konfiguration wieder auf den Stand vor dem
     * Test zurueck. Ansonsten kann es passieren, dass die Log-Konfiguration
     * noch auf die DB des gerade heruntergefahrenen Docker-Containers geht.
     */
    @AfterClass
    public static void resetLogConfig() {
        logConfig = new LogConfig(lastLogConfig.getDbURI());
    }

}
