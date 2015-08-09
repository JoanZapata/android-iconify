package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class MaterialModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-material.ttf";
    }

    @Override
    public Icon[] characters() {
        return MaterialIcons.values();
    }
}
