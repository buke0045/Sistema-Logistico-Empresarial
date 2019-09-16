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
import models.Student;
import models.StudentDAO;

@ManagedBean
@SessionScoped
public class StudentController{

	private List<Student> students;
	private StudentDAO studentDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public StudentController() throws Exception {
		students = new ArrayList<>();
		
		studentDbUtil = StudentDAO.getInstance();
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void loadStudents() {

		logger.info("Loading students");
		
		students.clear();

		try {			
		
			students = studentDbUtil.getStudents();
			
		} catch (Exception exc) {			
			logger.log(Level.SEVERE, "Error loading students", exc);		
			addErrorMessage(exc);
		}
	}
		
	public String addStudent(Student theStudent) {

		logger.info("Adding student: " + theStudent);

		try {
			studentDbUtil.addStudent(theStudent);
			
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error adding students", exc);			
			addErrorMessage(exc);

			return null;
		}
		
		return "list-students?faces-redirect=true";
	}

	public String loadStudent(int studentId) {
		
		logger.info("loading student: " + studentId);
		
		try {
			Student theStudent = studentDbUtil.getStudent(studentId);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("student", theStudent);	
			
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading student id:" + studentId, exc);
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-student-form.xhtml";
	}	
	
	public String updateStudent(Student theStudent) {
		logger.info("updating student: " + theStudent);		
		try {			
			studentDbUtil.updateStudent(theStudent);
			
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error updating student: " + theStudent, exc);
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-students?faces-redirect=true";		
	}
	
	public String deleteStudent(int studentId) {

		logger.info("Deleting student id: " + studentId);
		
		try {
			studentDbUtil.deleteStudent(studentId);
			
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error deleting student id: " + studentId, exc);
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
