package com.joanzapata.android.iconify.sample;

import android.app.Application;
import com.joanzapata.android.iconify.Iconify;
import com.joanzapata.android.iconify.fonts.FontAwesomeModule;
import com.joanzapata.android.iconify.fonts.TypiconsModule;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule())
                .with(new TypiconsModule());
    }
}
