/*
 * Copyright (c) 2024 by Oli B.
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
 * (c)reated 12.04.24 by oboehm
 */

package gdv.xport.tools;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit-Tests fuer {@link SatzartenScanner}.
 */
class SatzartenScannerTest {

    private final SatzartenScanner scanner = new SatzartenScanner();

    @Test
    void getURI() {
        URI uri = scanner.getURI();
        assertNotNull(uri);
    }

}