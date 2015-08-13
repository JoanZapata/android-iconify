package com.joanzapata.iconify;

import android.content.Context;
import android.widget.TextView;
import com.joanzapata.iconify.internal.IconFontDescriptorWrapper;
import com.joanzapata.iconify.internal.ParsingUtil;

import java.util.ArrayList;
import java.util.List;

public class Iconify {

    /** List of icon font descriptors */
    private static List<IconFontDescriptorWrapper> iconFontDescriptors = new ArrayList<IconFontDescriptorWrapper>();

    /**
     * Add support for a new icon font.
     * @param iconFontDescriptor The IconDescriptor holding the ttf file reference and its mappings.
     * @return An initializer instance for chain calls.
     */
    public static IconifyInitializer with(IconFontDescriptor iconFontDescriptor) {
        return new IconifyInitializer(iconFontDescriptor);
    }

    /**
     * Replace "{}" tags in the given text views with actual icons, requesting the IconFontDescriptors
     * one after the others.<p>
     * <strong>This is a one time call.</strong> If you call {@link TextView#setText(CharSequence)} after this,
     * you'll need to call it again.
     * @param textViews The TextView(s) to enhance.
     */
    public static void addIcons(TextView... textViews) {
        for (TextView textView : textViews) {
            if (textView == null) continue;
            textView.setText(compute(textView.getContext(), textView.getText(), textView));
        }
    }

    private static void addIconFontDescriptor(IconFontDescriptor iconFontDescriptor) {

        // Prevent duplicates
        for (IconFontDescriptorWrapper wrapper : iconFontDescriptors) {
            if (wrapper.getIconFontDescriptor().ttfFileName().equals(iconFontDescriptor.ttfFileName())) {
                throw new IllegalArgumentException("Can't add twice the same font \""
                        + iconFontDescriptor.ttfFileName() + "\"");
            }
        }

        // Add to the list
        iconFontDescriptors.add(new IconFontDescriptorWrapper(iconFontDescriptor));

    }

    public static CharSequence compute(Context context, CharSequence text) {
        return compute(context, text, null);
    }

    public static CharSequence compute(Context context, CharSequence text, TextView target) {
        return ParsingUtil.parse(context, iconFontDescriptors, text, target);
    }

    /**
     * Allows chain calls on {@link Iconify#with(IconFontDescriptor)}.
     */
    public static class IconifyInitializer {

        public IconifyInitializer(IconFontDescriptor iconFontDescriptor) {
            Iconify.addIconFontDescriptor(iconFontDescriptor);
        }

        /**
         * Add support for a new icon font.
         * @param iconFontDescriptor The IconDescriptor holding the ttf file reference and its mappings.
         * @return An initializer instance for chain calls.
         */
        public IconifyInitializer with(IconFontDescriptor iconFontDescriptor) {
            Iconify.addIconFontDescriptor(iconFontDescriptor);
            return this;
        }
    }

    /**
     * Internal method for finding the Typeface to apply for a particular icon.
     * Used by {@link IconDrawable}.
     */
    static IconFontDescriptorWrapper findTypefaceOf(Icon icon) {
        for (IconFontDescriptorWrapper iconFontDescriptor : iconFontDescriptors) {
            if (iconFontDescriptor.hasIcon(icon)) {
                return iconFontDescriptor;
            }
        }
        return null;
    }


    /**
     * Retrieve an icon from a key,
     * @return The icon, or null if no icon matches the key.
     */
    static Icon findIconForKey(String iconKey) {
        for (int i = 0, iconFontDescriptorsSize = iconFontDescriptors.size(); i < iconFontDescriptorsSize; i++) {
            IconFontDescriptorWrapper iconFontDescriptor = iconFontDescriptors.get(i);
            Icon icon = iconFontDescriptor.getIcon(iconKey);
            if (icon != null) return icon;
        }
        return null;
    }
}
