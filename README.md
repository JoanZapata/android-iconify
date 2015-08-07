![Alt](logo.jpg)

[![Alt](http://developer.android.com/images/brand/en_app_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.joanzapata.android.icons.sample)

**If you're tired of copying 5 images (ldpi, mdpi, hdpi, xhdpi, xxhdpi) for each icon you want to use in your app, for each color you want to use them with, and bang your head on the wall when you suddenly need to change their color or size, then I think ```Iconify``` can help you.**

**Iconify** allows you to easily include vector icons in your `TextView`, `Button`, etc…
The icons are infinitely **scalable**, and **customizable** with shadows and everything you can do on texts.

The base library contains more than XXX icons, and allows you to add your own icons from icon font generation services like [fontello.com](http://fontello.com/), [icomoon.io](https://icomoon.io/) or [fontastic.me](http://fontastic.me/).

-----

### Install

You don't need to import all the modules, choose the modules you'll want to use.

```gradle
dependencies {
    compile 'com.joanzapata.android.iconify:android-iconify-fontawesome:2.0.0'
    compile 'com.joanzapata.android.iconify:android-iconify-entypo:2.0.0'
    compile 'com.joanzapata.android.iconify:android-iconify-typicons:2.0.0'
    compile 'com.joanzapata.android.iconify:android-iconify-iconic:2.0.0'
    compile 'com.joanzapata.android.iconify:android-iconify-meteocons:2.0.0'
    compile 'com.joanzapata.android.iconify:android-iconify-zocial:2.0.0'
    compile 'com.joanzapata.android.iconify:android-iconify-brandico:2.0.0'
    compile 'com.joanzapata.android.iconify:android-iconify-elusive:2.0.0'
}
```

After that, don't forget to initialize them in your Application's `onCreate`:

```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify
            .with(new FontAwesomeModule())
            .with(new EntypoModule())
            .with(new TypiconsModule())
            .with(new IconicModule())
            .with(new MeteoconsModule())
            .with(new ZocialModule())
            .with(new BrandicoModule())
            .with(new ElusiveModule())
    }
}
```

### Icons in texts

If you need to put an icon on a ```TextView```, use the ```{ }``` syntax.

You'll notice that the shadow, size, and color you apply on the TextView also affect the icons!

```xml
<IconTextView
    android:text="I {fa-heart-o} to {fa-code} on {fa-android}"
    android:shadowColor="#22000000"
    android:shadowDx="3"
    android:shadowDy="3"
    android:shadowRadius="1"
    android:textSize="40sp"
    android:textColor="#FF..."
    ... />
```

> You can either use ```IconTextView``` / ```IconButton``` or use any ```TextView``` and then programmatically call ```Iconify.addIcons(myTextView);```.

![Alt](androids.png)

### Icons as `Drawable`s

If you need an icon in an ```ImageView``` or in your ```ActionBar```, then you should use ```IconDrawable```. Again, icons are infinitely scalable and will never get fuzzy!

```java
// Set an icon in the ActionBar
menu.findItem(R.id.share).setIcon(
   new IconDrawable(this, FontAwesomeIcons.fa_share)
   .colorRes(R.color.ab_icon)
   .actionBarSize());
```

-----

## Proguard

If you use Proguard, don't forget to add the following rule.

```
-keep class com.joanzapata.** { *; }
```

## License

```
Copyright 2015 Joan Zapata

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

It uses FontAwesome font by Dave Gandy, licensed under OFL 1.1, which is compatible
with this library's license.

    http://scripts.sil.org/cms/scripts/render_download.php?format=file&media_id=OFL_plaintext&filename=OFL.txt
    
```
