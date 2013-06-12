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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.GridView;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.iconify.Iconify;
import com.joanzapata.android.icons.sample.R;
import com.joanzapata.android.utils.IconUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.joanzapata.android.iconify.Iconify.IconValue;
import static com.joanzapata.android.utils.IconUtils.sort;
import static java.lang.String.format;
import static java.util.Arrays.asList;

@EFragment(R.layout.fragment_iconlist)
public class IconListFragment extends Fragment {

    @ViewById
    protected GridView gridView;

    @AfterViews
    void afterViews() {
        setRetainInstance(true);
        gridView.setAdapter(new QuickAdapter<IconValue>(this.getActivity(), R.layout.item, sort(asList(IconValue.values()))) {
            @Override
            protected void convert(BaseAdapterHelper helper, IconValue iconValue) {
                helper.setText(R.id.iconText, format("{%s}  %s",
                        iconValue.toString(), iconValue.toString()));
                Iconify.addIcons((TextView) helper.getView(R.id.iconText));
            }
        });
    }

    @ItemClick
    void gridView(IconValue iconValue) {
        IconDialogFragment_.builder()
                .iconValue(iconValue)
                .build()
                .show(getFragmentManager(),
                        IconDialogFragment.class.getSimpleName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //first saving my state, so the bundle wont be empty.
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }
}
