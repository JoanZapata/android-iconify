package com.joanzapata.android.iconify;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.io.*;

import static android.util.TypedValue.*;

class Utils {

    public static final String ICON_FONT_FOLDER = "icon_tmp";

    private Utils() {
        // Prevents instantiation
    }

    static int convertDpToPx(Context context, float dp) {
        return (int) applyDimension(COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    static boolean isEnabled(int[] stateSet) {
        for (int state : stateSet)
            if (state == R.attr.state_enabled)
                return true;
        return false;
    }

    static File resourceToFile(Context context, String resourceName) throws IOException {
        InputStream inputStream = Iconify.class.getClassLoader().getResourceAsStream(resourceName);
        File f = new File(context.getFilesDir(), ICON_FONT_FOLDER);
        if (!f.exists()) {
            if (!f.mkdirs()) {
                return null;
            }
        }
        File outPath = new File(f, resourceName);
        if (outPath.exists()) return outPath;
        BufferedOutputStream bos = null;
        try {
            byte[] buffer = new byte[inputStream.available()];
            bos = new BufferedOutputStream(new FileOutputStream(outPath));
            int l = 0;
            while ((l = inputStream.read(buffer)) > 0) {
                bos.write(buffer, 0, l);
            }
            return outPath;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    // Don't care
                }
            }
        }
    }
}
