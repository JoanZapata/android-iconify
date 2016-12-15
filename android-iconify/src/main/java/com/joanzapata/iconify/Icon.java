package com.joanzapata.iconify;

/**
 * Icon represents one icon in an icon font.
 */
public interface Icon {

    /** The key of icon, for example 'fa-ok' */
    String key();

    /** The character matching the key in the font, for example String.valueOf('\u4354') or in case
     * of characters outside the initial Basic Multilingual Plane, simply "\uD83D\uDC64" */
    String character();

}
