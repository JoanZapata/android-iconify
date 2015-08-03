package com.joanzapata.android;

import com.joanzapata.android.iconify.BaseIconValue;

public enum FontHelloValue implements BaseIconValue {
    hello_emo_happy('\ue800'),
    hello_emo_wink('\ue801'),
    hello_emo_wink2('\ue813'),
    hello_emo_unhappy('\ue802'),
    hello_emo_sleep('\ue803'),
    hello_emo_thumbsup('\ue804'),
    hello_emo_devil('\ue805'),
    hello_emo_surprised('\ue806'),
    hello_emo_tongue('\ue807'),
    hello_emo_coffee('\ue808'),
    hello_emo_sunglasses('\ue809'),
    hello_emo_displeased('\ue80a'),
    hello_emo_beer('\ue80b'),
    hello_emo_grin('\ue80c'),
    hello_emo_angry('\ue80d'),
    hello_emo_saint('\ue80e'),
    hello_emo_cry('\ue80f'),
    hello_emo_shoot('\ue810'),
    hello_emo_squint('\ue811'),
    hello_emo_laugh('\ue812'),
    hello_spin1('\ue830'),
    hello_spin2('\ue831'),
    hello_spin3('\ue832'),
    hello_spin4('\ue834'),
    hello_spin5('\ue838'),
    hello_spin6('\ue839'),
    hello_firefox('\ue840'),
    hello_chrome('\ue841'),
    hello_opera('\ue842'),
    hello_ie('\ue843'),
    hello_crown('\ue844'),
    hello_crown_plus('\ue845'),
    hello_crown_minus('\ue846'),
    hello_marquee('\ue847');

    private char character;

    FontHelloValue(char character) {
        this.character = character;
    }

    @Override
    public char character() {
        return character;
    }

    @Override
    public String getTtfFilename() {
        return "asset:fontelico.ttf";
    }

    @Override
    public String getPrefix() {
        return "hello";
    }

    @Override
    public FontHelloValue iconFrom(String value) {
        return FontHelloValue.valueOf(value);
    }
}
