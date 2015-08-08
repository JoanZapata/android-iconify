# Add support for a new font `XXX`

1. Go to [Icomoon](https://icomoon.io) and generate a font.
1. Copy `./android-iconify-fontawesome` to `./android-iconify-XXX`
1. Update `./settings.gradle` with the new module.
1. Rename class `FontAwesomeIcons` -> `XXXIcons`
1. Rename class `FontAwesomeModule` -> `XXXModule`
1. Replace the font in `src/main/assets` with `XXX.ttf` from Icomoon.
1. Update `XXXModule` as appropriate.
1. Update `XXXIcons` with the mappings.
> If you generated from Icomoon, you can quickly generate the mappings using find `\.(.*?):before \{\n.*?: "\\(.*?)";\n}` replace `$1('\\u$2'),` on the generated `styles.css` file.
1. Add the dependency in `android-iconify-sample` and update the sample with the new font.


