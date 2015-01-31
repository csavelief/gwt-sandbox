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

import fr.mncc.gwttoolbox.purecssframework.client.PureCssResponsiveFramework;

/**
 * Annotate methods with @ClassName to rename a class at compile time.
 */
public interface BlogLayoutCssResource extends PureCssResponsiveFramework {

    @ClassName("post-category") String post_category();

    @ClassName("post-category-pure") String post_category_pure();

    @ClassName("content-subhead") String content_subhead();

    @ClassName("post-title") String post_title();

    @ClassName("nav-list") String nav_list();

    String post();

    @ClassName("post-image-meta") String post_image_meta();

    @ClassName("post-category-design") String post_category_design();

    @ClassName("post-meta") String post_meta();

    String sidebar();

    @ClassName("nav-item") String nav_item();

    @ClassName("post-description") String post_description();

    @ClassName("brand-tagline") String brand_tagline();

    String header();

    String content();

    @ClassName("post-category-js") String post_category_js();

    String footer();

    @ClassName("post-category-yui") String post_category_yui();

    @ClassName("pure-img-responsive") String pure_img_responsive();

    String layout();

    @ClassName("post-avatar") String post_avatar();

    @ClassName("post-images") String post_images();

    @ClassName("brand-title") String brand_title();
}
