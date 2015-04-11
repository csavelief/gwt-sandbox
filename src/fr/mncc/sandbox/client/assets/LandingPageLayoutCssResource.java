/**
 * Copyright (c) 2014 MNCC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author http://www.mncc.fr
 */
package fr.mncc.sandbox.client.assets;

import fr.mncc.minus.purecss.client.PureCssResponsive;

/**
 * Annotate methods with @ClassName to rename a class at compile time.
 */
public interface LandingPageLayoutCssResource extends PureCssResponsive {

    @ClassName("content-head-ribbon") String content_head_ribbon();

    @ClassName("content-subhead") String content_subhead();

    @ClassName("l-box") String l_box();

    String splash();

    @ClassName("content-wrapper") String content_wrapper();

    @ClassName("is-center") String is_center();

    @ClassName("splash-container") String splash_container();

    String content();

    String footer();

    @ClassName("pure-img-responsive") String pure_img_responsive();

    @ClassName("splash-head") String splash_head();

    @ClassName("splash-subhead") String splash_subhead();

    String ribbon();

    @ClassName("home-menu") String home_menu();

    @ClassName("content-head") String content_head();

    @ClassName("l-box-lrg") String l_box_lrg();

    @ClassName("pure-form") String pure_form();

    @ClassName("pure-button") String pure_button();

    @ClassName("pure-button-primary") String pure_button_primary();

    @ClassName("pure-menu") String pure_menu();

    @ClassName("pure-menu-open") String pure_menu_open();

    @ClassName("pure-menu-fixed") String pure_menu_fixed();

    @ClassName("pure-menu-heading") String pure_menu_heading();

    @ClassName("pure-menu-selected") String pure_menu_selected();
}
