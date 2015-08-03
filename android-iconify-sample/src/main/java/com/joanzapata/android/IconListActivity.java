/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * It uses FontAwesome font, licensed under OFL 1.1, which is compatible
 * with this library's license.
 *
 *     http://scripts.sil.org/cms/scripts/render_download.php?format=file&media_id=OFL_plaintext&filename=OFL.txt
 */
package com.joanzapata.android;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.icons.sample.R;

import static com.joanzapata.android.iconify.Iconify.IconValue;

@EActivity(R.layout.activity_iconlist)
@OptionsMenu(R.menu.menu_iconlist)
public class IconListActivity extends SherlockFragmentActivity implements ActionBar.TabListener {

    @AfterViews
    void afterViews() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.actionbar_logo);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab()
                .setText("FontAwesome")
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab()
                .setText("Fontelico")
                .setTabListener(this));
        actionBar.selectTab(actionBar.getTabAt(0));
    }

    @OptionsItem(R.id.about)
    void onAboutClicked() {
        AboutActivity_.intent(this).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Drawable aboutDrawable = new IconDrawable(this, IconValue.fa_question)
                .colorRes(R.color.ab_item)
                .actionBarSize();
        menu.findItem(R.id.about).setIcon(aboutDrawable);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        if (tab.getPosition() == 0) {
            ft.replace(R.id.containerView, new FontAwesomeFragment_());
        } else {
            ft.replace(R.id.containerView, new FontHelloFragment_());
        }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
}
