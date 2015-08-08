package com.joanzapata.android.iconify.fonts;

import com.joanzapata.android.iconify.Icon;
import com.joanzapata.android.iconify.IconFontDescriptor;

public class FontAwesomeModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-fontawesome.ttf";
    }

    @Override
    public Icon[] characters() {
        return FontAwesomeIcons.values();
    }
}
