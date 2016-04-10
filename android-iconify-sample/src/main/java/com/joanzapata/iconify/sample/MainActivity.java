package com.joanzapata.iconify.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.fab_search)
    FloatingActionButton fabSearch;

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

        fabSearch.setImageDrawable(new IconDrawable(this, MaterialIcons.md_search)
                .colorRes(android.R.color.white)
                .actionBarSize());
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.launch(MainActivity.this);
            }
        });
    }
}
