package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.joanzapata.android.iconify.Iconify;

public class IconButton extends Button {

    public IconButton(Context context) {
        super(context);
        init();
    }

    public IconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Iconify.addIcons(this);
    }
}
