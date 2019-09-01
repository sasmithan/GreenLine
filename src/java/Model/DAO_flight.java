/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SasmithaN
 */
public class DAO_flight {
     private Connection con;

    public DAO_flight() {
        con = Database.getConnection();
    }

    public List<flight> getAllFlights() {
        List<flight> fly = new ArrayList<>();
        try {

            PreparedStatement ps = con.prepareStatement("select * from flight");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int flight_id = rs.getInt("flight_id");
                String flight_type = rs.getString("flight_type");
                String airport = rs.getString("airport");
                String terminal = rs.getString("terminal");
                String departure = rs.getString("departure");
                String arrival = rs.getString("arrival");
                String from_place = rs.getString("from_place");
                String to_place = rs.getString("to_place");
                flight viewFlights = new flight(flight_id, flight_type, airport, terminal, departure, arrival, from_place, to_place);
                fly.add(viewFlights);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fly;
    }

    public flight getFlightById(int flight_id) {

        flight fly = null;

        try {
            PreparedStatement ps = con.prepareStatement("select * from flight where flight_id=?");
            ps.setInt(1, flight_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                flight_id = rs.getInt("flight_id");
                String flight_type = rs.getString("flight_type");
                String airport = rs.getString("airport");
                String terminal = rs.getString("terminal");
                String departure = rs.getString("departure");
                String arrival = rs.getString("arrival");
                String from_place = rs.getString("from_place");
                String to_place = rs.getString("to_place");
                fly=new flight(flight_id, flight_type, airport, terminal, departure, arrival, from_place, to_place);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fly;
    }
  
    public void addFlight(flight fly) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into flight(flight_type,airport,terminal,departure,arrival,from_place,to_place)" + "values(?,?,?,?,?,?,?)");
            ps.setString(1, fly.getFlight_type());
            ps.setString(2, fly.getAirport());
            ps.setString(3, fly.getTerminal());
            ps.setString(4, fly.getDeparture());
            ps.setString(5, fly.getArrival());
            ps.setString(6, fly.getFrom_place());
            ps.setString(7, fly.getTo_place());
           
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void deleteFlight(int flight_id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from flight where flight_id=?");
            ps.setInt(1, flight_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}