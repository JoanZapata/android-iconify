package com.joanzapata.iconify.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.sample.utils.AndroidUtils;
import com.joanzapata.iconify.sample.utils.SearchUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity
        implements MenuItemCompat.OnActionExpandListener,
        SearchView.OnQueryTextListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_no_result)
    TextView tvNoResult;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private SearchView searchView;

    private Handler handler;
    private Runnable runnable;
    private String lastNoResultKeyword;
    private IconAdapter adapter;

    ///////////////////////////////////////////

    public static void launch(Context from) {
        Intent intent = new Intent(from, SearchActivity.class);
        from.startActivity(intent);
    }

    ///////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setupToolbar();
        setupRecyclerView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        int nbColumns = AndroidUtils.getScreenSize(this).width /
                getResources().getDimensionPixelSize(R.dimen.item_width);
        recyclerView.setLayoutManager(new GridLayoutManager(this, nbColumns));
        adapter = new IconAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.expandActionView(searchItem);
        MenuItemCompat.setOnActionExpandListener(searchItem, this);

        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.WHITE);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        finish();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        if (handler == null) {
            handler = new Handler();
        }
        handler.removeCallbacks(runnable);
        runnable = new Runnable() {
            @Override
            public void run() {
                doSearch(newText);
            }
        };
        handler.postDelayed(runnable, 500);
        return false;
    }

    private void doSearch(final String keyword) {
        if (TextUtils.isEmpty(keyword)
                || lastNoResultKeyword != null
                && keyword.startsWith(lastNoResultKeyword)) {
            adapter.reset();
            adapter.notifyDataSetChanged();
            if (TextUtils.isEmpty(keyword)) {
                tvNoResult.setVisibility(View.GONE);
            } else {
                tvNoResult.setVisibility(View.VISIBLE);
            }
            return;
        }

        // TODO: it is better to search in thread, but here it is also enough
        List<Icon> icons = SearchUtils.searchIcons(keyword);
        adapter.setIcons(icons);
        adapter.notifyDataSetChanged();
        if (icons.size() == 0) {
            lastNoResultKeyword = keyword;
            tvNoResult.setVisibility(View.VISIBLE);
        } else {
            tvNoResult.setVisibility(View.GONE);
        }
    }

}
