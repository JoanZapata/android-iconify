package com.joanzapata.android.iconify.fonts;

import com.joanzapata.android.iconify.Icon;
import com.joanzapata.android.iconify.IconFontDescriptor;

/** IconFontDescriptor for FontAwesome. */
public class EntypoModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "entypo.ttf";
    }

    @Override
    public Icon[] characters() {
        return EntypoIcons.values();
    }
}
