package com.joanzapata.android.iconify.sample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.joanzapata.android.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.android.iconify.sample.utils.AndroidUtils;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Setup toolbar
        setSupportActionBar(toolbar);

        // Tab layout
        tabLayout.addTab(tabLayout.newTab().setText("FontAwesome"));
        tabLayout.addTab(tabLayout.newTab().setText("Typicons"));

        // Fill recycler view
        recyclerView.setHasFixedSize(true);
        int nbColumns = AndroidUtils.getScreenSize(this).width / getResources().getDimensionPixelSize(R.dimen.item_width);
        recyclerView.setLayoutManager(new GridLayoutManager(this, nbColumns));
        recyclerView.setAdapter(new IconAdapter(FontAwesomeIcons.values()));
    }
}
