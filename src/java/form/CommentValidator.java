/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.UsersDAO;
import exceptions.FriendshipException;
import form.CommentForm;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import services.UserService;

/**
 *
 * @author goks
 */
public class CommentValidator implements Validator {
    
    private User sender;

    public boolean supports(Class<?> clazz) {
        return CommentForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destiny", null);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", null);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "comment.body.required");
        
        CommentForm form = (CommentForm) o;
        String type = form.getType(); 
        UsersDAO dao = new UsersDAO();
        User dest = dao.findById(form.getDestiny());
        UserService serv = new UserService();
        
        try {
            if(!serv.areFriends(this.sender, dest)) {
                errors.rejectValue("destiny", "comment.destiny.accessdenied");
            }
           
        } catch (FriendshipException ex) { // misma persona
            if(!type.equals("wall")) {
                errors.rejectValue("type", "comment.type.invalid");
            }
        
        }
        
        
    }
    
    
    public void setSender(User sender) {
        this.sender = sender;
    }
}
