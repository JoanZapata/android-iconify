/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * It uses FontAwesome font, licensed under OFL 1.1, which is compatible
 * with this library's license.
 *
 *     http://scripts.sil.org/cms/scripts/render_download.php?format=file&media_id=OFL_plaintext&filename=OFL.txt
 */
package com.joanzapata.android.iconify;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spanned;
import android.widget.TextView;

import java.io.IOException;

import static android.text.Html.*;
import static com.joanzapata.android.iconify.Utils.*;
import static java.lang.String.valueOf;

public final class Iconify {

    private static final String TTF_FILE = "fontawesome-webfont-4.0.3.ttf";

    public static final String TAG = Iconify.class.getSimpleName();

    private static Typeface typeface = null;

    private Iconify() {
        // Prevent instantiation
    }

    /** Transform the given TextViews replacing {icon_xxx} texts with icons. */
    public static final void addIcons(TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTypeface(getTypeface(textView.getContext()));
            textView.setText(compute(textView.getText()));
        }
    }

    public static CharSequence compute(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            String text = toHtml((Spanned) charSequence);
            return fromHtml(replaceIcons(new StringBuilder((text))).toString());
        }
        String text = charSequence.toString();
        return replaceIcons(new StringBuilder(text));
    }

    public static final void setIcon(TextView textView, IconValue value) {
        textView.setTypeface(getTypeface(textView.getContext()));
        textView.setText(valueOf(value.character));
    }

    /**
     * The typeface that contains FontAwesome icons.
     * @return the typeface, or null if something goes wrong.
     */
    public static final Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromFile(resourceToFile(context, TTF_FILE));
            } catch (IOException e) {
                return null;
            }
        }
        return typeface;
    }

    public static enum IconValue {

        fa_glass('\uf000'),
        fa_music('\uf001'),
        fa_search('\uf002'),
        fa_envelope_o('\uf003'),
        fa_heart('\uf004'),
        fa_star('\uf005'),
        fa_star_o('\uf006'),
        fa_user('\uf007'),
        fa_film('\uf008'),
        fa_th_large('\uf009'),
        fa_th('\uf00a'),
        fa_th_list('\uf00b'),
        fa_check('\uf00c'),
        fa_times('\uf00d'),
        fa_search_plus('\uf00e'),
        fa_search_minus('\uf010'),
        fa_power_off('\uf011'),
        fa_signal('\uf012'),
        fa_cog('\uf013'),
        fa_trash_o('\uf014'),
        fa_home('\uf015'),
        fa_file_o('\uf016'),
        fa_clock_o('\uf017'),
        fa_road('\uf018'),
        fa_download('\uf019'),
        fa_arrow_circle_o_down('\uf01a'),
        fa_arrow_circle_o_up('\uf01b'),
        fa_inbox('\uf01c'),
        fa_play_circle_o('\uf01d'),
        fa_repeat('\uf01e'),
        fa_refresh('\uf021'),
        fa_list_alt('\uf022'),
        fa_lock('\uf023'),
        fa_flag('\uf024'),
        fa_headphones('\uf025'),
        fa_volume_off('\uf026'),
        fa_volume_down('\uf027'),
        fa_volume_up('\uf028'),
        fa_qrcode('\uf029'),
        fa_barcode('\uf02a'),
        fa_tag('\uf02b'),
        fa_tags('\uf02c'),
        fa_book('\uf02d'),
        fa_bookmark('\uf02e'),
        fa_print('\uf02f'),
        fa_camera('\uf030'),
        fa_font('\uf031'),
        fa_bold('\uf032'),
        fa_italic('\uf033'),
        fa_text_height('\uf034'),
        fa_text_width('\uf035'),
        fa_align_left('\uf036'),
        fa_align_center('\uf037'),
        fa_align_right('\uf038'),
        fa_align_justify('\uf039'),
        fa_list('\uf03a'),
        fa_outdent('\uf03b'),
        fa_indent('\uf03c'),
        fa_video_camera('\uf03d'),
        fa_picture_o('\uf03e'),
        fa_pencil('\uf040'),
        fa_map_marker('\uf041'),
        fa_adjust('\uf042'),
        fa_tint('\uf043'),
        fa_pencil_square_o('\uf044'),
        fa_share_square_o('\uf045'),
        fa_check_square_o('\uf046'),
        fa_arrows('\uf047'),
        fa_step_backward('\uf048'),
        fa_fast_backward('\uf049'),
        fa_backward('\uf04a'),
        fa_play('\uf04b'),
        fa_pause('\uf04c'),
        fa_stop('\uf04d'),
        fa_forward('\uf04e'),
        fa_fast_forward('\uf050'),
        fa_step_forward('\uf051'),
        fa_eject('\uf052'),
        fa_chevron_left('\uf053'),
        fa_chevron_right('\uf054'),
        fa_plus_circle('\uf055'),
        fa_minus_circle('\uf056'),
        fa_times_circle('\uf057'),
        fa_check_circle('\uf058'),
        fa_question_circle('\uf059'),
        fa_info_circle('\uf05a'),
        fa_crosshairs('\uf05b'),
        fa_times_circle_o('\uf05c'),
        fa_check_circle_o('\uf05d'),
        fa_ban('\uf05e'),
        fa_arrow_left('\uf060'),
        fa_arrow_right('\uf061'),
        fa_arrow_up('\uf062'),
        fa_arrow_down('\uf063'),
        fa_share('\uf064'),
        fa_expand('\uf065'),
        fa_compress('\uf066'),
        fa_plus('\uf067'),
        fa_minus('\uf068'),
        fa_asterisk('\uf069'),
        fa_exclamation_circle('\uf06a'),
        fa_gift('\uf06b'),
        fa_leaf('\uf06c'),
        fa_fire('\uf06d'),
        fa_eye('\uf06e'),
        fa_eye_slash('\uf070'),
        fa_exclamation_triangle('\uf071'),
        fa_plane('\uf072'),
        fa_calendar('\uf073'),
        fa_random('\uf074'),
        fa_comment('\uf075'),
        fa_magnet('\uf076'),
        fa_chevron_up('\uf077'),
        fa_chevron_down('\uf078'),
        fa_retweet('\uf079'),
        fa_shopping_cart('\uf07a'),
        fa_folder('\uf07b'),
        fa_folder_open('\uf07c'),
        fa_arrows_v('\uf07d'),
        fa_arrows_h('\uf07e'),
        fa_bar_chart_o('\uf080'),
        fa_twitter_square('\uf081'),
        fa_facebook_square('\uf082'),
        fa_camera_retro('\uf083'),
        fa_key('\uf084'),
        fa_cogs('\uf085'),
        fa_comments('\uf086'),
        fa_thumbs_o_up('\uf087'),
        fa_thumbs_o_down('\uf088'),
        fa_star_half('\uf089'),
        fa_heart_o('\uf08a'),
        fa_sign_out('\uf08b'),
        fa_linkedin_square('\uf08c'),
        fa_thumb_tack('\uf08d'),
        fa_external_link('\uf08e'),
        fa_sign_in('\uf090'),
        fa_trophy('\uf091'),
        fa_github_square('\uf092'),
        fa_upload('\uf093'),
        fa_lemon_o('\uf094'),
        fa_phone('\uf095'),
        fa_square_o('\uf096'),
        fa_bookmark_o('\uf097'),
        fa_phone_square('\uf098'),
        fa_twitter('\uf099'),
        fa_facebook('\uf09a'),
        fa_github('\uf09b'),
        fa_unlock('\uf09c'),
        fa_credit_card('\uf09d'),
        fa_rss('\uf09e'),
        fa_hdd_o('\uf0a0'),
        fa_bullhorn('\uf0a1'),
        fa_bell('\uf0f3'),
        fa_certificate('\uf0a3'),
        fa_hand_o_right('\uf0a4'),
        fa_hand_o_left('\uf0a5'),
        fa_hand_o_up('\uf0a6'),
        fa_hand_o_down('\uf0a7'),
        fa_arrow_circle_left('\uf0a8'),
        fa_arrow_circle_right('\uf0a9'),
        fa_arrow_circle_up('\uf0aa'),
        fa_arrow_circle_down('\uf0ab'),
        fa_globe('\uf0ac'),
        fa_wrench('\uf0ad'),
        fa_tasks('\uf0ae'),
        fa_filter('\uf0b0'),
        fa_briefcase('\uf0b1'),
        fa_arrows_alt('\uf0b2'),
        fa_users('\uf0c0'),
        fa_link('\uf0c1'),
        fa_cloud('\uf0c2'),
        fa_flask('\uf0c3'),
        fa_scissors('\uf0c4'),
        fa_files_o('\uf0c5'),
        fa_paperclip('\uf0c6'),
        fa_floppy_o('\uf0c7'),
        fa_square('\uf0c8'),
        fa_bars('\uf0c9'),
        fa_list_ul('\uf0ca'),
        fa_list_ol('\uf0cb'),
        fa_strikethrough('\uf0cc'),
        fa_underline('\uf0cd'),
        fa_table('\uf0ce'),
        fa_magic('\uf0d0'),
        fa_truck('\uf0d1'),
        fa_pinterest('\uf0d2'),
        fa_pinterest_square('\uf0d3'),
        fa_google_plus_square('\uf0d4'),
        fa_google_plus('\uf0d5'),
        fa_money('\uf0d6'),
        fa_caret_down('\uf0d7'),
        fa_caret_up('\uf0d8'),
        fa_caret_left('\uf0d9'),
        fa_caret_right('\uf0da'),
        fa_columns('\uf0db'),
        fa_sort('\uf0dc'),
        fa_sort_asc('\uf0dd'),
        fa_sort_desc('\uf0de'),
        fa_envelope('\uf0e0'),
        fa_linkedin('\uf0e1'),
        fa_undo('\uf0e2'),
        fa_gavel('\uf0e3'),
        fa_tachometer('\uf0e4'),
        fa_comment_o('\uf0e5'),
        fa_comments_o('\uf0e6'),
        fa_bolt('\uf0e7'),
        fa_sitemap('\uf0e8'),
        fa_umbrella('\uf0e9'),
        fa_clipboard('\uf0ea'),
        fa_lightbulb_o('\uf0eb'),
        fa_exchange('\uf0ec'),
        fa_cloud_download('\uf0ed'),
        fa_cloud_upload('\uf0ee'),
        fa_user_md('\uf0f0'),
        fa_stethoscope('\uf0f1'),
        fa_suitcase('\uf0f2'),
        fa_bell_o('\uf0a2'),
        fa_coffee('\uf0f4'),
        fa_cutlery('\uf0f5'),
        fa_file_text_o('\uf0f6'),
        fa_building_o('\uf0f7'),
        fa_hospital_o('\uf0f8'),
        fa_ambulance('\uf0f9'),
        fa_medkit('\uf0fa'),
        fa_fighter_jet('\uf0fb'),
        fa_beer('\uf0fc'),
        fa_h_square('\uf0fd'),
        fa_plus_square('\uf0fe'),
        fa_angle_double_left('\uf100'),
        fa_angle_double_right('\uf101'),
        fa_angle_double_up('\uf102'),
        fa_angle_double_down('\uf103'),
        fa_angle_left('\uf104'),
        fa_angle_right('\uf105'),
        fa_angle_up('\uf106'),
        fa_angle_down('\uf107'),
        fa_desktop('\uf108'),
        fa_laptop('\uf109'),
        fa_tablet('\uf10a'),
        fa_mobile('\uf10b'),
        fa_circle_o('\uf10c'),
        fa_quote_left('\uf10d'),
        fa_quote_right('\uf10e'),
        fa_spinner('\uf110'),
        fa_circle('\uf111'),
        fa_reply('\uf112'),
        fa_github_alt('\uf113'),
        fa_folder_o('\uf114'),
        fa_folder_open_o('\uf115'),
        fa_smile_o('\uf118'),
        fa_frown_o('\uf119'),
        fa_meh_o('\uf11a'),
        fa_gamepad('\uf11b'),
        fa_keyboard_o('\uf11c'),
        fa_flag_o('\uf11d'),
        fa_flag_checkered('\uf11e'),
        fa_terminal('\uf120'),
        fa_code('\uf121'),
        fa_reply_all('\uf122'),
        fa_mail_reply_all('\uf122'),
        fa_star_half_o('\uf123'),
        fa_location_arrow('\uf124'),
        fa_crop('\uf125'),
        fa_code_fork('\uf126'),
        fa_chain_broken('\uf127'),
        fa_question('\uf128'),
        fa_info('\uf129'),
        fa_exclamation('\uf12a'),
        fa_superscript('\uf12b'),
        fa_subscript('\uf12c'),
        fa_eraser('\uf12d'),
        fa_puzzle_piece('\uf12e'),
        fa_microphone('\uf130'),
        fa_microphone_slash('\uf131'),
        fa_shield('\uf132'),
        fa_calendar_o('\uf133'),
        fa_fire_extinguisher('\uf134'),
        fa_rocket('\uf135'),
        fa_maxcdn('\uf136'),
        fa_chevron_circle_left('\uf137'),
        fa_chevron_circle_right('\uf138'),
        fa_chevron_circle_up('\uf139'),
        fa_chevron_circle_down('\uf13a'),
        fa_html5('\uf13b'),
        fa_css3('\uf13c'),
        fa_anchor('\uf13d'),
        fa_unlock_alt('\uf13e'),
        fa_bullseye('\uf140'),
        fa_ellipsis_h('\uf141'),
        fa_ellipsis_v('\uf142'),
        fa_rss_square('\uf143'),
        fa_play_circle('\uf144'),
        fa_ticket('\uf145'),
        fa_minus_square('\uf146'),
        fa_minus_square_o('\uf147'),
        fa_level_up('\uf148'),
        fa_level_down('\uf149'),
        fa_check_square('\uf14a'),
        fa_pencil_square('\uf14b'),
        fa_external_link_square('\uf14c'),
        fa_share_square('\uf14d'),
        fa_compass('\uf14e'),
        fa_caret_square_o_down('\uf150'),
        fa_caret_square_o_up('\uf151'),
        fa_caret_square_o_right('\uf152'),
        fa_eur('\uf153'),
        fa_gbp('\uf154'),
        fa_usd('\uf155'),
        fa_inr('\uf156'),
        fa_jpy('\uf157'),
        fa_rub('\uf158'),
        fa_krw('\uf159'),
        fa_btc('\uf15a'),
        fa_file('\uf15b'),
        fa_file_text('\uf15c'),
        fa_sort_alpha_asc('\uf15d'),
        fa_sort_alpha_desc('\uf15e'),
        fa_sort_amount_asc('\uf160'),
        fa_sort_amount_desc('\uf161'),
        fa_sort_numeric_asc('\uf162'),
        fa_sort_numeric_desc('\uf163'),
        fa_thumbs_up('\uf164'),
        fa_thumbs_down('\uf165'),
        fa_youtube_square('\uf166'),
        fa_youtube('\uf167'),
        fa_xing('\uf168'),
        fa_xing_square('\uf169'),
        fa_youtube_play('\uf16a'),
        fa_dropbox('\uf16b'),
        fa_stack_overflow('\uf16c'),
        fa_instagram('\uf16d'),
        fa_flickr('\uf16e'),
        fa_adn('\uf170'),
        fa_bitbucket('\uf171'),
        fa_bitbucket_square('\uf172'),
        fa_tumblr('\uf173'),
        fa_tumblr_square('\uf174'),
        fa_long_arrow_down('\uf175'),
        fa_long_arrow_up('\uf176'),
        fa_long_arrow_left('\uf177'),
        fa_long_arrow_right('\uf178'),
        fa_apple('\uf179'),
        fa_windows('\uf17a'),
        fa_android('\uf17b'),
        fa_linux('\uf17c'),
        fa_dribbble('\uf17d'),
        fa_skype('\uf17e'),
        fa_foursquare('\uf180'),
        fa_trello('\uf181'),
        fa_female('\uf182'),
        fa_male('\uf183'),
        fa_gittip('\uf184'),
        fa_sun_o('\uf185'),
        fa_moon_o('\uf186'),
        fa_archive('\uf187'),
        fa_bug('\uf188'),
        fa_vk('\uf189'),
        fa_weibo('\uf18a'),
        fa_renren('\uf18b'),
        fa_pagelines('\uf18c'),
        fa_stack_exchange('\uf18d'),
        fa_arrow_circle_o_right('\uf18e'),
        fa_arrow_circle_o_left('\uf190'),
        fa_caret_square_o_left('\uf191'),
        fa_dot_circle_o('\uf192'),
        fa_wheelchair('\uf193'),
        fa_vimeo_square('\uf194'),
        fa_try('\uf195'),
        fa_plus_square_o('\uf196');

        char character;

        IconValue(char character) {
            this.character = character;
        }

        public String formattedName() {
            return "{" + name() + "}";
        }

        public char character() {
            return character;
        }
    }
}
