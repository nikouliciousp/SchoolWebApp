package gr.perisnik.cjweb.schoolwebapp.controller;

import gr.perisnik.cjweb.schoolwebapp.dto.UserDTO;
import gr.perisnik.cjweb.schoolwebapp.service.IUserService;
import gr.perisnik.cjweb.schoolwebapp.service.UserServiceImpl;
import gr.perisnik.cjweb.schoolwebapp.dao.UserDAOImpl;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.UserServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to handle displaying users.
 *
 * @version 1.0
 * @autor Peris Nik
 */
@WebServlet(name = "UserController", value = "/secure/users")
public class UsersController extends HttpServlet {

    private IUserService userService;

    @Override
    public void init() throws ServletException {
        // Initialize the UserService
        this.userService = new UserServiceImpl(new UserDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve all users from the UserService
            List<UserDTO> users = userService.getAllUsers();
            // Set users as a request attribute
            request.setAttribute("users", users);
            // Forward the request to the users JSP
            request.getRequestDispatcher("/schoolapp/static/templates/users.jsp").forward(request, response);
        } catch (UserServiceException e) {
            // Handle the exception (log it, show error message, etc.)
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}