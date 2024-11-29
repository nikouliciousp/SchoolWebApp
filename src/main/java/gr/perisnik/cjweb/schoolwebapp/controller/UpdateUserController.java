package gr.perisnik.cjweb.schoolwebapp.controller;

import gr.perisnik.cjweb.schoolwebapp.dao.exceptions.UserNotFoundException;
import gr.perisnik.cjweb.schoolwebapp.dto.UserDTO;
import gr.perisnik.cjweb.schoolwebapp.service.IUserService;
import gr.perisnik.cjweb.schoolwebapp.service.UserServiceImpl;
import gr.perisnik.cjweb.schoolwebapp.dao.UserDAOImpl;
import gr.perisnik.cjweb.schoolwebapp.service.exceptions.UserServiceException;
import gr.perisnik.cjweb.schoolwebapp.validation.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to update a user.
 *
 * @version 1.0
 * @author Peris Nik
 */
@WebServlet(name = "UpdateUserController", value = "/secure/updateUser")
public class UpdateUserController extends HttpServlet {

    private IUserService userService;
    private UserValidator userValidator;

    @Override
    public void init() throws ServletException {
        // Initialize the UserService and UserValidator
        this.userService = new UserServiceImpl(new UserDAOImpl());
        this.userValidator = new UserValidator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        try {
            UserDTO user = userService.getUserByUsername(username).stream().findFirst().orElse(null);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/schoolapp/static/templates/updateUser.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/secure/users");
            }
        } catch (UserServiceException | UserNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/secure/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("password");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(newPassword);

        try {
            if (!userValidator.isValid(userDTO)) {
                request.setAttribute("user", userDTO); // Διατήρηση των στοιχείων του χρήστη
                request.setAttribute("message", "Invalid user data");
                request.getRequestDispatcher("/schoolapp/static/templates/updateUser.jsp").forward(request, response);
                return;
            }
            userService.updateUser(userDTO);
            request.setAttribute("user", userDTO); // Διατήρηση των στοιχείων του χρήστη
            request.setAttribute("message", "User updated successfully");
            request.getRequestDispatcher("/schoolapp/static/templates/updateUser.jsp").forward(request, response);
        } catch (UserNotFoundException e) {
            request.setAttribute("user", userDTO); // Διατήρηση των στοιχείων του χρήστη
            request.setAttribute("message", "User not found");
            request.getRequestDispatcher("/schoolapp/static/templates/updateUser.jsp").forward(request, response);
        } catch (UserServiceException e) {
            e.printStackTrace();
            request.setAttribute("user", userDTO); // Διατήρηση των στοιχείων του χρήστη
            request.setAttribute("message", "An error occurred while updating the user");
            request.getRequestDispatcher("/schoolapp/static/templates/updateUser.jsp").forward(request, response);
        }
    }
}