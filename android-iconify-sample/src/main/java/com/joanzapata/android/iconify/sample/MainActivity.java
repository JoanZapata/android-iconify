package com.joanzapata.android.iconify.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.joanzapata.android.iconify.Iconify;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.text);
        System.out.println("Add icons");
        Iconify.addIcons(text);
    }
}
