package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.joanzapata.android.iconify.Iconify;

public class IconTextView extends TextView {

    public IconTextView(Context context) {
        super(context);
        init();
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Iconify.addIcons(this);
    }
}
