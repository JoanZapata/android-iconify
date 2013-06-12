package com.joanzapata.android;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FromHtml;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.iconify.Iconify;
import com.joanzapata.android.icons.sample.R;

@EFragment(R.layout.fragment_about)
public class HomeFragment extends Fragment {

    @ViewById
    @FromHtml
    protected TextView fontAwesomeForAndroid;

    @ViewById
    @FromHtml
    protected TextView bullets;

    @ViewById
    protected TextView iconify;

    @AfterViews
    protected void init() {
        setRetainInstance(true);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "DaysOne-Regular.ttf");
        fontAwesomeForAndroid.setTypeface(face);
        iconify.setTypeface(face);
        Iconify.addIcons(bullets);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //first saving my state, so the bundle wont be empty.
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }
}
