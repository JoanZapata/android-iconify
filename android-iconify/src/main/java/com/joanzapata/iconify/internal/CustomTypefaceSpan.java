package com.joanzapata.iconify.internal;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface type;
    private final float iconSizePx;
    private final int iconColor;

    public CustomTypefaceSpan(String family, Typeface type, float iconSizePx, int iconColor) {
        super(family);
        this.type = type;
        this.iconSizePx = iconSizePx;
        this.iconColor = iconColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeFace(ds, type);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, type);
    }

    private void applyCustomTypeFace(Paint paint, Typeface tf) {
        paint.setFakeBoldText(false);
        paint.setTextSkewX(0f);
        paint.setTypeface(tf);
        if (iconSizePx > 0) paint.setTextSize(iconSizePx);
        if (iconColor > 0) paint.setColor(iconColor);
    }
}