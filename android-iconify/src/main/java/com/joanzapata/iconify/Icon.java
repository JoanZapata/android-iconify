package com.joanzapata.iconify;

/**
 * Icon represents one icon in an icon font.
 */
public interface Icon {

    /** The key of icon, for example 'fa-ok' */
    String key();

    /** The character matching the key in the font, for example '\u4354' */
    char character();

}
