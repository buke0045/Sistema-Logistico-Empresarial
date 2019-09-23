
package controllers;

// DESPLEGAR(falta), y *probar en DAO
// AGREGAR(falta), y *probar en DAO
// MODIFICAR(falta),
// ELIMINAR(falta) y *probar en DAO

// ESCOGER IDIOMA EN EL SPANGLISH XD

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
import models.Category;
import models.CategoryDAO;

@ManagedBean
@SessionScoped
public class CategoryController {
    
    private List<Category> Categs;
    private CategoryDAO CategsDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());

    public CategoryController() throws Exception {
            Categs = new ArrayList<>();

            CategsDbUtil = CategoryDAO.getInstanceC();
    }

    public List<Category> getCategories() {
            return Categs;
    }

    public void loadCategories() {

            logger.info("Loading categories");

            Categs.clear();

            try {			

                    Categs = CategsDbUtil.getCategory();

            } catch (Exception exc) {			
                    logger.log(Level.SEVERE, "Error loading categories", exc);		
                    addErrorMessage(exc);
            }
    }

    public String addCategory(Category categs) {

            logger.info("Adding category: " + categs);

            try {
                    CategsDbUtil.addCategory(categs);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error adding category", exc);			
                    addErrorMessage(exc);

                    return null;
            }

            return "AdmCategories?faces-redirect=true";
    }

    public String loadCategory(int categCode) {

            logger.info("loading category: " + categCode);

            try {
                    Category categs = CategsDbUtil.getCategory(categCode);
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
                    Map<String, Object> requestMap = externalContext.getRequestMap();
                    requestMap.put("category", categs);	

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error loading category code:" + categCode, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "update-category-form.xhtml";
    }	

    public String updateCategory(Category categ) {
            logger.info("updating category: " + categ);		
            try {			
                    CategsDbUtil.updateCategory(categ);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error updating category: " + categ, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "AdmCategories?faces-redirect=true";		
    }

    public String deleteCategory(int categCode) {

            logger.info("Deleting category code: " + categCode);

            try {
                    CategsDbUtil.deleteCategory(categCode);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error deleting category code: " + categCode, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "AdmCategories";	
    }	

    private void addErrorMessage(Exception exc) {
            FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
