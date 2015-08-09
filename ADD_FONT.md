# Add support for a new font `XXX`

**1** Go to [Icomoon](https://icomoon.io) and generate a font.

**2** Copy `./android-iconify-fontawesome` to `./android-iconify-XXX`

**3** Update `./settings.gradle` with the new module.

**4** Rename class `FontAwesomeIcons` -> `XXXIcons`

**5** Rename class `FontAwesomeModule` -> `XXXModule`

**6** Replace the font in `src/main/assets` with `XXX.ttf` from Icomoon.

**7** Update `XXXModule` as appropriate.

**8** Update `XXXIcons` with the mappings.

> If you generated from Icomoon, you can quickly generate the mappings using find `\.(.*?):before \{\n.*?: "\\(.*?)";\n}` replace `$1('\\u$2'),` on the generated `styles.css` file.

**9** Add the dependency in `android-iconify-sample` and update the sample with the new font.

**10** Compile and test the sample app on your device and make sure everything's ok.
