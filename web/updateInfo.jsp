
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
            String id = request.getParameter("email");
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            String password = request.getParameter("password");
            int contact = Integer.parseInt(request.getParameter("contact"));

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenline", "root", "");
                PreparedStatement ps = con.prepareStatement("UPDATE user SET email=?,name=?,contact=?,dob=?,password=? WHERE email='"+ id +"'");
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setInt(3, contact);
                ps.setString(4, dob);
                ps.setString(5, password);
                ps.executeUpdate();
                response.sendRedirect("index.html");

            } catch (Exception ex) {
                out.println(ex.getMessage());
                out.println("Can not Inserted Record..");
            }
        %>
    </body>
</html>
