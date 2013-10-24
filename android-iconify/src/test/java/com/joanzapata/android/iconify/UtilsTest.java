/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * It uses FontAwesome font, licensed under OFL 1.1, which is compatible
 * with this library's license.
 *
 *     http://scripts.sil.org/cms/scripts/render_download.php?format=file&media_id=OFL_plaintext&filename=OFL.txt
 */
package com.joanzapata.android.iconify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.joanzapata.android.iconify.Iconify.IconValue.*;
import static org.junit.Assert.assertEquals;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class UtilsTest {

    @Test
    public void test_replaceIcons() {
        test("A{fa_adjust}A", "A" + fa_adjust.character + "A");
    }

    @Test
    public void test_replaceIcons_noIcons() {
        test("A A", "A A");
    }

    @Test
    public void test_replaceIcons_manyIcons() {
        test("A{fa_adjust}A{fa_adn}A", "A" + fa_adjust.character + "A" + fa_adn.character + "A");
    }

    @Test
    public void test_replaceIcons_withDash() {
        test("A{fa-adjust}A{fa-adn}A", "A" + fa_adjust.character + "A" + fa_adn.character + "A");
    }

    @Test
    public void test_replaceIcons_wrong() {
        test("A{icon-okA{icon-camera}A", "A{icon-okA{icon-camera}A");
    }

    @Test
    public void test_replaceIcons_empty() {
        test("A{}A", "A{}A");
    }

    private void test(String in, String out) {
        assertEquals(out, Utils.replaceIcons(new StringBuilder(in)).toString());
    }

}
