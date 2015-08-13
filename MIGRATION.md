# Migration from 1.X.X

Iconify 2.X.X introduces breaking changes, here's the full list:

**In build file**

- `compile 'com.joanzapata.android:android-iconify:1.X.X'` -> `compile 'com.joanzapata.iconify:android-iconify-fontawesome:2.X.X'`

**In layouts**

- `android.widget.IconButton` -> `com.joanzapata.iconify.widget.IconButton`
- `android.widget.IconTextView` -> `com.joanzapata.iconify.widget.IconTextView`
- `android.widget.IconToggleButton` -> `com.joanzapata.iconify.widget.IconToggleButton`

**In code**

- `com.joanzapata.android.iconify...` -> `com.joanzapata.iconify...`
- `Iconify.IconValue.fa_something` -> `FontAwesomeIcons.fa_something`
- Nothing in `Application` class -> `Iconify.with(new FontAwesomeModule())`
