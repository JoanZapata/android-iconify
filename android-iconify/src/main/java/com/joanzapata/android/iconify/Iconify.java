package com.joanzapata.android.iconify;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.*;

import static java.lang.String.valueOf;

public final class Iconify {

    private static Typeface typeface = null;

    private Iconify() {
        // Prevent instantiation
    }

    public static final void addIcons(TextView textView) {
        textView.setTypeface(fileStreamTypeface(textView.getContext()));
        textView.setText(replaceIcons(new StringBuilder(textView.getText().toString())));
    }

    private static StringBuilder replaceIcons(StringBuilder text) {
        int startIndex = text.indexOf("{icon_");
        if (startIndex == -1) {
            return text;
        }

        int endIndex = text.substring(startIndex).indexOf("}") + startIndex + 1;
        if (endIndex == startIndex) {
            return text;
        }

        String iconString = text.substring(startIndex + 1, endIndex - 1);
        IconValue value = IconValue.valueOf(iconString);
        String iconValue;
        if (value == null) {
            iconValue = "{}";
        } else {
            iconValue = valueOf(value.character);
        }

        text = text.replace(startIndex, endIndex, iconValue);
        return replaceIcons(text);
    }

    public static final void setIcon(TextView textView, IconValue value) {
        textView.setTypeface(fileStreamTypeface(textView.getContext()));
        textView.setText(valueOf(value.character));
    }

    private static final Typeface fileStreamTypeface(Context context) {
        if (typeface != null) return typeface;
        InputStream inputStream = Iconify.class.getClassLoader().getResourceAsStream("fontawesome_webfont.ttf");
        File f = new File(context.getFilesDir(), "icon_tmp");
        if (!f.exists()) {
            if (!f.mkdirs()) {
                return null;
            }
        }
        File outPath = new File(f, "tmp.raw");
        try {
            byte[] buffer = new byte[inputStream.available()];
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outPath));
            int l = 0;
            while ((l = inputStream.read(buffer)) > 0) {
                bos.write(buffer, 0, l);
            }
            bos.close();
            typeface = Typeface.createFromFile(outPath);
            outPath.delete();
        } catch (IOException e) {
            return null;
        }

        return typeface;
    }

    public static enum IconValue {
        icon_glass('\uf000'), icon_music('\uf001'), icon_search('\uf002'), icon_envelope('\uf003'),
        icon_heart('\uf004'), icon_star('\uf005'), icon_star_empty('\uf006'), icon_user('\uf007'),
        icon_film('\uf008'), icon_th_large('\uf009'), icon_th('\uf00a'), icon_th_list('\uf00b'),
        icon_ok('\uf00c'), icon_remove('\uf00d'), icon_zoom_in('\uf00e'), icon_zoom_out('\uf010'),
        icon_off('\uf011'), icon_signal('\uf012'), icon_cog('\uf013'), icon_trash('\uf014'),
        icon_home('\uf015'), icon_file('\uf016'), icon_time('\uf017'), icon_road('\uf018'),
        icon_download_alt('\uf019'), icon_download('\uf01a'), icon_upload('\uf01b'), icon_inbox('\uf01c'),
        icon_play_circle('\uf01d'), icon_repeat('\uf01e'), icon_refresh('\uf021'), icon_list_alt('\uf022'),
        icon_lock('\uf023'), icon_flag('\uf024'), icon_headphones('\uf025'), icon_volume_off('\uf026'),
        icon_volume_down('\uf027'), icon_volume_up('\uf028'), icon_qrcode('\uf029'), icon_barcode('\uf02a'),
        icon_tag('\uf02b'), icon_tags('\uf02c'), icon_book('\uf02d'), icon_bookmark('\uf02e'),
        icon_print('\uf02f'), icon_camera('\uf030'), icon_font('\uf031'), icon_bold('\uf032'),
        icon_italic('\uf033'), icon_text_height('\uf034'), icon_text_width('\uf035'), icon_align_left('\uf036'),
        icon_align_center('\uf037'), icon_align_right('\uf038'), icon_align_justify('\uf039'), icon_list('\uf03a'),
        icon_indent_left('\uf03b'), icon_indent_right('\uf03c'), icon_facetime_video('\uf03d'), icon_picture('\uf03e'),
        icon_pencil('\uf040'), icon_map_marker('\uf041'), icon_adjust('\uf042'), icon_tint('\uf043'),
        icon_edit('\uf044'), icon_share('\uf045'), icon_check('\uf046'), icon_move('\uf047'),
        icon_step_backward('\uf048'), icon_fast_backward('\uf049'), icon_backward('\uf04a'), icon_play('\uf04b'),
        icon_pause('\uf04c'), icon_stop('\uf04d'), icon_forward('\uf04e'), icon_fast_forward('\uf050'),
        icon_step_forward('\uf051'), icon_eject('\uf052'), icon_chevron_left('\uf053'), icon_chevron_right('\uf054'),
        icon_plus_sign('\uf055'), icon_minus_sign('\uf056'), icon_remove_sign('\uf057'), icon_ok_sign('\uf058'),
        icon_question_sign('\uf059'), icon_info_sign('\uf05a'), icon_screenshot('\uf05b'), icon_remove_circle('\uf05c'),
        icon_ok_circle('\uf05d'), icon_ban_circle('\uf05e'), icon_arrow_left('\uf060'), icon_arrow_right('\uf061'),
        icon_arrow_up('\uf062'), icon_arrow_down('\uf063'), icon_share_alt('\uf064'), icon_resize_full('\uf065'),
        icon_resize_small('\uf066'), icon_plus('\uf067'), icon_minus('\uf068'), icon_asterisk('\uf069'),
        icon_exclamation_sign('\uf06a'), icon_gift('\uf06b'), icon_leaf('\uf06c'), icon_fire('\uf06d'),
        icon_eye_open('\uf06e'), icon_eye_close('\uf070'), icon_warning_sign('\uf071'), icon_plane('\uf072'),
        icon_calendar('\uf073'), icon_random('\uf074'), icon_comment('\uf075'), icon_magnet('\uf076'),
        icon_chevron_up('\uf077'), icon_chevron_down('\uf078'), icon_retweet('\uf079'), icon_shopping_cart('\uf07a'),
        icon_folder_close('\uf07b'), icon_folder_open('\uf07c'), icon_resize_vertical('\uf07d'), icon_resize_horizontal('\uf07e'),
        icon_bar_chart('\uf080'), icon_twitter_sign('\uf081'), icon_facebook_sign('\uf082'), icon_camera_retro('\uf083'),
        icon_key('\uf084'), icon_cogs('\uf085'), icon_comments('\uf086'), icon_thumbs_up('\uf087'),
        icon_thumbs_down('\uf088'), icon_star_half('\uf089'), icon_heart_empty('\uf08a'), icon_signout('\uf08b'),
        icon_linkedin_sign('\uf08c'), icon_pushpin('\uf08d'), icon_external_link('\uf08e'), icon_signin('\uf090'),
        icon_trophy('\uf091'), icon_github_sign('\uf092'), icon_upload_alt('\uf093'), icon_lemon('\uf094'),
        icon_phone('\uf095'), icon_check_empty('\uf096'), icon_bookmark_empty('\uf097'), icon_phone_sign('\uf098'),
        icon_twitter('\uf099'), icon_facebook('\uf09a'), icon_github('\uf09b'), icon_unlock('\uf09c'),
        icon_credit_card('\uf09d'), icon_rss('\uf09e'), icon_hdd('\uf0a0'), icon_bullhorn('\uf0a1'),
        icon_bell('\uf0a2'), icon_certificate('\uf0a3'), icon_hand_right('\uf0a4'), icon_hand_left('\uf0a5'),
        icon_hand_up('\uf0a6'), icon_hand_down('\uf0a7'), icon_circle_arrow_left('\uf0a8'), icon_circle_arrow_right('\uf0a9'),
        icon_circle_arrow_up('\uf0aa'), icon_circle_arrow_down('\uf0ab'), icon_globe('\uf0ac'), icon_wrench('\uf0ad'),
        icon_tasks('\uf0ae'), icon_filter('\uf0b0'), icon_briefcase('\uf0b1'), icon_fullscreen('\uf0b2'),
        icon_group('\uf0c0'), icon_link('\uf0c1'), icon_cloud('\uf0c2'), icon_beaker('\uf0c3'),
        icon_cut('\uf0c4'), icon_copy('\uf0c5'), icon_paper_clip('\uf0c6'), icon_save('\uf0c7'),
        icon_sign_blank('\uf0c8'), icon_reorder('\uf0c9'), icon_list_ul('\uf0ca'), icon_list_ol('\uf0cb'),
        icon_strikethrough('\uf0cc'), icon_underline('\uf0cd'), icon_table('\uf0ce'), icon_magic('\uf0d0'),
        icon_truck('\uf0d1'), icon_pinterest('\uf0d2'), icon_pinterest_sign('\uf0d3'), icon_google_plus_sign('\uf0d4'),
        icon_google_plus('\uf0d5'), icon_money('\uf0d6'), icon_caret_down('\uf0d7'), icon_caret_up('\uf0d8'),
        icon_caret_left('\uf0d9'), icon_caret_right('\uf0da'), icon_columns('\uf0db'), icon_sort('\uf0dc'),
        icon_sort_down('\uf0dd'), icon_sort_up('\uf0de'), icon_envelope_alt('\uf0e0'), icon_linkedin('\uf0e1'),
        icon_undo('\uf0e2'), icon_legal('\uf0e3'), icon_dashboard('\uf0e4'), icon_comment_alt('\uf0e5'),
        icon_comments_alt('\uf0e6'), icon_bolt('\uf0e7'), icon_sitemap('\uf0e8'), icon_umbrella('\uf0e9'),
        icon_paste('\uf0ea'), icon_lightbulb('\uf0eb'), icon_exchange('\uf0ec'), icon_cloud_download('\uf0ed'),
        icon_cloud_upload('\uf0ee'), icon_user_md('\uf0f0'), icon_stethoscope('\uf0f1'), icon_suitcase('\uf0f2'),
        icon_bell_alt('\uf0f3'), icon_coffee('\uf0f4'), icon_food('\uf0f5'), icon_file_alt('\uf0f6'),
        icon_building('\uf0f7'), icon_hospital('\uf0f8'), icon_ambulance('\uf0f9'), icon_medkit('\uf0fa'),
        icon_fighter_jet('\uf0fb'), icon_beer('\uf0fc'), icon_h_sign('\uf0fd'), icon_plus_sign_alt('\uf0fe'),
        icon_double_angle_left('\uf100'), icon_double_angle_right('\uf101'), icon_double_angle_up('\uf102'), icon_double_angle_down('\uf103'),
        icon_angle_left('\uf104'), icon_angle_right('\uf105'), icon_angle_up('\uf106'), icon_angle_down('\uf107'),
        icon_desktop('\uf108'), icon_laptop('\uf109'), icon_tablet('\uf10a'), icon_mobile_phone('\uf10b'),
        icon_circle_blank('\uf10c'), icon_quote_left('\uf10d'), icon_quote_right('\uf10e'), icon_spinner('\uf110'),
        icon_circle('\uf111'), icon_reply('\uf112'), icon_folder_close_alt('\uf114'), icon_folder_open_alt('\uf115'),
        icon_expand_alt('\uf116'), icon_collapse_alt('\uf117'), icon_smile('\uf118'), icon_frown('\uf119'),
        icon_meh('\uf11a'), icon_gamepad('\uf11b'), icon_keyboard('\uf11c'), icon_flag_alt('\uf11d'),
        icon_flag_checkered('\uf11e'), icon_terminal('\uf120'), icon_code('\uf121'), icon_reply_all('\uf122'),
        icon_mail_reply_all('\uf122'), icon_star_half_full('\uf123'), icon_star_half_empty('\uf123'), icon_location_arrow('\uf124'),
        icon_crop('\uf125'), icon_code_fork('\uf126'), icon_unlink('\uf127'), icon_question('\uf128'),
        icon_info('\uf129'), icon_exclamation('\uf12a'), icon_superscript('\uf12b'), icon_subscript('\uf12c'),
        icon_eraser('\uf12d'), icon_puzzle_piece('\uf12e'), icon_microphone('\uf130'), icon_microphone_off('\uf131'),
        icon_shield('\uf132'), icon_calendar_empty('\uf133'), icon_fire_extinguisher('\uf134'), icon_rocket('\uf135'),
        icon_maxcdn('\uf136'), icon_chevron_sign_left('\uf137'), icon_chevron_sign_right('\uf138'), icon_chevron_sign_up('\uf139'),
        icon_chevron_sign_down('\uf13a'), icon_html5('\uf13b'), icon_css3('\uf13c'), icon_anchor('\uf13d'),
        icon_unlock_alt('\uf13e'), icon_bullseye('\uf140'), icon_ellipsis_horizontal('\uf141'), icon_ellipsis_vertical('\uf142'),
        icon_rss_sign('\uf143'), icon_play_sign('\uf144'), icon_ticket('\uf145'), icon_minus_sign_alt('\uf146'),
        icon_check_minus('\uf147'), icon_level_up('\uf148'), icon_level_down('\uf149'), icon_check_sign('\uf14a'),
        icon_edit_sign('\uf14b'), icon_external_link_sign('\uf14c'), icon_share_sign('\uf14d');

        char character;

        IconValue(char character) {
            this.character = character;
        }
    }
}
