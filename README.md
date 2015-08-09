![Alt](graphics/logo.jpg)

[![Alt](http://developer.android.com/images/brand/en_app_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.joanzapata.android.icons.sample)

**If you're tired of copying 5 images (ldpi, mdpi, hdpi, xhdpi, xxhdpi) for each icon you want to use in your app, for each color you want to use them with, and bang your head on the wall when you suddenly need to change their color or size, then I think ```Iconify``` can help you.**

**Iconify** allows you to easily include vector icons in your `TextView`, `Button`, etc…
The icons are infinitely **scalable**, and **customizable** with shadows and everything you can do on texts.

It currently supports three icon font libraries out-of-the-box, and there are many more to come. In case you need a custom one, it allows you to add your own icons from icon font generation services like [fontello.com](http://fontello.com/), [icomoon.io](https://icomoon.io/) or [fontastic.me](http://fontastic.me/).

-----

### Install

Include the modules you need and declare them in your `Application`.

```gradle
dependencies {
    compile 'com.joanzapata.iconify:android-iconify-fontawesome:2.0.0'
    compile 'com.joanzapata.iconify:android-iconify-entypo:2.0.0'
    compile 'com.joanzapata.iconify:android-iconify-typicons:2.0.0'
}
```

```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify
            .with(new FontAwesomeModule())
            .with(new EntypoModule())
            .with(new TypiconsModule());
    }
}
```

### Show icons in text widgets

If you need to put an icon on a ```TextView``` or a ```Button```, use the ```{ }``` syntax. The icons act exactly like the text, so you can apply shadow, size and color on them!

```xml
<com.joanzapata.iconify.widget.IconTextView
    android:text="I {fa-heart-o} to {fa-code} on {fa-android}"
    android:shadowColor="#22000000"
    android:shadowDx="3"
    android:shadowDy="3"
    android:shadowRadius="1"
    android:textSize="40sp"
    android:textColor="#FF..."
    ... />
```

![Alt](graphics/androids.png)

> You can either use ```IconTextView``` / ```IconButton``` or use any ```TextView``` and then programmatically call ```Iconify.addIcons(myTextView)```.

### Show an icon where you need a `Drawable`

If you need an icon in an ```ImageView``` or in your ```ActionBar``` menu item, then you should use ```IconDrawable```. Again, icons are infinitely scalable and will never get fuzzy!

```java
// Set an icon in the ActionBar
menu.findItem(R.id.share).setIcon(
   new IconDrawable(this, FontAwesomeIcons.fa_share)
   .colorRes(R.color.ab_icon)
   .actionBarSize());
```

-----

## Migration from 1.X.X

Iconify 2.X.X introduces breaking changes, here's the full list:

**In build file**

- `compile 'com.joanzapata.android:android-iconify:1.X.X'` -> `compile 'com.joanzapata.iconify:android-iconify-fontawesome:2.X.X'`

**In layouts**

- `android.widget.IconButton` -> `com.joanzapata.iconify.views.IconButton`
- `android.widget.IconTextView` -> `com.joanzapata.iconify.views.IconTextView`
- `android.widget.IconToggleButton` -> `com.joanzapata.iconify.views.IconToggleButton`

**In code**

- `com.joanzapata.android.iconify...` -> `com.joanzapata.iconify...`
- `Iconify.IconValue.fa_something` -> `FontAwesomeIcons.fa_something`
- Nothing in `Application` class -> `Iconify.with(new FontAwesomeModule())`

## Proguard

If you use Proguard, don't forget to add the following rule.

```
-keep class com.joanzapata.** { *; }
```

## Contributions

* Joan Zapata [@JoanZapata](https://github.com/JoanZapata)
* Damien Villeneuve [@DayS](https://github.com/DayS)
* Mike Penz [@mikepenz](https://github.com/mikepenz)

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
