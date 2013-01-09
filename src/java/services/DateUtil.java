/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author goks
 */
public class DateUtil extends SimpleDateFormat{
    public DateUtil() {
        super("dd-MM-yyyy");
    }
    
}
