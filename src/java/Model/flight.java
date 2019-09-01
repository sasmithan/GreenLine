/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author SasmithaN
 */
public class flight {
    public int flight_id;
    public String flight_type,airport,terminal,departure,arrival,from_place,to_place; 

    public flight(int flight_id, String flight_type, String airport, String terminal, String departure, String arrival, String from_place, String to_place) {
        this.flight_id = flight_id;
        this.flight_type = flight_type;
        this.airport = airport;
        this.terminal = terminal;
        this.departure = departure;
        this.arrival = arrival;
        this.from_place = from_place;
        this.to_place = to_place;
    }

    public flight(String flight_type, String airport, String terminal, String departure, String arrival, String from_place, String to_place) {
        this.flight_type = flight_type;
        this.airport = airport;
        this.terminal = terminal;
        this.departure = departure;
        this.arrival = arrival;
        this.from_place = from_place;
        this.to_place = to_place;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getFlight_type() {
        return flight_type;
    }

    public void setFlight_type(String flight_type) {
        this.flight_type = flight_type;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getFrom_place() {
        return from_place;
    }

    public void setFrom_place(String from_place) {
        this.from_place = from_place;
    }

    public String getTo_place() {
        return to_place;
    }

    public void setTo_place(String to_place) {
        this.to_place = to_place;
    }
    
    
}
