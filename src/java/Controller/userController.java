/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO_user;
import Model.Database;
import Model.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SasmithaN
 */
public class userController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        try {

            if (command == null) {
                command = "LIST";
            }
            switch (command) {
                case "LIST":
                    ListUsers(request, response);
                    break;
                case "ADD":
                    AddUser(request, response);
                    break;
                case "ADD_ADMIN":
                    AddUserByAdmin(request, response);
                    break;
                case "DELETE":
                    DeleteUser(request, response);
                    break;
                case "EDIT_PAGE":
                    EditUserInfo(request, response);
                    break;
                case "PROFILE":
                    Profile(request, response);
                    break;
                
                default:
                    ListUsers(request, response);

            }

        } catch (Exception ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

    private void ListUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        DAO_user dao = new DAO_user();
        List<user> users = dao.getAllUsers();
        request.setAttribute("USER_LIST", users);
        RequestDispatcher Dispatcher = request.getRequestDispatcher("/listOfUsers.jsp");
        Dispatcher.forward(request, response);
    }
   

    private void AddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        try {
            String port = request.getParameter("passport");
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM user WHERE passport='" + port + "'");
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if (count > 0) {
                response.sendRedirect("alreadyExist.jsp");

            } else {
                DAO_user dao = new DAO_user();
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String passport = request.getParameter("passport");
                int contact = Integer.parseInt(request.getParameter("contact"));
                String dob = request.getParameter("dob");
                String password = request.getParameter("password");
                String utype = request.getParameter("utype");
                
                user newUser = new user(name, email, passport, contact, dob, password, utype);
                dao.addUser(newUser);
                response.sendRedirect("user.jsp");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
   private void AddUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        try {
            String port = request.getParameter("passport");
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM user WHERE passport='" + port + "'");
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if (count > 0) {
                response.sendRedirect("alreadyExist.jsp");

            } else {
                DAO_user dao = new DAO_user();
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String passport = request.getParameter("passport");
                int contact = Integer.parseInt(request.getParameter("contact"));
                String dob = request.getParameter("dob");
                String password = request.getParameter("password");
                String utype = request.getParameter("utype");
                
                user newUser = new user(name, email, passport, contact, dob, password, utype);
                dao.addUser(newUser);
                response.sendRedirect("adminRegister.jsp");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    private void DeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO_user dao = new DAO_user();
        String email = request.getParameter("email");
        dao.deleteUser(email);
        response.sendRedirect("index.html");

    }

    private void EditUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO_user dao = new DAO_user();
        String email = request.getParameter("email");
        user theuser = dao.getUserById(email);
        RequestDispatcher Dispatcher = request.getRequestDispatcher("/editUser.jsp");
        request.setAttribute("tempUser", theuser);
        Dispatcher.forward(request, response);
    }
    
    
    private void Profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO_user dao = new DAO_user();
        String email = request.getParameter("email");
        user theuser = dao.getUserById(email);
        RequestDispatcher Dispatcher = request.getRequestDispatcher("/viewProfile.jsp");
        request.setAttribute("tempUser", theuser);
        Dispatcher.forward(request, response);
    }

}