package com.joanzapata.android.iconify.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.joanzapata.android.iconify.Iconify;

public class IconTextView extends TextView {

    public IconTextView(Context context) {
        super(context);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(Iconify.compute(getContext(), text), type);
    }

}
