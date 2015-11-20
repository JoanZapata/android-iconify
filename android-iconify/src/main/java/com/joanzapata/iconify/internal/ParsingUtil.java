package com.joanzapata.iconify.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.TypedValue;
import android.widget.TextView;
import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.internal.HasOnViewAttachListener.OnViewAttachListener;

import java.util.List;

public final class ParsingUtil {

    // Prevents instantiation
    private ParsingUtil() {}

    public static CharSequence parse(
            Context context,
            List<IconFontDescriptorWrapper> iconFontDescriptors,
            CharSequence text,
            final TextView target) {
        context = context.getApplicationContext();

        // Analyse the text and replace {} blocks with the appropriate character
        // Retain all transformations in the accumulator
        final SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(text);
        recursivePrepareSpannableIndexes(context,
                text.toString(), spannableBuilder,
                iconFontDescriptors, 0);
        boolean isAnimated = hasAnimatedSpans(spannableBuilder);

        // If animated, periodically invalidate the TextView so that the
        // CustomTypefaceSpan can redraw itself
        if (isAnimated) {
            if (target == null)
                throw new IllegalArgumentException("You can't use \"spin\" without providing the target TextView.");
            if (!(target instanceof HasOnViewAttachListener))
                throw new IllegalArgumentException(target.getClass().getSimpleName() + " does not implement " +
                        "HasOnViewAttachListener. Please use IconTextView, IconButton or IconToggleButton.");

            ((HasOnViewAttachListener) target).setOnViewAttachListener(new OnViewAttachListener() {
                boolean isAttached = false;

                @Override
                public void onAttach() {
                    isAttached = true;
                    ViewCompat.postOnAnimation(target, new Runnable() {
                        @Override
                        public void run() {
                            if (isAttached) {
                                target.invalidate();
                                ViewCompat.postOnAnimation(target, this);
                            }
                        }
                    });
                }

                @Override
                public void onDetach() {
                    isAttached = false;
                }
            });

        } else if (target instanceof HasOnViewAttachListener) {
            ((HasOnViewAttachListener) target).setOnViewAttachListener(null);
        }

        return spannableBuilder;
    }

    private static boolean hasAnimatedSpans(SpannableStringBuilder spannableBuilder) {
        CustomTypefaceSpan[] spans = spannableBuilder.getSpans(0, spannableBuilder.length(), CustomTypefaceSpan.class);
        for (CustomTypefaceSpan span : spans) {
            if (span.isAnimated())
                return true;
        }
        return false;
    }

    private static void recursivePrepareSpannableIndexes(
            Context context,
            String fullText,
            SpannableStringBuilder text,
            List<IconFontDescriptorWrapper> iconFontDescriptors,
            int start) {

        // Try to find a {...} in the string and extract expression from it
        String stringText = text.toString();
        int startIndex = stringText.indexOf("{", start);
        if (startIndex == -1) return;
        int endIndex = stringText.indexOf("}", startIndex) + 1;
        String expression = stringText.substring(startIndex + 1, endIndex - 1);

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
            recursivePrepareSpannableIndexes(context, fullText, text, iconFontDescriptors, endIndex);
            return;
        }

        // See if any more stroke within {} should be applied
        float iconSizePx = -1;
        int iconColor = Integer.MAX_VALUE;
        float iconSizeRatio = -1;
        boolean spin = false;
        int rotation = 0;
        boolean flip_vertical = false;
        boolean flip_horizontal = false;
        for (int i = 1; i < strokes.length; i++) {
            String stroke = strokes[i];

            // Look for "spin"
            if (stroke.equalsIgnoreCase("spin")) {
                spin = true;
            }

            // Look for an icon size
            else if (stroke.matches("([0-9]*(\\.[0-9]*)?)dp")) {
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
            }

            // Look for an icon color
            else if (stroke.matches("#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})")) {
                iconColor = Color.parseColor(stroke);
            } else if (stroke.matches("@color/(.*)")) {
                iconColor = getColorFromResource(context, stroke.substring(7));
                if (iconColor == Integer.MAX_VALUE)
                    throw new IllegalArgumentException("Unknown resource " + stroke + " in \"" + fullText + "\"");
            }

            // look for icon rotation
            else if (stroke.matches("rotate-([0-9]+)")) {
                try{
                    rotation = Integer.parseInt(stroke.substring(8));
                }catch (NumberFormatException e){
                    throw new IllegalArgumentException("Bad number format " + stroke + " in \"" + fullText + "\"");
                }
            }

            // look for icon mirroring
            else if (stroke.equals("flip-vertical")) {
                flip_vertical = true;
            } else if (stroke.equals("flip-horizontal")) {
                flip_horizontal = true;
            }

            else {
                throw new IllegalArgumentException("Unknown expression " + stroke + " in \"" + fullText + "\"");
            }


        }

        // Replace the character and apply the typeface
        text = text.replace(startIndex, endIndex, "" + icon.character());
        text.setSpan(new CustomTypefaceSpan(icon,
                        iconFontDescriptor.getTypeface(context),
                        iconSizePx, iconSizeRatio, iconColor, spin, rotation,
                        flip_vertical, flip_horizontal),
                startIndex, startIndex + 1,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        recursivePrepareSpannableIndexes(context, fullText, text, iconFontDescriptors, startIndex);
    }

    public static float getPxFromDimen(Context context, String resName) {
        Resources resources = context.getResources();
        int resId = resources.getIdentifier(
                resName, "dimen",
                context.getPackageName());
        if (resId <= 0) return -1;
        return resources.getDimension(resId);
    }

    public static int getColorFromResource(Context context, String resName) {
        Resources resources = context.getResources();
        int resId = resources.getIdentifier(
                resName, "color",
                context.getPackageName());
        if (resId <= 0) return Integer.MAX_VALUE;
        return resources.getColor(resId);
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
