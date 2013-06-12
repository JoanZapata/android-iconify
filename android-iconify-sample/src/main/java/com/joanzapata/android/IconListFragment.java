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
