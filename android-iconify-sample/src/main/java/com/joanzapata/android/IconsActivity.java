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

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NonConfigurationInstance;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.icons.sample.R;
import com.viewpagerindicator.TitlePageIndicator;

import java.io.Serializable;

@EActivity(R.layout.activity_main)
public class IconsActivity extends FragmentActivity {

    private static final String TAG = IconsActivity.class.getSimpleName();

    @ViewById
    protected ViewPager viewPager;

    @ViewById
    protected TitlePageIndicator titles;

    @AfterViews
    void afterViews() {
        viewPager.setAdapter(new ViewPagerAdapter(this));
        titles.setViewPager(viewPager);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        @SuppressWarnings("unchecked")
        private Class<? extends Fragment>[] pages = new Class[]
                {HomeFragment_.class, IconListFragment_.class};

        private int[] titles = {
                R.string.page_home,
                R.string.page_list
        };

        private Context context;

        public ViewPagerAdapter(FragmentActivity context) {
            super(context.getSupportFragmentManager());
            this.context = context;
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return context.getString(titles[position]);
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return pages[position].newInstance();
            } catch (Exception e) {
                Log.e(TAG, "", e);
                return null;
            }
        }

    }
}
