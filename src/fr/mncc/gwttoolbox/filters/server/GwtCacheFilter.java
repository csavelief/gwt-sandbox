package fr.mncc.gwttoolbox.filters.server;

import com.samaxes.filter.CacheFilter;
import com.samaxes.filter.util.HTTPCacheHeader;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * <p>Ensure that resources that contain .cache. in their file name ARE cached.</p>
 *
 * <p>Ensure that resources that contain .nocache. in their file name ARE NOT cached.</p>
 *
 * <p>Declare the filter in your web descriptor file {@code web.xml}:</p>
 *
 * <pre>
 * &lt;filter&gt;
 *     &lt;filter-name&gt;GwtCacheFilter&lt;/filter-name&gt;
 *     &lt;filter-class&gt;fr.mncc.gwttoolbox.filters.server.GwtCacheFilter&lt;/filter-class&gt;
 *     &lt;init-param&gt;
 *         &lt;param-name&gt;expiration&lt;/param-name&gt;
 *         &lt;param-value&gt;2592000&lt;/param-value&gt;
 *     &lt;/init-param&gt;
 * &lt;/filter&gt;
 * </pre>
 *
 * <p>Map the filter to serve your static resources:</p>
 *
 * <pre>
 * &lt;filter-mapping&gt;
 *     &lt;filter-name&gt;GwtCacheFilter&lt;/filter-name&gt;
 *     &lt;url-pattern&gt;/&lt;/url-pattern&gt;
 * &lt;/filter-mapping&gt;
 * </pre>
 */
public class GwtCacheFilter extends CacheFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        final String requestedURI = httpServletRequest.getRequestURI();

        if (requestedURI.contains(".cache.")) {
            super.doFilter(servletRequest, servletResponse, filterChain);
        }
        else if (requestedURI.contains(".nocache.")) {

            final Date now = new Date();

            // Set create date to current timestamp
            httpServletResponse.setDateHeader(HTTPCacheHeader.DATE.getName(), now.getTime());

            // Set modify date to current timestamp
            httpServletResponse.setDateHeader(HTTPCacheHeader.LAST_MODIFIED.getName(), now.getTime());

            // Set expiry to back in the past (makes us a bad candidate for caching)
            httpServletResponse.setDateHeader(HTTPCacheHeader.EXPIRES.getName(), 0);

            // HTTP 1.0 (disable caching)
            httpServletResponse.setHeader(HTTPCacheHeader.PRAGMA.getName(), "no-cache");

            // HTTP 1.1 (disable caching of any kind)
            // HTTP 1.1 'pre-check=0, post-check=0' => (Internet Explorer should always check)
            // Note: no-store is not included here as it will disable offline application storage on Firefox
            httpServletResponse.setHeader(HTTPCacheHeader.CACHE_CONTROL.getName(), "no-cache, must-revalidate, pre-check=0, post-check=0");

            filterChain.doFilter(servletRequest, httpServletResponse);
        }
        else {
            filterChain.doFilter(servletRequest, httpServletResponse);
        }
    }
}
