package com.joanzapata.android.iconify.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;
import com.joanzapata.android.iconify.Iconify;

public class IconToggleButton extends ToggleButton {

    public IconToggleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public IconToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconToggleButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (isInEditMode()) return;
        Iconify.addIcons(this);
    }

    @Override
    public void setTextOn(CharSequence textOn) {
        super.setTextOn(Iconify.compute(getContext(), textOn));
    }

    @Override
    public void setTextOff(CharSequence textOff) {
        super.setTextOff(Iconify.compute(getContext(), textOff));
    }
}
