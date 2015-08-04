package com.joanzapata.android.iconify;

import android.content.Context;
import android.widget.TextView;
import com.joanzapata.android.iconify.internal.IconFontDescriptorWrapper;
import com.joanzapata.android.iconify.internal.ParsingUtil;

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
        System.out.println("Add icons1");
        for (TextView textView : textViews) {
            textView.setText(compute(textView.getContext(), textView.getText()));
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
        System.out.println("Add icons2");
        return ParsingUtil.parse(context, iconFontDescriptors, text);
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

}
