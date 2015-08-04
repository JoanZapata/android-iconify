package com.joanzapata.android.iconify.internal;

import android.content.Context;
import android.graphics.Typeface;
import com.joanzapata.android.iconify.Icon;
import com.joanzapata.android.iconify.IconFontDescriptor;

import java.util.HashMap;
import java.util.Map;

public class IconFontDescriptorWrapper {

    private final IconFontDescriptor iconFontDescriptor;

    private final Map<String, Icon> iconsByKey;

    private Typeface cachedTypeface;

    private CustomTypefaceSpan customTypefaceSpan;

    public IconFontDescriptorWrapper(IconFontDescriptor iconFontDescriptor) {
        this.iconFontDescriptor = iconFontDescriptor;
        iconsByKey = new HashMap<String, Icon>();
        Icon[] characters = iconFontDescriptor.characters();
        for (int i = 0, charactersLength = characters.length; i < charactersLength; i++) {
            Icon icon = characters[i];
            iconsByKey.put(icon.key(), icon);
        }
    }

    public Icon getIcon(String key) {
        return iconsByKey.get(key);
    }

    public IconFontDescriptor getIconFontDescriptor() {
        return iconFontDescriptor;
    }

    public CustomTypefaceSpan getCustomTypefaceSpan(Context context) {
        if (customTypefaceSpan != null) return customTypefaceSpan;
        synchronized (this) {
            if (customTypefaceSpan != null) return customTypefaceSpan;
            customTypefaceSpan = new CustomTypefaceSpan(iconFontDescriptor.ttfFileName(), getTypeface(context));
            return customTypefaceSpan;
        }
    }

    public Typeface getTypeface(Context context) {
        if (cachedTypeface != null) return cachedTypeface;
        synchronized (this) {
            if (cachedTypeface != null) return cachedTypeface;
            cachedTypeface = Typeface.createFromAsset(context.getAssets(), iconFontDescriptor.ttfFileName());
            return cachedTypeface;
        }
    }
}
