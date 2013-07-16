![Alt](https://raw.github.com/JoanZapata/android-iconify/master/logo.jpg)

![Alt](https://raw.github.com/JoanZapata/android-iconify/master/header.jpg)

[![Alt](http://developer.android.com/images/brand/en_app_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.joanzapata.android.icons.sample)

> **Iconify** makes it easy to include any of the **[361 FontAwesome icons](http://fortawesome.github.io/Font-Awesome/icons)** in a native Android application. 

> If you're not familiar with it, ```FontAwesome``` is a **font** in which some special characters draw vectorial icons instead of letters. That means you can **scale them as much as you want**, and you can **apply text transforms** on these icons, just like you would do with texts. These icons will **never ever become fuzzy**.

## How to use

Wrap icon names with ```{ }``` where you want the icon to appear in the text.

```xml
<IconTextView
    android:text="FontAwesome {icon_flag} in Android {icon_android} !"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

![Alt](https://raw.github.com/JoanZapata/android-iconify/master/texts.png)

> You can use any icon from [FontAwesome 3.2.0](http://fortawesome.github.io/Font-Awesome/icons/)


# Transforms

The good thing is that you can **scale** and apply **shadows** and **colors** to the icon, like any text.

```xml
<IconTextView
    android:text="{icon_android}"
    android:shadowColor="#22000000"
    android:shadowDx="3"
    android:shadowDy="3"
    android:shadowRadius="1"
    android:textSize="90dp"
    android:textColor="#FF33B5E5"
    ... />
```

![Alt](https://raw.github.com/JoanZapata/android-iconify/master/androids.png)

## Buttons

It plays nice with buttons, just use ```IconButton``` instead of ```Button```.

```xml
<IconButton
    android:text="{icon_retweet} Retweet"
    ... />
```

![Alt](https://raw.github.com/JoanZapata/android-iconify/master/buttons.png)

## Other views

Any ```View``` that extends ```TextView``` can be iconified programmatically.

```java
TextView myTextView = (TextView) findViewById(R.id.myTextView);
Iconify.addIcons(myTextView);
```

## [Download JAR](http://search.maven.org/remotecontent?filepath=com/joanzapata/android/android-iconify/1.0.1/android-iconify-1.0.1.jar)

Or via **Maven Central**

```xml
<dependency>
    <groupId>com.joanzapata.android</groupId>
    <artifactId>android-iconify</artifactId>
    <version>1.0.1</version>
</dependency>
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

It uses FontAwesome font, licensed under OFL 1.1, which is compatible
with this library's license.

    http://scripts.sil.org/cms/scripts/render_download.php?format=file&media_id=OFL_plaintext&filename=OFL.txt
    
```
