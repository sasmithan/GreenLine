/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO_user;
import Model.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SasmithaN
 */
public class Login extends HttpServlet {

    
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       RequestDispatcher dispatcher=request.getRequestDispatcher("/index.html");
         dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email=request.getParameter("email");
        String psd=request.getParameter("password");
        user theuser=new user(email,psd);
        
        DAO_user dao=new DAO_user();
        
        String urole=null;
        try{
            urole=dao.authenticateUser(theuser);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        switch(urole){
            case "A":
            {
                HttpSession session=request.getSession();
                session.setAttribute("email", email);
                RequestDispatcher dispatcher=request.getRequestDispatcher("/adminPAge.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "P":
            {
                HttpSession session=request.getSession();
                session.setAttribute("email", email);
                RequestDispatcher dispatcher=request.getRequestDispatcher("/passengerPAge.jsp");
                dispatcher.forward(request, response);
                break;
            }
           
           
            default:
            {
                RequestDispatcher dispatcher=request.getRequestDispatcher("/InvalidUser.html");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    

}