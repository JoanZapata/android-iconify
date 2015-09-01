package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class MaterialCommunityModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-material-community.ttf";
    }

    @Override
    public Icon[] characters() {
        return MaterialCommunityIcons.values();
    }
}
