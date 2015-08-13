package com.joanzapata.iconify.internal;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;
import android.view.animation.LinearInterpolator;
import com.joanzapata.iconify.Icon;

public class CustomTypefaceSpan extends ReplacementSpan {
    private static final int ROTATION_DURATION = 2000;
    private static final Rect TEXT_BOUNDS = new Rect();
    private static final Paint LOCAL_PAINT = new Paint();

    private final String icon;
    private final Typeface type;
    private final float iconSizePx;
    private final float iconSizeRatio;
    private final int iconColor;
    private final boolean rotate;
    private final ValueAnimator valueAnimator;

    public CustomTypefaceSpan(Icon icon, Typeface type, float iconSizePx, float iconSizeRatio, int iconColor, boolean rotate) {
        this.rotate = rotate;
        this.icon = String.valueOf(icon.character());
        this.type = type;
        this.iconSizePx = iconSizePx;
        this.iconSizeRatio = iconSizeRatio;
        this.iconColor = iconColor;
        if (rotate) {
            valueAnimator = ValueAnimator.ofFloat(0f, 360f);
            valueAnimator.setDuration(ROTATION_DURATION);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.start();
        } else {
            valueAnimator = null;
        }
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        LOCAL_PAINT.set(paint);
        applyCustomTypeFace(LOCAL_PAINT, type);
        LOCAL_PAINT.getTextBounds(icon, 0, 1, TEXT_BOUNDS);
        if (fm != null) {
            fm.ascent = TEXT_BOUNDS.top;
            fm.descent = TEXT_BOUNDS.bottom;
            fm.top = fm.ascent;
            fm.bottom = fm.descent;
        }
        return TEXT_BOUNDS.width();
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        applyCustomTypeFace(paint, type);
        paint.getTextBounds(icon, 0, 1, TEXT_BOUNDS);
        canvas.save();
        if (rotate) {
            Float rotation = (Float) valueAnimator.getAnimatedValue();
            float centerX = x + TEXT_BOUNDS.centerX();
            float centerY = y + TEXT_BOUNDS.centerY();
            canvas.rotate(rotation, centerX, centerY);
        }
        canvas.drawText(icon, x, y, paint);
        canvas.restore();
    }

    public boolean isAnimated() {
        return rotate;
    }

    private void applyCustomTypeFace(Paint paint, Typeface tf) {
        paint.setFakeBoldText(false);
        paint.setTextSkewX(0f);
        paint.setTypeface(tf);
        if (rotate) paint.clearShadowLayer();
        if (iconSizeRatio > 0) paint.setTextSize(paint.getTextSize() * iconSizeRatio);
        else if (iconSizePx > 0) paint.setTextSize(iconSizePx);
        if (iconColor < Integer.MAX_VALUE) paint.setColor(iconColor);
    }
}