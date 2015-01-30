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

import fr.mncc.gwttoolbox.purecssframework.client.PureCssFramework;

/**
 * Annotate methods with @ClassName to rename a class at compile time.
 */
public interface SideMenuLayoutCss extends PureCssFramework {

    @ClassName("app-layout") String app_layout();

    @ClassName("app-menu") String app_menu();

    String content();

    @ClassName("pure-menu-heading") String pure_menu_heading();

    @ClassName("content-subhead") String content_subhead();

    @ClassName("pure-img-responsive") String pure_img_responsive();

    @ClassName("menu-link") String menu_link();

    String active();

    @ClassName("menu-item-divided") String menu_item_divided();

    @ClassName("pure-menu") String pure_menu();

    String header();

    @ClassName("pure-menu-selected") String pure_menu_selected();
}
