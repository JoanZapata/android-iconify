package com.joanzapata.iconify.internal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.text.style.ReplacementSpan;
import com.joanzapata.iconify.Icon;

public class CustomTypefaceSpan extends ReplacementSpan {
    private static final int ROTATION_DURATION = 2000;
    // Font Awesome uses 8-step rotation for pulse, and
    // it seems to have the only pulsing spinner. If
    // spinners with different pulses are introduced at
    // some point, then a pulse property can be
    // implemented for the icons.
    static final int ROTATION_PULSES = 8;
    private static final int ROTATION_PULSE_DURATION = ROTATION_DURATION / ROTATION_PULSES;
    private static final Rect TEXT_BOUNDS = new Rect();
    private static final Paint LOCAL_PAINT = new Paint();
    private static final float BASELINE_RATIO = 1 / 7f;

    private final String icon;
    private final Typeface type;
    private final float iconSizePx;
    private final float iconSizeRatio;
    private final int iconColor;
    private final Animation animation;
    private final boolean baselineAligned;
    private final long rotationStartTime;

    public CustomTypefaceSpan(Icon icon, Typeface type,
            float iconSizePx, float iconSizeRatio, int iconColor,
            Animation animation, boolean baselineAligned) {
        this.animation = animation != null ? animation : Animation.NONE;
        this.baselineAligned = baselineAligned;
        this.icon = String.valueOf(icon.character());
        this.type = type;
        this.iconSizePx = iconSizePx;
        this.iconSizeRatio = iconSizeRatio;
        this.iconColor = iconColor;
        this.rotationStartTime = SystemClock.uptimeMillis();
    }

    @Override
    public int getSize(Paint paint, CharSequence text,
            int start, int end, Paint.FontMetricsInt fm) {
        LOCAL_PAINT.set(paint);
        applyCustomTypeFace(LOCAL_PAINT, type);
        LOCAL_PAINT.getTextBounds(icon, 0, 1, TEXT_BOUNDS);
        if (fm != null) {
            float baselineRatio = baselineAligned ? 0 : BASELINE_RATIO;
            fm.descent = (int) (TEXT_BOUNDS.height() * baselineRatio);
            fm.ascent = -(TEXT_BOUNDS.height() - fm.descent);
            fm.top = fm.ascent;
            fm.bottom = fm.descent;
        }
        return TEXT_BOUNDS.width();
    }

    @Override
    public void draw(Canvas canvas, CharSequence text,
            int start, int end, float x, int top, int y,
            int bottom, Paint paint) {
        applyCustomTypeFace(paint, type);
        paint.getTextBounds(icon, 0, 1, TEXT_BOUNDS);
        canvas.save();
        float baselineRatio = baselineAligned ? 0f : BASELINE_RATIO;
        if (animation != Animation.NONE) {
            long timeElapsed = SystemClock.uptimeMillis() - rotationStartTime;
            float rotation;
            switch (animation) {
                case PULSE:
                    rotation = ((int) Math.floor(timeElapsed / (float) ROTATION_PULSE_DURATION))
                            * 360f / ROTATION_PULSES;
                    break;
                case SPIN:
                    rotation = timeElapsed / (float) ROTATION_DURATION * 360f;
                    break;
                default:
                    throw new IllegalStateException();
            }
            float centerX = x + TEXT_BOUNDS.width() / 2f;
            float centerY = y - TEXT_BOUNDS.height() / 2f + TEXT_BOUNDS.height() * baselineRatio;
            canvas.rotate(rotation, centerX, centerY);
        }

        canvas.drawText(icon,
                x - TEXT_BOUNDS.left,
                y - TEXT_BOUNDS.bottom + TEXT_BOUNDS.height() * baselineRatio, paint);
        canvas.restore();
    }

    public Animation getAnimation() {
        return animation;
    }

    private void applyCustomTypeFace(Paint paint, Typeface tf) {
        paint.setFakeBoldText(false);
        paint.setTextSkewX(0f);
        paint.setTypeface(tf);
        if (animation != Animation.NONE) paint.clearShadowLayer();
        if (iconSizeRatio > 0) paint.setTextSize(paint.getTextSize() * iconSizeRatio);
        else if (iconSizePx > 0) paint.setTextSize(iconSizePx);
        if (iconColor < Integer.MAX_VALUE) paint.setColor(iconColor);
    }
}