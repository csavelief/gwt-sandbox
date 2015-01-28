package fr.mncc.sandbox.client.assets;

import com.google.gwt.resources.client.CssResource;

/**
 * Annotate methods with @ClassName to rename a class at compile time.
 */
public interface AppCss extends PureCss {

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
