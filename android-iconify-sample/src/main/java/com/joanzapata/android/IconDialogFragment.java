package com.joanzapata.android;

import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.*;
import com.joanzapata.android.iconify.Iconify;
import com.joanzapata.android.icons.sample.R;
import com.joanzapata.android.utils.IconUtils;

import static com.joanzapata.android.iconify.Iconify.IconValue;
import static com.joanzapata.android.utils.IconUtils.unicodeValue;

@EFragment(R.layout.dialog_icon)
public class IconDialogFragment extends DialogFragment {

    @FragmentArg
    protected IconValue iconValue;

    @ViewById
    protected TextView dialogBigIcon, dialogText, dialogDescription;

    @AfterViews
    protected void init() {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogBigIcon.setText(iconValue.formattedName());
        dialogText.setText(iconValue.name());
        Iconify.addIcons(dialogBigIcon);
        dialogDescription.setText(unicodeValue(iconValue));
    }

}
