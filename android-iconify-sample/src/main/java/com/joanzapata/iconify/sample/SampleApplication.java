package com.joanzapata.iconify.sample;

import android.app.Application;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.TypiconsModule;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule())
                .with(new TypiconsModule())
                .with(new EntypoModule());
    }
}
