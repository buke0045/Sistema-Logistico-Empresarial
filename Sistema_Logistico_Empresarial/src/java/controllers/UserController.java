
package controllers;

import java.util.ArrayList;
import java.util.List;
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
// AGREGAR(probar), h
// MODIFICAR(falta),
// ELIMINAR(falta)

@ManagedBean
@SessionScoped
public class UserController {
    
    private List<User> Users;
    private UserDAO loginDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    public UserController()throws Exception{
        Users = new ArrayList<>();
        loginDbUtil = UserDAO.getInstance();
    }
    public List<User> getUsers(){
        return Users;
    }
    public void loadUsers(){
        logger.info("Loading users");
        Users.clear();
        try {
            Users = loginDbUtil.getUsers();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading users", e);
            addErrorMessage(e);
        }
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
			
	return null;
    }
    public String updateUser(User pUser) {
            logger.info("updating User: " + pUser);		
            try {			
                    loginDbUtil.updateUser(pUser);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error updating User: " + pUser, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "AdmUsuario?faces-redirect=true";		
    }
    private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
