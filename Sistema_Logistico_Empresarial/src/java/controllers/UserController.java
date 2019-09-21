
package controllers;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.User;
import models.UserDAO;

// VER USERS EXISTENTES(acomodar a un mostrar sin afectar validacion de login),
// AGREGAR(probar),
// MODIFICAR(falta),
// ELIMINAR(falta)

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
    public String loadUser(String pUsername, String pPassword) {
		
		logger.info("loading user: " + pUsername);
		
		try {
			User theUser = loginDbUtil.getUser(pUsername, pPassword);	
			if(pUsername.equals(theUser.getUsername()) && pPassword.equals(theUser.getPassword())){
                           
                            return "principal.xhtml";
                            
                        }
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error user controller :" + pUsername, exc);
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-student-form.xhtml";
	}
    private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
