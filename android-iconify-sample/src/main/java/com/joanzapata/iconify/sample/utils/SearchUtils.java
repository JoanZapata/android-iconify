package com.joanzapata.iconify.sample.utils;

import android.support.annotation.NonNull;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.sample.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baurine on 4/10/16.
 */
public class SearchUtils {

    public static List<Icon> searchIcons(@NonNull List<String> keywords) {
        List<Icon> icons = new ArrayList<Icon>();
        for (String keyword : keywords) {
            icons.addAll(searchIcons(keyword));
        }
        return icons;
    }

    public static List<Icon> searchIcons(@NonNull String keyword) {
        List<Icon> icons = new ArrayList<Icon>();
        for (Font font : Font.values()) {
            IconFontDescriptor fontDescriptor = font.getFont();
            for (Icon icon : fontDescriptor.characters()) {
                if (icon.key().contains(keyword)) {
                    icons.add(icon);
                }
            }
        }
        return icons;
    }

}
