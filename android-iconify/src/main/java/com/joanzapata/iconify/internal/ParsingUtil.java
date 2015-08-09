package com.joanzapata.iconify.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.SparseArray;
import android.util.TypedValue;
import com.joanzapata.iconify.Icon;

import java.util.List;

public final class ParsingUtil {

    // Prevents instantiation
    private ParsingUtil() {}

    public static SpannableString parse(
            Context context,
            List<IconFontDescriptorWrapper> iconFontDescriptors,
            CharSequence text) {
        context = context.getApplicationContext();

        // Analyse the text and replace {} blocks with the appropriate character
        // Retain all transformations in the accumulator
        SparseArray<CustomTypefaceSpan> accumulator = new SparseArray<CustomTypefaceSpan>();
        String result = recursivePrepareSpannableIndexes(context, text.toString(), new StringBuilder(text), iconFontDescriptors, accumulator, 0);
        SpannableString spannableString = SpannableString.valueOf(result);

        // Then apply spans at all positions
        int size = accumulator.size();
        for (int i = 0; i < size; i++) {
            int index = accumulator.keyAt(i);
            CustomTypefaceSpan descriptor = accumulator.valueAt(i);
            spannableString.setSpan(descriptor, index, index + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }

    private static String recursivePrepareSpannableIndexes(
            Context context,
            String fullText,
            StringBuilder text,
            List<IconFontDescriptorWrapper> iconFontDescriptors,
            SparseArray<CustomTypefaceSpan> accumulator,
            int start) {

        // Try to find a {...} in the string and extract expression from it
        int startIndex = text.indexOf("{", start);
        if (startIndex == -1) {
            return text.toString();
        }
        int endIndex = text.indexOf("}", startIndex) + 1;
        String expression = text.substring(startIndex + 1, endIndex - 1);

        // Split the expression and retrieve the icon key
        String[] strokes = expression.split(" ");
        String key = strokes[0];

        // Loop through the descriptors to find a key match
        IconFontDescriptorWrapper iconFontDescriptor = null;
        Icon icon = null;
        for (int i = 0; i < iconFontDescriptors.size(); i++) {
            iconFontDescriptor = iconFontDescriptors.get(i);
            icon = iconFontDescriptor.getIcon(key);
            if (icon != null) break;
        }

        // If no match, ignore and continue
        if (icon == null) {
            return recursivePrepareSpannableIndexes(context, fullText, text, iconFontDescriptors, accumulator, endIndex);
        }

        // See if any more stroke within {} should be applied
        float iconSizePx = -1;
        int iconColor = -1;
        float iconSizeRatio = -1;
        for (int i = 1; i < strokes.length; i++) {
            String stroke = strokes[i];

            // Look for an icon size
            if (stroke.matches("([0-9]*(\\.[0-9]*)?)dp")) {
                iconSizePx = dpToPx(context, Float.valueOf(stroke.substring(0, stroke.length() - 2)));
            } else if (stroke.matches("([0-9]*(\\.[0-9]*)?)sp")) {
                iconSizePx = spToPx(context, Float.valueOf(stroke.substring(0, stroke.length() - 2)));
            } else if (stroke.matches("([0-9]*)px")) {
                iconSizePx = Integer.valueOf(stroke.substring(0, stroke.length() - 2));
            } else if (stroke.matches("@dimen/(.*)")) {
                iconSizePx = getPxFromDimen(context, stroke.substring(7));
                if (iconSizePx < 0)
                    throw new IllegalArgumentException("Unknown resource " + stroke + " in \"" + fullText + "\"");
            } else if (stroke.matches("([0-9]*(\\.[0-9]*)?)%")) {
                iconSizeRatio = Float.valueOf(stroke.substring(0, stroke.length() - 1)) / 100f;
            } else {
                throw new IllegalArgumentException("Unknown expression " + stroke + " in \"" + fullText + "\"");
            }
        }

        // Get the typeface and set it
        text = text.replace(startIndex, endIndex, "" + icon.character());
        accumulator.put(startIndex, new CustomTypefaceSpan(
                iconFontDescriptor.getIconFontDescriptor().ttfFileName(),
                iconFontDescriptor.getTypeface(context),
                iconSizePx, iconSizeRatio, iconColor));
        return recursivePrepareSpannableIndexes(context, fullText, text, iconFontDescriptors, accumulator, startIndex);

    }

    public static float getPxFromDimen(Context context, String resName) {
        Resources resources = context.getResources();
        int resId = resources.getIdentifier(
                resName, "dimen",
                context.getPackageName());
        if (resId <= 0) return -1;
        return resources.getDimension(resId);
    }

    public static float dpToPx(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static float spToPx(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }

}
