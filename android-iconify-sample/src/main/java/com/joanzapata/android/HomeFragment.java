package com.joanzapata.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.joanzapata.android.icons.sample.R;

@EFragment(R.layout.fragment_about)
public class HomeFragment extends Fragment {

    @AfterViews
    protected void init() {
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //first saving my state, so the bundle wont be empty.
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }
}
