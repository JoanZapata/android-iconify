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
import android.widget.GridView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.joanzapata.android.icons.sample.R;

import static com.joanzapata.android.iconify.Iconify.IconValue;
import static com.joanzapata.android.utils.IconUtils.sort;
import static java.lang.String.format;
import static java.util.Arrays.asList;

@EActivity(R.layout.activity_iconlist)
@OptionsMenu(R.menu.menu_iconlist)
public class IconListActivity extends SherlockFragmentActivity {

    @ViewById
    protected GridView gridView;

    @AfterViews
    void afterViews() {
        getSupportActionBar().setIcon(R.drawable.actionbar_logo);
        gridView.setAdapter(new QuickAdapter<IconValue>(this, R.layout.item, sort(asList(IconValue.values()))) {
            @Override
            protected void convert(BaseAdapterHelper helper, IconValue iconValue) {
                String iconName = iconValue.toString().replaceAll("_", "-");
                helper.setText(R.id.iconText, format("{%s}  %s", iconName, iconName));
                Iconify.addIcons((TextView) helper.getView(R.id.iconText));
            }
        });
    }

    @OptionsItem(R.id.about)
    void onAboutClicked() {
        AboutActivity_.intent(this).start();
    }

    @ItemClick
    void gridView(IconValue iconValue) {
        IconDialogFragment_.builder()
                .iconValue(iconValue)
                .build()
                .show(getSupportFragmentManager(),
                        IconDialogFragment.class.getSimpleName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Drawable aboutDrawable = new IconDrawable(this, IconValue.fa_question)
                .colorRes(R.color.ab_item)
                .actionBarSize();
        menu.findItem(R.id.about).setIcon(aboutDrawable);
        return super.onCreateOptionsMenu(menu);
    }
}
