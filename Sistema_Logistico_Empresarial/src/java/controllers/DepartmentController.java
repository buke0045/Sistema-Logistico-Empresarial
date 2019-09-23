
package controllers;

// VISUALIZAR TODOS(falta),
// AGREGAR(falta),
// MODIFICAR(falta),
// ELIMINAR(falta)

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
import models.Department;
import models.DepartmentDAO;

@ManagedBean
@SessionScoped
public class DepartmentController {
    
    private List<Department> Deps;
    private DepartmentDAO DepsDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());

    public DepartmentController() throws Exception {
            Deps = new ArrayList<>();

            DepsDbUtil = DepartmentDAO.getInstanceD();
    }

    public List<Department> getDepartments() {
            return Deps;
    }

    public void loadDepartments() {

            logger.info("Loading departments");

            Deps.clear();

            try {			

                    Deps = DepsDbUtil.getDepartment();

            } catch (Exception exc) {			
                    logger.log(Level.SEVERE, "Error loading departments", exc);		
                    addErrorMessage(exc);
            }
    }

    public String addDepartment(Department Deps) {

            logger.info("Adding department: " + Deps);

            try {
                    DepsDbUtil.addDepartment(Deps);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error adding department", exc);			
                    addErrorMessage(exc);

                    return null;
            }

            return "AdmDepartments?faces-redirect=true";
    }

    public String loadDepartment(int depCode) {

            logger.info("loading department: " + depCode);

            try {
                    Department deps = DepsDbUtil.getDepartment(depCode);
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
                    Map<String, Object> requestMap = externalContext.getRequestMap();
                    requestMap.put("department", deps);	

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error loading department code:" + depCode, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "update-department-form.xhtml";
    }	

    public String updateDepartment(Department dep) {
            logger.info("updating department: " + dep);		
            try {			
                    DepsDbUtil.updateDepartment(dep);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error updating department: " + dep, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "AdmDepartments?faces-redirect=true";		
    }

    public String deleteDepartment(int depCode) {

            logger.info("Deleting department code: " + depCode);

            try {
                    DepsDbUtil.deleteDepartment(depCode);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error deleting department code: " + depCode, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "AdmDepartments";	
    }	

    private void addErrorMessage(Exception exc) {
            FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
