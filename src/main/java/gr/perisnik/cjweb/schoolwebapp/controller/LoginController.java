package gr.perisnik.cjweb.schoolwebapp.controller;

import gr.perisnik.cjweb.schoolwebapp.authentication.AuthenticationProvider;
import gr.perisnik.cjweb.schoolwebapp.dao.UserDAOImpl;
import gr.perisnik.cjweb.schoolwebapp.dto.UserDTO;
import gr.perisnik.cjweb.schoolwebapp.service.IUserService;
import gr.perisnik.cjweb.schoolwebapp.service.UserServiceImpl;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.UserServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet to handle user login.
 *
 * @version 1.0
 * @author Peris Nik
 */
@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private AuthenticationProvider authenticationProvider;

    @Override
    public void init() throws ServletException {
        // Initialize the UserService and AuthenticationProvider
        IUserService userService = new UserServiceImpl(new UserDAOImpl());
        this.authenticationProvider = AuthenticationProvider.getInstance(userService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordAdmin = System.getenv("TS_ADMIN_PASSWORD");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        try {
            if (isAdmin(username, password, passwordAdmin)) {
                authenticateAdmin(request, response, userDTO);
            } else {
                authenticateUser(request, response, userDTO);
            }
        } catch (UserServiceException e) {
            handleException(response, e, "/login");
        }
    }

    private boolean isAdmin(String username, String password, String passwordAdmin) {
        return "admin".equals(username) && password.equals(passwordAdmin);
    }

    private void authenticateAdmin(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) throws IOException {
        HttpSession session = createSession(request, userDTO.getUsername());
        addCookieToResponse(response, session);
        response.sendRedirect(request.getContextPath() + "/secure/users");
    }

    private void authenticateUser(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) throws IOException, UserServiceException {
        boolean isAuthenticated = authenticationProvider.authenticate(userDTO.getUsername(), userDTO.getPassword());
        if (isAuthenticated) {
            HttpSession session = createSession(request, userDTO.getUsername());
            addCookieToResponse(response, session);
            response.sendRedirect(request.getContextPath() + "/secure/schoolMenu");
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private HttpSession createSession(HttpServletRequest request, String username) {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        session.setMaxInactiveInterval(60 * 15);
        return session;
    }

    private void addCookieToResponse(HttpServletResponse response, HttpSession session) {
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(session.getMaxInactiveInterval());
        response.addCookie(cookie);
    }

    private void handleException(HttpServletResponse response, Exception e, String redirectPath) throws IOException {
        e.printStackTrace();
        response.sendRedirect(redirectPath);
    }
}