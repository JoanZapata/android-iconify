package com.joanzapata.iconify.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.sample.utils.AndroidUtils;
import com.joanzapata.iconify.sample.utils.DebugUtils;
import com.joanzapata.iconify.sample.utils.SearchUtils;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SimilarIconsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_icon_detail)
    IconTextView tvIconDetail;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private IconAdapter adapter;

    ///////////////////////////////////////////

    private static final String KEY_ICON_NAME = "KEY_ICON_NAME";
    private String iconName;

    public static void launch(Context from, String iconName) {
        Intent intent = new Intent(from, SimilarIconsActivity.class);
        intent.putExtra(KEY_ICON_NAME, iconName);
        from.startActivity(intent);
    }

    ///////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar);
        ButterKnife.bind(this);

        getBundleData();
        setupToolbar();
        setupRecyclerView();
        setupTvIconDetail();
        findSimilarIcons();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        getBundleData();
        setupTvIconDetail();
        findSimilarIcons();
    }

    private void getBundleData() {
        iconName = getIntent().getStringExtra(KEY_ICON_NAME);
        DebugUtils.assertFalse(iconName != null);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimilarIconsActivity.this.finish();
            }
        });
    }

    private void setupRecyclerView() {
        int nbColumns = AndroidUtils.getScreenSize(this).width /
                getResources().getDimensionPixelSize(R.dimen.item_width);
        recyclerView.setLayoutManager(new GridLayoutManager(this, nbColumns));
        adapter = new IconAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupTvIconDetail() {
        Icon icon = Iconify.findIconForKey(iconName);
        DebugUtils.assertFalse(icon != null);
        String content = String.format("  {%s} %s\n"
                        + "  {%s #FF3AAD73} %s #FF3AAD73\n"
                        + "  {%s spin} %s spin\n"
                        + "  {%s 36sp} %s 36sp",
                iconName, iconName, iconName, iconName,
                iconName, iconName, iconName, iconName);
        tvIconDetail.setText(content);
    }

    private void findSimilarIcons() {
        String[] keywordArray = iconName.split("-");
        List<String> keywordList = new ArrayList<String>();
        for (int i = 1; i < keywordArray.length; i++) {
            if (keywordArray[i].length() > 1) {
                keywordList.add(keywordArray[i]);
            }
        }
        List<Icon> similarIcons = SearchUtils.searchIcons(keywordList);
        adapter.setIcons(similarIcons);
        adapter.notifyDataSetChanged();
    }

}
