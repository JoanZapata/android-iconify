package com.joanzapata.android.iconify.internal;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.SparseArray;
import com.joanzapata.android.iconify.Icon;

import java.util.List;

public final class ParsingUtil {

    // Prevents instantiation
    private ParsingUtil() {}

    public static SpannableString parse(Context context, List<IconFontDescriptorWrapper> iconFontDescriptors, CharSequence text) {
        context = context.getApplicationContext();

        // Analyse the text and replace {} blocks with the appropriate character
        // Retain all transformations in the accumulator
        SparseArray<IconFontDescriptorWrapper> accumulator = new SparseArray<IconFontDescriptorWrapper>();
        String result = recursivePrepareSpannableIndexes(new StringBuilder(text), iconFontDescriptors, accumulator);
        SpannableString spannableString = SpannableString.valueOf(result);

        // Then apply spans at all positions
        int size = accumulator.size();
        for (int i = 0; i < size; i++) {
            int index = accumulator.keyAt(i);
            IconFontDescriptorWrapper descriptor = accumulator.valueAt(i);
            spannableString.setSpan(descriptor.getCustomTypefaceSpan(context), index, index + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }

    private static String recursivePrepareSpannableIndexes(StringBuilder text,
            List<IconFontDescriptorWrapper> iconFontDescriptors,
            SparseArray<IconFontDescriptorWrapper> accumulator) {

        // Try to find a {...} in the string and extract key from it
        int startIndex = text.indexOf("{");
        if (startIndex == -1) {
            return text.toString();
        }
        int endIndex = text.indexOf("}", startIndex) + 1;
        String key = text.substring(startIndex + 1, endIndex - 1);

        // Loop through the descriptors to find a match
        IconFontDescriptorWrapper iconFontDescriptor = null;
        Icon icon = null;
        for (int i = 0; i < iconFontDescriptors.size(); i++) {
            iconFontDescriptor = iconFontDescriptors.get(i);
            icon = iconFontDescriptor.getIcon(key);
            if (icon != null) break;
        }

        // If no match, throw
        if (icon == null) {
            throw new IllegalArgumentException("Unknown icon key \"" + key + "\"");
        }

        // Get the typeface and set it
        text = text.replace(startIndex, endIndex, "" + icon.character());
        accumulator.put(startIndex, iconFontDescriptor);
        return recursivePrepareSpannableIndexes(text, iconFontDescriptors, accumulator);

    }

}
