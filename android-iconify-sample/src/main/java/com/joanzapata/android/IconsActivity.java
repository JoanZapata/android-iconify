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
 */
package com.joanzapata.android;

import android.app.Activity;
import android.widget.GridView;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.iconify.Iconify;
import com.joanzapata.android.icons.sample.R;

import static com.joanzapata.android.iconify.Iconify.IconValue;
import static java.lang.String.format;
import static java.util.Arrays.asList;

@EActivity(R.layout.activity_main)
public class IconsActivity extends Activity {

    @ViewById
    protected GridView gridView;

    @AfterViews
    void afterViews() {
        gridView.setAdapter(new QuickAdapter<IconValue>(this, R.layout.item, asList(IconValue.values())) {
            @Override
            protected void convert(BaseAdapterHelper helper, IconValue iconValue) {
                helper.setText(R.id.icon, format("{%s}", iconValue.toString()))
                        .setText(R.id.iconText, format("{%s} %s",
                                iconValue.toString(), iconValue.toString()));
                Iconify.addIcons((TextView) helper.getView(R.id.icon));
                Iconify.addIcons((TextView) helper.getView(R.id.iconText));
            }
        });
    }
}
