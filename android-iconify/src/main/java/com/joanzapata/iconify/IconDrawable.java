package com.joanzapata.iconify;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.TypedValue;
import com.joanzapata.iconify.internal.IconFontDescriptorWrapper;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Embed an icon into a Drawable that can be used as TextView icons, or ActionBar icons.
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

    private Context context;

    private Icon icon;

    private TextPaint paint;

    private int size = -1;

    private int alpha = 255;

    /**
     * Create an IconDrawable.
     * @param context Your activity or application context.
     * @param iconKey The icon key you want this drawable to display.
     * @throws IllegalArgumentException if the key doesn't match any icon.
     */
    public IconDrawable(Context context, String iconKey) {
        Icon icon = Iconify.findIconForKey(iconKey);
        if (icon == null) {
            throw new IllegalArgumentException("No icon with that key \"" + iconKey + "\".");
        }
        init(context, icon);
    }

    /**
     * Create an IconDrawable.
     * @param context Your activity or application context.
     * @param icon    The icon you want this drawable to display.
     */
    public IconDrawable(Context context, Icon icon) {
        init(context, icon);
    }

    private void init(Context context, Icon icon) {
        this.context = context;
        this.icon = icon;
        paint = new TextPaint();
        IconFontDescriptorWrapper descriptor = Iconify.findTypefaceOf(icon);
        if (descriptor == null) {
            throw new IllegalStateException("Unable to find the module associated " +
                    "with icon " + icon.key() + ", have you registered the module " +
                    "you are trying to use with Iconify.with(...) in your Application?");
        }
        paint.setTypeface(descriptor.getTypeface(context));
        paint.setStyle(Paint.Style.FILL);
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

    /**
     * Returns the icon to be displayed
     * @return The icon
     */
    public final Icon getIcon() {
        return icon;
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
        Rect bounds = getBounds();
        int height = bounds.height();
        paint.setTextSize(height);
        Rect textBounds = new Rect();
        String textValue = String.valueOf(icon.character());
        paint.getTextBounds(textValue, 0, 1, textBounds);
        int textHeight = textBounds.height();
        float textBottom = bounds.top + (height - textHeight) / 2f + textHeight - textBounds.bottom;
        canvas.drawText(textValue, bounds.exactCenterX(), textBottom, paint);
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

    // Util
    private boolean isEnabled(int[] stateSet) {
        for (int state : stateSet)
            if (state == android.R.attr.state_enabled)
                return true;
        return false;
    }

    // Util
    private int convertDpToPx(Context context, float dp) {
        return (int) TypedValue.applyDimension(
                COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}