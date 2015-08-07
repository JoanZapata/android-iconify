package com.joanzapata.android.iconify.fonts;

import com.joanzapata.android.iconify.Icon;
import com.joanzapata.android.iconify.IconFontDescriptor;
import com.joanzapata.android.iconify.fonts.FontAwesomeIcons;

/** IconFontDescriptor for FontAwesome. */
public class FontAwesomeModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "fontawesome-webfont-4.4.0.ttf";
    }

    @Override
    public Icon[] characters() {
        return FontAwesomeIcons.values();
    }
}
