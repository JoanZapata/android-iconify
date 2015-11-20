package com.joanzapata.iconify.internal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;
import com.joanzapata.iconify.Icon;

public class CustomTypefaceSpan extends ReplacementSpan {
    private static final int ROTATION_DURATION = 2000;
    private static final Rect TEXT_BOUNDS = new Rect();
    private static final Paint LOCAL_PAINT = new Paint();
    private static final float BASELINE_RATIO = 1 / 7f;

    private final String icon;
    private final Typeface type;
    private final float iconSizePx;
    private final float iconSizeRatio;
    private final int iconColor;
    private final boolean spin;
    private final long rotationStartTime;
    private final int rotation;
    private final boolean flip_vertical;
    private final boolean flip_horizontal;

    public CustomTypefaceSpan(Icon icon, Typeface type, float iconSizePx, float iconSizeRatio, int iconColor, boolean spin, int rotation, boolean flip_vertical, boolean flip_horizontal) {
        this.spin = spin;
        this.icon = String.valueOf(icon.character());
        this.type = type;
        this.iconSizePx = iconSizePx;
        this.iconSizeRatio = iconSizeRatio;
        this.iconColor = iconColor;
        this.rotation = rotation;
        this.flip_vertical = flip_vertical;
        this.flip_horizontal = flip_horizontal;
        this.rotationStartTime = System.currentTimeMillis();
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        LOCAL_PAINT.set(paint);
        applyCustomTypeFace(LOCAL_PAINT, type);
        LOCAL_PAINT.getTextBounds(icon, 0, 1, TEXT_BOUNDS);
        if (fm != null) {
            fm.descent = (int) (TEXT_BOUNDS.height() * BASELINE_RATIO);
            fm.ascent = -(TEXT_BOUNDS.height() - fm.descent);
            fm.top = fm.ascent;
            fm.bottom = fm.descent;
        }
        return TEXT_BOUNDS.width();
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        applyCustomTypeFace(paint, type);
        paint.getTextBounds(icon, 0, 1, TEXT_BOUNDS);
        float centerX = x + TEXT_BOUNDS.width() / 2f;
        float centerY = y - TEXT_BOUNDS.height() / 2f + TEXT_BOUNDS.height() * BASELINE_RATIO;
        canvas.save();
        if (spin) {
            float angle = (System.currentTimeMillis() - rotationStartTime) / (float) ROTATION_DURATION * 360f;
            canvas.rotate(angle, centerX, centerY);
        }
        if(rotation > 0)
            canvas.rotate(rotation, centerX, centerY);
        if(flip_horizontal)
            canvas.scale(-1, 1, centerX, centerY);
        if (flip_vertical)
            canvas.scale(-1, 1, centerX, centerY);

        canvas.drawText(icon,
                x - TEXT_BOUNDS.left,
                y - TEXT_BOUNDS.bottom + TEXT_BOUNDS.height() * BASELINE_RATIO, paint);
        canvas.restore();
    }

    public boolean isAnimated() {
        return spin;
    }

    private void applyCustomTypeFace(Paint paint, Typeface tf) {
        paint.setFakeBoldText(false);
        paint.setTextSkewX(0f);
        paint.setTypeface(tf);
        if (spin) paint.clearShadowLayer();
        if (iconSizeRatio > 0) paint.setTextSize(paint.getTextSize() * iconSizeRatio);
        else if (iconSizePx > 0) paint.setTextSize(iconSizePx);
        if (iconColor < Integer.MAX_VALUE) paint.setColor(iconColor);
    }
}