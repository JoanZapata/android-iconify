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
package com.joanzapata.android.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import com.joanzapata.android.iconify.Iconify;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.toHexString;

public final class IconUtils {

    private static Typeface typeface;

    private IconUtils() {
        // Prevents instantiation
    }

    public static List<Iconify.IconValue> sort(List<Iconify.IconValue> iconValues) {
        Collections.sort(iconValues, new Comparator<Iconify.IconValue>() {
            @Override
            public int compare(Iconify.IconValue lhs, Iconify.IconValue rhs) {
                return lhs.toString().compareTo(rhs.toString());
            }
        });
        return iconValues;
    }

    public static String unicodeValue(Iconify.IconValue iconValue) {
        return "\\u" + toHexString(iconValue.character() | 0x10000).substring(1);
    }

    public static Typeface getRobotoTypeface(Context context) {
        if (typeface == null)
            typeface = Typeface.createFromAsset(
                    context.getApplicationContext().getAssets(),
                    "Roboto-Light.ttf");
        return typeface;
    }

    public static void setTypefaces(Typeface typeface, TextView... views) {
        for (TextView view : views) {
            view.setTypeface(typeface);
        }
    }
}
