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
public class DAO_user {
     private Connection con;

    public DAO_user() {
        con = Database.getConnection();
    }

    public List<user> getAllUsers() {
        List<user> users = new ArrayList<>();
        try {

            PreparedStatement ps = con.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String passport = rs.getString("passport");
                int contact = rs.getInt("contact");
                String dob = rs.getString("dob");
                String password = rs.getString("password");
                String utype = rs.getString("utype");
                user myUser = new user(name, email, passport, contact, dob, password, utype);
                users.add(myUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public user getUserById(String email) {

        user users = null;

        try {
            PreparedStatement ps = con.prepareStatement("select * from user where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               String name = rs.getString("name");
                email = rs.getString("email");
                String passport = rs.getString("passport");
                int contact = rs.getInt("contact");
                String dob = rs.getString("dob");
                String password = rs.getString("password");
                String utype = rs.getString("utype");
                users = new user(name, email, passport, contact, dob, password, utype);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
   
    
    public String authenticateUser(user user) throws SQLException{
        String email=user.getEmail();
        String password=user.getPassword();
        try{
            PreparedStatement ps=con.prepareStatement("select email,password,utype,name from user");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                String Email=rs.getString("email");
                String Psd=rs.getString("password");
                String utype=rs.getString("utype");
                
                if(email.equals(Email) && password.equals(Psd) && utype.equals("P"))
                    return "P";
                else if(email.equals(Email) && password.equals(Psd) && utype.equals("A"))
                    return "A";
            }
        }
        catch(Exception e){
        e.printStackTrace();
        }
        return "Invalid";
    
    }
     
    
    public void addUser(user users) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into user(name,email,passport,contact,dob,password,utype)" + "values(?,?,?,?,?,?,?)");
            ps.setString(1, users.getName());
            ps.setString(2, users.getEmail());
            ps.setString(3, users.getPassport());
            ps.setInt(4, users.getContact());
            ps.setString(5, users.getDob());
            ps.setString(6, users.getPassword());
            ps.setString(7, users.getUtype());
           
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void deleteUser(String port) {
//do more stuff
        try {
            PreparedStatement ps = con.prepareStatement("delete from user where passport=?");
            ps.setString(1, port);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
