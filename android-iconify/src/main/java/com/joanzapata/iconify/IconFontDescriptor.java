package com.joanzapata.iconify;

/**
 * An IconFontDescriptor defines a TTF font file
 * and is able to map keys with characters in this file.
 */
public interface IconFontDescriptor {

    /**
     * The TTF file name.
     * @return a name with no slash, present in the assets.
     */
    String ttfFileName();

    Icon[] characters();

}
