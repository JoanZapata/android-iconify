package com.joanzapata.iconify.sample;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.joanzapata.iconify.fonts.TypiconsModule;

public enum Font implements FontIconsViewPagerAdapter.FontWithTitle {
    FONTAWESOME("FontAwesome", new FontAwesomeModule()),
    ENTYPO("Entypo", new EntypoModule()),
    TYPICONS("Typicons", new TypiconsModule()),
    MATERIAL("Material", new MaterialModule());

    private final String title;
    private final IconFontDescriptor descriptor;

    @Override
    public String getTitle() {
        return title;
    }

    public IconFontDescriptor getFont() {
        return descriptor;
    }

    Font(String title, IconFontDescriptor descriptor) {
        this.title = title;
        this.descriptor = descriptor;
    }
}