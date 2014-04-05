/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * It uses FontAwesome font, licensed under OFL 1.1, which is compatible
 * with this library's license.
 *
 *     http://scripts.sil.org/cms/scripts/render_download.php?format=file&media_id=OFL_plaintext&filename=OFL.txt
 */
package com.joanzapata.android.iconify;

import static com.joanzapata.android.iconify.Utils.convertDpToPx;
import static com.joanzapata.android.iconify.Utils.isEnabled;
import static java.lang.String.valueOf;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

/**
 * Embed an icon into a Drawable that can be used as TextView icons, or ActionBar icons.
 * <p/>
 * <pre>
 *     new IconDrawable(context, IconValue.icon_star)
 *           .colorRes(R.color.white)
 *           .actionBarSize();
 * </pre>
 * If you don't set the size of the drawable, it will use the size
 * that is given to him. Note that in an ActionBar, if you don't
 * set the size explicitly it uses 0, so please use actionBarSize().
 */
public class IconDrawable extends Drawable {

    public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;

    private final Context context;

    private final Iconify.IconValue icon;

    private TextPaint paint;

    private int size = -1;

    private int alpha = 255;

    /**
     * Create an IconDrawable.
     * @param context Your activity or application context.
     * @param icon    The icon you want this drawable to display.
     */
    public IconDrawable(Context context, Iconify.IconValue icon) {
        this.context = context;
        this.icon = icon;
        paint = new TextPaint();
        paint.setTypeface(Iconify.getTypeface(context));
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setUnderlineText(false);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
    }

    /**
     * Set the size of this icon to the standard Android ActionBar.
     * @return The current IconDrawable for chaining.
     */
    public IconDrawable actionBarSize() {
        return sizeDp(ANDROID_ACTIONBAR_ICON_SIZE_DP);
    }

    /**
     * Set the size of the drawable.
     * @param dimenRes The dimension resource.
     * @return The current IconDrawable for chaining.
     */
    public IconDrawable sizeRes(int dimenRes) {
        return sizePx(context.getResources().getDimensionPixelSize(dimenRes));
    }

    /**
     * Set the size of the drawable.
     * @param size The size in density-independent pixels (dp).
     * @return The current IconDrawable for chaining.
     */
    public IconDrawable sizeDp(int size) {
        return sizePx(convertDpToPx(context, size));
    }

    /**
     * Set the size of the drawable.
     * @param size The size in pixels (px).
     * @return The current IconDrawable for chaining.
     */
    public IconDrawable sizePx(int size) {
        this.size = size;
        setBounds(0, 0, size, size);
        invalidateSelf();
        return this;
    }

    /**
     * Set the color of the drawable.
     * @param color The color, usually from android.graphics.Color or 0xFF012345.
     * @return The current IconDrawable for chaining.
     */
    public IconDrawable color(int color) {
        paint.setColor(color);
        invalidateSelf();
        return this;
    }

    /**
     * Set the color of the drawable.
     * @param colorRes The color resource, from your R file.
     * @return The current IconDrawable for chaining.
     */
    public IconDrawable colorRes(int colorRes) {
        paint.setColor(context.getResources().getColor(colorRes));
        invalidateSelf();
        return this;
    }

    /**
     * Set the alpha of this drawable.
     * @param alpha The alpha, between 0 (transparent) and 255 (opaque).
     * @return The current IconDrawable for chaining.
     */
    public IconDrawable alpha(int alpha) {
        setAlpha(alpha);
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

    /**
     * Sets paint style.
     * @param style to be applied
     */
    public void setStyle(Paint.Style style) {
    	paint.setStyle(style);
    }

}
