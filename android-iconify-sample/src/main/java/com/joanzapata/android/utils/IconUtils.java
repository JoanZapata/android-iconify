package com.joanzapata.android.utils;

import com.joanzapata.android.iconify.Iconify;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class IconUtils {

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
}
