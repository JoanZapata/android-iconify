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

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.icons.sample.R;

import static com.joanzapata.android.iconify.Iconify.IconValue;
import static com.joanzapata.android.iconify.Iconify.IconValue.*;
import static com.joanzapata.android.utils.IconUtils.*;

@EActivity(R.layout.activity_about)
@OptionsMenu(R.menu.menu_about)
public class AboutActivity extends SherlockActivity {

    @ViewById
    protected TextView bullet1, bullet2, bullet3, bullet4, bulletTwitter;

    @AfterViews
    protected void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bullet1.setCompoundDrawablesWithIntrinsicBounds(getIcon(fa_android), null, null, null);
        bullet2.setCompoundDrawablesWithIntrinsicBounds(getIcon(fa_code), null, null, null);
        bullet3.setCompoundDrawablesWithIntrinsicBounds(getIcon(fa_flag), null, null, null);
        bullet4.setCompoundDrawablesWithIntrinsicBounds(getIcon(fa_globe), null, null, null);
        bulletTwitter.setCompoundDrawablesWithIntrinsicBounds(getIcon(fa_twitter), null, null, null);
        setTypefaces(getRobotoTypeface(this), bullet1, bullet2, bullet3, bullet4, bulletTwitter);
    }

    @Override
    @OptionsItem(android.R.id.home)
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OptionsItem(R.id.share)
    @Click(R.id.share)
    public void onShareClicked() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_action));
        this.startActivity(Intent.createChooser(intent, "Share..."));
    }

    @Click(R.id.website)
    public void onWebsiteClicked() {
        String url = "http://joanzapata.com/android-iconify";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Click(R.id.bulletTwitter)
    public void onTwitterClicked() {
        String url = "https://twitter.com/JoanZap";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Drawable aboutDrawable = new IconDrawable(this, IconValue.fa_share)
                .colorRes(R.color.ab_item)
                .actionBarSize();
        menu.findItem(R.id.share).setIcon(aboutDrawable);
        return super.onCreateOptionsMenu(menu);
    }

    private Drawable getIcon(IconValue iconValue) {
        return new IconDrawable(this, iconValue).colorRes(R.color.text).sizeDp(40);
    }

}
