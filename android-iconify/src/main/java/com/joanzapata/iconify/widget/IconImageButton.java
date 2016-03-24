package com.joanzapata.iconify.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.R;

public class IconImageButton extends ImageButton {

    private int color;

    public IconImageButton(Context context) {
        super(context);
    }

    public IconImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.IconImageView, defStyleAttr, 0);
        color = a.getColor(R.styleable.IconImageView_iconColor, Color.BLACK);
        String iconKey = a.getString(R.styleable.IconImageView_iconName);
        if (iconKey != null) {
            setImageDrawable(new IconDrawable(context, iconKey));
        }
        a.recycle();
    }

    public void setIcon(Icon icon) {
        setImageDrawable(new IconDrawable(getContext(), icon));
    }

    public final Icon getIcon() {
        Drawable drawable = getDrawable();
        if (drawable instanceof IconDrawable) {
            return ((IconDrawable) drawable).getIcon();
        }
        return null;
    }

    public void setIconColor(int color) {
        this.color = color;
        Drawable drawable = getDrawable();
        if (drawable instanceof IconDrawable) {
            ((IconDrawable) drawable).color(color);
        }
    }

    public void setIconColorResource(int colorResId) {
        setIconColor(getContext().getResources().getColor(colorResId));
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (drawable instanceof IconDrawable && drawable != getDrawable()) {
            ((IconDrawable) drawable).color(color);
        }
        super.setImageDrawable(drawable);
    }

}
