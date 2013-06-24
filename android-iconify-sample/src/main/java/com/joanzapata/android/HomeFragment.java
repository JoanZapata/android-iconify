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

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FromHtml;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.iconify.Iconify;
import com.joanzapata.android.icons.sample.R;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

import static android.animation.ValueAnimator.*;

@EFragment(R.layout.fragment_about)
public class HomeFragment extends Fragment {

    @ViewById
    @FromHtml
    protected TextView fontAwesomeForAndroid;

    @ViewById
    @FromHtml
    protected TextView bullets;

    @ViewById
    protected TextView iconify, title;

    private ValueAnimator valueAnimator;

    @AfterViews
    protected void init() {
        setRetainInstance(true);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "DaysOne-Regular.ttf");
        fontAwesomeForAndroid.setTypeface(face);
        iconify.setTypeface(face);
        Iconify.addIcons(bullets);
        updateColor();
    }

    private void updateColor() {
        if (valueAnimator != null) return;
        valueAnimator = ValueAnimator.ofInt(0xFF33B5E5, 0xFFAA66CC, 0xFF99CC00, 0xFFFFBB33, 0xFFFF4444);
        valueAnimator.setDuration(6000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatMode(REVERSE);
        valueAnimator.setRepeatCount(INFINITE);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                iconify.setTextColor((Integer) valueAnimator.getAnimatedValue());
                title.setTextColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //first saving my state, so the bundle wont be empty.
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }
}
