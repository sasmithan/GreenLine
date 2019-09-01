
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GreenLine</title>
    </head>
    <body>
        <%
                int flight_id = Integer.parseInt(request.getParameter("flight_id"));
                String flight_type = request.getParameter("flight_type");
                String airport = request.getParameter("airport");
                String terminal = request.getParameter("terminal");
                String departure = request.getParameter("departure");
                String arrival = request.getParameter("arrival");
                String from_place = request.getParameter("from_place");
                String to_place = request.getParameter("to_place");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenline", "root", "");
                PreparedStatement ps = con.prepareStatement("UPDATE flight SET flight_id=?,flight_type=?,airport=?,terminal=?,departure=?,arrival=?,from_place=?,to_place=? WHERE flight_id='"+ flight_id +"'");
                ps.setInt(1, flight_id);
                ps.setString(2, flight_type);
                ps.setString(3, airport);
                ps.setString(4, terminal);
                ps.setString(5, departure);
                ps.setString(6, arrival);
                ps.setString(7, from_place);
                ps.setString(8, to_place);
                ps.executeUpdate();
                response.sendRedirect("flightController");

            } catch (Exception ex) {
                out.println(ex.getMessage());
                out.println("Can not Inserted Record..");
            }
        %>
    </body>
</html>
