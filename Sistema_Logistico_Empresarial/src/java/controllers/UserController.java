
package controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.User;
import models.UserDAO;


@ManagedBean
@SessionScoped
public class UserController {
    
    private UserDAO loginDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    public UserController()throws Exception{
        loginDbUtil = UserDAO.getInstance();
    }
    public String addUser(User pUser){
        try {
            loginDbUtil.addUser(pUser);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding students", e);			
            addErrorMessage(e);
            return null;
        }
        return "index?faces-redirect=true";
    }
    private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
