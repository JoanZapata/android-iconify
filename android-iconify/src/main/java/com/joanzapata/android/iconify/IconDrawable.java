package com.joanzapata.android.iconify;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.Log;

import static java.lang.String.valueOf;

/**
 * Embed an icon into a Drawable.
 * You can use it to draw an icon in an
 * ImageView for example, or as a TextView's compound image.
 */
public class IconDrawable extends Drawable {

    private final Context context;
    private final Iconify.IconValue icon;
    private TextPaint paint;

    public IconDrawable(Context context, Iconify.IconValue icon) {
        this.context = context;
        this.icon = icon;
        paint = new TextPaint();
        paint.setTypeface(Iconify.getTypeface(context));
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setUnderlineText(false);
        paint.setColor(Color.BLACK);
    }

    public IconDrawable setColor(int color) {
        paint.setColor(color);
        invalidateSelf();
        return this;
    }

    public IconDrawable setColorRes(int colorRes) {
        paint.setColor(context.getResources().getColor(colorRes));
        invalidateSelf();
        return this;
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setTextSize(getBounds().height());
        Rect textBounds = new Rect();
        String textValue = valueOf(icon.character);
        paint.getTextBounds(textValue, 0, 1, textBounds);
        float textBottom = (getBounds().height() - textBounds.height()) / 2f + textBounds.height() - textBounds.bottom;
        canvas.drawText(textValue, getBounds().width() / 2f, textBottom, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return 255;
    }
}
