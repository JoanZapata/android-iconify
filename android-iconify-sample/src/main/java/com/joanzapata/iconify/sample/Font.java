package com.joanzapata.iconify.sample;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.fonts.*;

public enum Font implements FontIconsViewPagerAdapter.FontWithTitle {
    FONTAWESOME("FontAwesome", new FontAwesomeModule()),
    ENTYPO("Entypo", new EntypoModule()),
    TYPICONS("Typicons", new TypiconsModule()),
    IONICONS("Ionicons", new IoniconsModule()),
    MATERIAL("Material", new MaterialModule()),
    MATERIALCOMMUNITY("Material Community", new MaterialCommunityModule()),
    METEOCONS("Meteocons", new MeteoconsModule()),
    WEATHERICONS("WeatherIcons", new WeathericonsModule()),
    SIMPLELINEICONS("SimpleLineIcons", new SimpleLineIconsModule());

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