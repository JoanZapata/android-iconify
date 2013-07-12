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
        test("A{icon_ok}A", "A" + icon_ok.character + "A");
    }

    @Test
    public void test_replaceIcons_noIcons() {
        test("A A", "A A");
    }

    @Test
    public void test_replaceIcons_manyIcons() {
        test("A{icon_ok}A{icon_camera}A", "A" + icon_ok.character + "A" + icon_camera.character + "A");
    }

    @Test
    public void test_replaceIcons_withDash() {
        test("A{icon-ok}A{icon-camera}A", "A" + icon_ok.character + "A" + icon_camera.character + "A");
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
