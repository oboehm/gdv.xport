package gdv.xport.srv.web;

import org.junit.Test;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/*
 * Copyright (c) 2022 by Oli B.
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
 * (c)reated 01.01.22 by oboehm
 */
public class ErrorModelTest {

    @Test
    public void testAsModelList() {
        Exception ex = new IllegalArgumentException("hoppla");
        List<Model> modelList = ErrorModel.asModelList(ex);
        assertFalse(modelList.isEmpty());
        Model model = modelList.get(0);
        assertEquals("hoppla", model.getAttribute("message"));
        assertEquals(ex, model.getAttribute("causes"));
    }

}