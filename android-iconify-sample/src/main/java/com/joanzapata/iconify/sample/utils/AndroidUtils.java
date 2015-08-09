package com.joanzapata.iconify.sample.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public final class AndroidUtils {

    // Prevent instantiation
    private AndroidUtils() {}

    /** Returns the available screensize, including status bar and navigation bar */
    public static Size getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point out = new Point();
        display.getSize(out);
        return new Size(out.x, out.y);
    }

    public static class Size {
        public final int width;
        public final int height;

        public Size(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
