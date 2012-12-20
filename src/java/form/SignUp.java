/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.sun.istack.internal.NotNull;
import model.Months;

/**
 *
 * @author goks
 */
public class SignUp {

    @NotNull
    private String nombre;
    private String apellido;
    private String email;
    private String user;
    private String password;
    private String repassword;
    private boolean genero;
    private int day;
    private Months month;
    private int year;    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Months getMonth() {
        return month;
    }

    public void setMonth(Months months) {
        this.month = months;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }  
    
}
