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
 * (c)reated 16.02.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * In AbstractControllerIT sind einige Gemeinsamkeiter der verschiedenen
 * ControllerIT-Klassen zusammengefasst.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerIT {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testSetUp() {
        assertNotNull(mockMvc);
    }

    /**
     * Baut die URL zusammen und ruft den Service als GET-Request auf.
     *
     * @param path       Context-Pfad der URL
     * @param mediaTypes Content-Types
     * @return Antwort des abgesendeten Requests
     */
    protected String getResponseStringFor(String path, MediaType... mediaTypes) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .headers(createHeaders(mediaTypes)))
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    protected String postResponseStringFor(String path, String text, MediaType... mediaTypes) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .content(text)
                        .headers(createHeaders(mediaTypes)))
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    private static HttpHeaders createHeaders(MediaType[] mediaTypes) {
        HttpHeaders headers = new HttpHeaders();
        if (mediaTypes.length > 0) {
            headers.setAccept(Arrays.asList(mediaTypes));
        }
        return headers;
    }

}
