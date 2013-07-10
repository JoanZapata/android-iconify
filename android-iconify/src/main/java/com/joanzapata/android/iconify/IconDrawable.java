package com.joanzapata.android.iconify;

import android.R;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.Log;

import static com.joanzapata.android.iconify.Utils.convertDpToPx;
import static com.joanzapata.android.iconify.Utils.isEnabled;
import static java.lang.String.valueOf;

/**
 * Embed an icon into a Drawable.
 * You can use it to draw an icon in an
 * ImageView for example, or as a TextView's compound image.
 */
public class IconDrawable extends Drawable {

    public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;
    private final Context context;
    private final Iconify.IconValue icon;
    private TextPaint paint;
    private int size = -1;
    private int alpha = 255;

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

    public IconDrawable setActionBarSize() {
        this.size = convertDpToPx(context, ANDROID_ACTIONBAR_ICON_SIZE_DP);
        return this;
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
    public int getIntrinsicHeight() {
        return size;
    }

    @Override
    public int getIntrinsicWidth() {
        return size;
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
    public boolean isStateful() {
        return true;
    }

    @Override
    public boolean setState(int[] stateSet) {
        int oldValue = paint.getAlpha();
        int newValue = isEnabled(stateSet) ? alpha : alpha / 2;
        paint.setAlpha(newValue);
        return oldValue != newValue;
    }

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public void clearColorFilter() {
        paint.setColorFilter(null);
    }

    @Override
    public int getOpacity() {
        return this.alpha;
    }

}
