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

import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockDialogFragment;
import com.googlecode.androidannotations.annotations.*;
import com.joanzapata.android.iconify.Iconify;
import com.joanzapata.android.icons.sample.R;
import com.joanzapata.android.utils.IconUtils;

import static com.joanzapata.android.iconify.Iconify.IconValue;
import static com.joanzapata.android.utils.IconUtils.unicodeValue;

@EFragment(R.layout.dialog_icon)
public class IconDialogFragment extends SherlockDialogFragment {

    @FragmentArg
    protected IconValue iconValue;

    @ViewById
    protected TextView dialogBigIcon, dialogText, dialogDescription;

    @AfterViews
    protected void init() {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        final String text = iconValue.formattedName();
        dialogBigIcon.setText(text);
        dialogText.setText(iconValue.name());
        Iconify.addIcons(dialogBigIcon);
        dialogDescription.setText(unicodeValue(iconValue));
    }

}
