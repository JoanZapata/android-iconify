package com.joanzapata.android.iconify.fonts;

import com.joanzapata.android.iconify.Icon;
import com.joanzapata.android.iconify.IconFontDescriptor;

public class TypiconsModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "typicons.ttf";
    }

    @Override
    public Icon[] characters() {
        return TypiconsIcons.values();
    }
}
