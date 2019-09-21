
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

            CategsDbUtil = CategoriasDAO.getInstance(); // ERROR-> FALTA TERMINAR DE DEFINIR SU DAO
    }

    public List<Categorias> getStudents() {
            return Categs;
    }

    public void loadStudents() {

            logger.info("Loading students");

            Categs.clear();

            try {			

                    Categs = CategsDbUtil.getCategorias();

            } catch (Exception exc) {			
                    logger.log(Level.SEVERE, "Error loading students", exc);		
                    addErrorMessage(exc);
            }
    }

    public String addStudent(Categorias categs) {

            logger.info("Adding student: " + categs);

            try {
                    CategsDbUtil.addCategoria(categs);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error adding students", exc);			
                    addErrorMessage(exc);

                    return null;
            }

            return "list-students?faces-redirect=true";
    }

    public String loadCategory(int categCodigo) {

            logger.info("loading category: " + categCodigo);

            try {
                    Categorias categs = CategsDbUtil.getCategorias(categCodigo); // ERROR-> FALTA TERMINAR DE DEFINIR SU DAO
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
                    Map<String, Object> requestMap = externalContext.getRequestMap();
                    requestMap.put("student", categs);	

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error loading student id:" + categCodigo, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "update-student-form.xhtml";
    }	

    public String updateStudent(Categorias categ) {
            logger.info("updating student: " + categ);		
            try {			
                    CategsDbUtil.updateCategoria(categ);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error updating category: " + categ, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "list-students?faces-redirect=true";		
    }

    public String deleteCategoria(int categCodigo) {

            logger.info("Deleting categorias codigo: " + categCodigo);

            try {
                    CategsDbUtil.deleteCategoria(categCodigo);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error deleting category code: " + categCodigo, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "list-students";	
    }	

    private void addErrorMessage(Exception exc) {
            FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
