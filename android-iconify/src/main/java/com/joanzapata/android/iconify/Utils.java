package com.joanzapata.android.iconify;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

class Utils {

    private Utils() {
        // Prevents instantiation
    }

    static float convertDpToPx(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

}
