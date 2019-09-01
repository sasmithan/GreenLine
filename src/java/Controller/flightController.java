/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO_flight;
import Model.Database;
import Model.flight;
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
public class flightController extends HttpServlet {

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
                    ListFlight(request, response);
                    break;
                case "PASSENGER_VIEW_LIST":
                    ListFlightForPassenger(request, response);
                    break;
                case "ADD":
                    AddFlight(request, response);
                    break;
                
                case "DELETE":
                    DeleteFlight(request, response);
                    break;
                case "EDIT_PAGE":
                    EditFlightInfo(request, response);
                    break;
                
                default:
                    ListFlight(request, response);

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

    private void ListFlight(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO_flight dao = new DAO_flight();
        List<flight> fly = dao.getAllFlights();
        request.setAttribute("FLIGHT_LIST", fly);
        RequestDispatcher Dispatcher = request.getRequestDispatcher("/listOfFlight.jsp");
        Dispatcher.forward(request, response);
    }
    private void ListFlightForPassenger(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO_flight dao = new DAO_flight();
        List<flight> fly = dao.getAllFlights();
        request.setAttribute("FLIGHT_LIST", fly);
        RequestDispatcher Dispatcher = request.getRequestDispatcher("/passengerViewFlight.jsp");
        Dispatcher.forward(request, response);
    }
   

    private void AddFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        try {
                DAO_flight dao = new DAO_flight();
                String flight_type = request.getParameter("flight_type");
                String airport = request.getParameter("airport");
                String terminal = request.getParameter("terminal");
                String departure = request.getParameter("departure");
                String arrival = request.getParameter("arrival");
                String from_place = request.getParameter("from_place");
                String to_place = request.getParameter("to_place");
                
                flight fly=new flight(flight_type, airport, terminal, departure, arrival, from_place, to_place);
                dao.addFlight(fly);
                response.sendRedirect("addFlight.jsp");
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void DeleteFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO_flight dao = new DAO_flight();
        int flight_id =Integer.parseInt(request.getParameter("flight_id"));
        dao.deleteFlight(flight_id);
        response.sendRedirect("listOfFlight.html");

    }

    private void EditFlightInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO_flight dao = new DAO_flight();
        int flight_id =Integer.parseInt(request.getParameter("flight_id"));
        flight fly = dao.getFlightById(flight_id);
        RequestDispatcher Dispatcher = request.getRequestDispatcher("/editFlight.jsp");
        request.setAttribute("tempUser", fly);
        Dispatcher.forward(request, response);
    }
    
    
    

}