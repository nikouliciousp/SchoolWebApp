package gr.perisnik.cjweb.schoolwebapp.authentication;

import gr.perisnik.cjweb.schoolwebapp.authentication.AuthenticationProvider;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authentication filter for checking user authentication.
 * Redirects to the login page if the user is not authenticated.
 *
 * @version 1.0
 * @author Peris Nik
 */
//@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check for session cookie
        Cookie[] cookies = httpRequest.getCookies();
        boolean authenticated = false;

        if (cookies != null) {
            // Iterate through the cookies to find the JSESSIONID cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    HttpSession session = httpRequest.getSession(false);
                    if ((session != null) && (session.getId().equals(cookie.getValue()))) {
                        // Session is valid, user is authenticated
                        authenticated = true;
                    } else {
                        String modifiedCookie = cookie.getValue().substring(0, cookie.getValue().length() - 6);
                        assert session != null;
                        if (session.getId().equals(modifiedCookie)) {
                            authenticated = true;
                        }
                    }
                }
            }
        }
        if (authenticated) {
            // If authenticated, proceed with the request
            chain.doFilter(request, response);
        } else {
            // Redirect to login page if the user is not authenticated
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}