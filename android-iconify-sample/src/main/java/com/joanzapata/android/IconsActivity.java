/**
 * Copyright 2013 Joan Zapata
 *
 * This file is part of Android-pdfview.
 *
 * Android-pdfview is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Android-pdfview is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Android-pdfview.  If not, see <http://www.gnu.org/licenses/>.
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
