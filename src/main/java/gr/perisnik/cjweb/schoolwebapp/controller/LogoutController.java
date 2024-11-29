package gr.perisnik.cjweb.schoolwebapp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * Servlet to handle user logout.
 *
 * @version 1.0
 * @autor Peris Nik
 */
@WebServlet(name = "LogoutController", value = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Διαγραφή του cookie συνεδρίας
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath() + "/login"); // Ανακατεύθυνση στη σελίδα σύνδεσης
    }
}