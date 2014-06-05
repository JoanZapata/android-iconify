![Alt](https://raw.github.com/JoanZapata/android-iconify/master/logo.jpg)

![Alt](https://raw.github.com/JoanZapata/android-iconify/master/header.jpg)

[![Alt](http://developer.android.com/images/brand/en_app_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.joanzapata.android.icons.sample)

> **[All available icons (439)](http://fortawesome.github.io/Font-Awesome/icons)**

**If, like me, you're tired of copying 5 images (ldpi, mdpi, hdpi, xhdpi, xxhdpi) for each icon you want to use in your app, for each color you want to use them with, and bang your head on the wall when you suddently need to change their color or size, then I think ```Iconify``` can help you.**

-----

### About

**Iconify** allows you to include any of the **[FontAwesome 4.1.0 icons by Dave Gandy](http://fortawesome.github.io/Font-Awesome/icons)** in your texts, your ```ActionBar```, and even in your ```EditText```s. Icons are infinitely **scalable**, and **customizable** with shadows and everything you can do on texts.

### Get started #1

If you need icons on a ```TextView```, use the ```{ }``` syntax. You can put any text around it and have more than one icon in the text. Note that the shadows apply to the icons as well.

```xml
<IconTextView
    android:text="{fa-android}"
    android:shadowColor="#22000000"
    android:shadowDx="3"
    android:shadowDy="3"
    android:shadowRadius="1"
    android:textSize="90dp"
    android:textColor="#FF33B5E5"
    ... />
```

> You can either use ```IconTextView``` / ```ButtonTextView``` or use any ```TextView``` and then programmatically call ```Iconify.addIcons(myTextView);```.

![Alt](https://raw.github.com/JoanZapata/android-iconify/master/androids.png)

### Get started #2

If you need an icon in an ```ImageView``` or in your ```ActionBar```, then you should use ```IconDrawable```. Again, icons are infinitely scalable and will never get fuzzy!

```java
// Set an icon in the ActionBar
menu.findItem(R.id.share).setIcon(
   new IconDrawable(this, IconValue.fa_share)
   .colorRes(R.color.ab_icon)
   .actionBarSize());
```

-----

## Get it

[Download JAR](http://search.maven.org/remotecontent?filepath=com/joanzapata/android/android-iconify/1.0.6/android-iconify-1.0.6.jar) or via **Maven Central**

```xml
<dependency>
    <groupId>com.joanzapata.android</groupId>
    <artifactId>android-iconify</artifactId>
    <version>1.0.6</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.joanzapata.android:android-iconify:1.0.6'
```

## License

```
Copyright 2013 Joan Zapata

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
