package com.joanzapata.iconify.internal;

import android.content.Context;
import android.graphics.Typeface;
import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

import java.util.HashMap;
import java.util.Map;

public class IconFontDescriptorWrapper {

    private final IconFontDescriptor iconFontDescriptor;

    private final Map<String, Icon> iconsByKey;

    private Typeface cachedTypeface;

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

    public Typeface getTypeface(Context context) {
        if (cachedTypeface != null) return cachedTypeface;
        synchronized (this) {
            if (cachedTypeface != null) return cachedTypeface;
            cachedTypeface = Typeface.createFromAsset(context.getAssets(), iconFontDescriptor.ttfFileName());
            return cachedTypeface;
        }
    }

    public boolean hasIcon(Icon icon) {
        return iconsByKey.values().contains(icon);
    }
}
