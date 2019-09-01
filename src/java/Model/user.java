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
public class user {
    public String name,email,passport;
    public int contact;
    public String dob,password,utype;

    public user(String name, String email, String passport, int contact, String dob, String password, String utype) {
        this.name = name;
        this.email = email;
        this.passport = passport;
        this.contact = contact;
        this.dob = dob;
        this.password = password;
        this.utype = utype;
    }

    public user(String email, String password) {
        this.email = email;
        this.password = password;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }
    
    
}
