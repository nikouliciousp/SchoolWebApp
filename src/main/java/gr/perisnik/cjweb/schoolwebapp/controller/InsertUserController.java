package gr.perisnik.cjweb.schoolwebapp.controller;

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
 * Servlet to insert a new user.
 *
 * @version 1.0
 * @author Peris Nik
 */
@WebServlet(name = "InsertUserController", value = "/secure/insertUser")
public class InsertUserController extends HttpServlet {

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
        request.getRequestDispatcher("/schoolapp/static/templates/insertUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        try {
            if (!userValidator.isValid(userDTO)) {
                request.setAttribute("user", userDTO);
                request.setAttribute("message", "Invalid user data");
                request.getRequestDispatcher("/schoolapp/static/templates/insertUser.jsp").forward(request, response);
                return;
            }
            userService.addUser(userDTO);
            request.setAttribute("message", "User inserted successfully");
            request.getRequestDispatcher("/schoolapp/static/templates/insertUser.jsp").forward(request, response);
        } catch (UserServiceException e) {
            e.printStackTrace();
            request.setAttribute("user", userDTO);
            request.setAttribute("message", "An error occurred while inserting the user");
            request.getRequestDispatcher("/schoolapp/static/templates/insertUser.jsp").forward(request, response);
        }
    }
}