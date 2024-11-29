package gr.perisnik.cjweb.schoolwebapp.controller;

import gr.perisnik.cjweb.schoolwebapp.service.IUserService;
import gr.perisnik.cjweb.schoolwebapp.service.UserServiceImpl;
import gr.perisnik.cjweb.schoolwebapp.dao.UserDAOImpl;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.UserServiceException;
import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to delete a user.
 *
 * @version 1.0
 * @autor Peris Nik
 */
@WebServlet(name = "DeleteUser", value = "/secure/deleteUser")
public class DeleteUser extends HttpServlet {

    private IUserService userService;

    @Override
    public void init() throws ServletException {
        // Initialize the UserService
        this.userService = new UserServiceImpl(new UserDAOImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        try {
            userService.deleteUserByUsername(username);
            response.sendRedirect(request.getContextPath() + "/secure/users");
        } catch (UserNotFoundException e) {
            // Handle user not found exception
            response.sendRedirect(request.getContextPath() + "/secure/users");
        } catch (UserServiceException e) {
            // Handle other service exceptions
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/secure/users");
        }
    }
}