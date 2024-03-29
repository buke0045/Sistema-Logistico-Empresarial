/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author eddie
 */
public class Conexion{
        private static UserDAO instance;
        private static CategoryDAO instanceC;
        private static DepartmentDAO instanceD;
        private static ProductDAO instanceP;
	private DataSource dataSource;
	private String jndiName = "java:app/sistema_logistico_empresarial";
	
	public static UserDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new UserDAO();
		}
		
		return instance;
	}
	
        public static CategoryDAO getInstanceC() throws Exception {
		if (instanceC == null) {
			instanceC = new CategoryDAO();
		}
		
		return instanceC;
	}
        
        public static DepartmentDAO getInstanceD() throws Exception {
		if (instanceD == null) {
			instanceD = new DepartmentDAO();
		}
		
		return instanceD;
	}
        
        public static ProductDAO getInstanceP() throws Exception {
		if (instanceP == null) {
			instanceP = new ProductDAO();
		}
		
		return instanceP;
	}
        
	public Conexion() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
        protected Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	protected void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	protected void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
