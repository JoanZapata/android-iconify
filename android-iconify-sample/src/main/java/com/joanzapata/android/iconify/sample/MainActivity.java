package com.joanzapata.android.iconify.sample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.joanzapata.android.iconify.IconFontDescriptor;
import com.joanzapata.android.iconify.fonts.EntypoModule;
import com.joanzapata.android.iconify.fonts.FontAwesomeModule;
import com.joanzapata.android.iconify.fonts.TypiconsModule;

public class MainActivity extends AppCompatActivity {


    public enum Font implements FontIconsViewPagerAdapter.FontWithTitle {
        FONTAWESOME("FontAwesome", new FontAwesomeModule()),
        ENTYPO("Entypo", new EntypoModule()),
        TYPICONS("Typicons", new TypiconsModule());

        private final String title;
        private final IconFontDescriptor descriptor;

        @Override
        public String getTitle() {
            return title;
        }

        public IconFontDescriptor getFont() {
            return descriptor;
        }

        Font(String title, IconFontDescriptor descriptor) {
            this.title = title;
            this.descriptor = descriptor;
        }
    }

    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Setup toolbar
        setSupportActionBar(toolbar);

        // Fill view pager
        viewPager.setAdapter(new FontIconsViewPagerAdapter(Font.values()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
