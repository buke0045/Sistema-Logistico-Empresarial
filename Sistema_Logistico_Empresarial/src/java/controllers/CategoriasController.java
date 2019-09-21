
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
import models.Categorias;
import models.CategoriasDAO;

@ManagedBean
@SessionScoped
public class CategoriasController {
    
    private List<Categorias> Categs;
    private CategoriasDAO CategsDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());

    public CategoriasController() throws Exception {
            Categs = new ArrayList<>();

            CategsDbUtil = CategoriasDAO.getInstanceC();
    }

    public List<Categorias> getCategories() {
            return Categs;
    }

    public void loadCategories() {

            logger.info("Loading categories");

            Categs.clear();

            try {			

                    Categs = CategsDbUtil.getCategorias();

            } catch (Exception exc) {			
                    logger.log(Level.SEVERE, "Error loading categories", exc);		
                    addErrorMessage(exc);
            }
    }

    public String addCategory(Categorias categs) {

            logger.info("Adding categories: " + categs);

            try {
                    CategsDbUtil.addCategoria(categs);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error adding categories", exc);			
                    addErrorMessage(exc);

                    return null;
            }

            return "list-categories?faces-redirect=true";
    }

    public String loadCategory(int categCodigo) {

            logger.info("loading category: " + categCodigo);

            try {
                    Categorias categs = CategsDbUtil.getCategory(categCodigo);
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
                    Map<String, Object> requestMap = externalContext.getRequestMap();
                    requestMap.put("categorias", categs);	

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error loading category codigo:" + categCodigo, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "update-category-form.xhtml";
    }	

    public String updateCategory(Categorias categ) {
            logger.info("updating categoria: " + categ);		
            try {			
                    CategsDbUtil.updateCategoria(categ);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error updating category: " + categ, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "list-categories?faces-redirect=true";		
    }

    public String deleteCategory(int categCodigo) {

            logger.info("Deleting category code: " + categCodigo);

            try {
                    CategsDbUtil.deleteCategoria(categCodigo);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error deleting category code: " + categCodigo, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "list-categories";	
    }	

    private void addErrorMessage(Exception exc) {
            FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}