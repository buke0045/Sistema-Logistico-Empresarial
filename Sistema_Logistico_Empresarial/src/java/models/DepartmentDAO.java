
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends Conexion{
    
    public DepartmentDAO() throws Exception{
        super();
    }

    //Agregar
    public void addDepartment(Department pDepartments) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		myConn = this.getConnection();

		String sql = "insert into department (code, name, manager, location, phone, email) values (?, ?, ?, ?, ?, ?)";

		myStmt = myConn.prepareStatement(sql);

		myStmt.setInt(1, pDepartments.getCode());
		myStmt.setString(2, pDepartments.getName());
		myStmt.setString(3, pDepartments.getManager());
		myStmt.setString(4, pDepartments.getLocation());
		myStmt.setInt(5, pDepartments.getPhone());
		myStmt.setString(6, pDepartments.getEmail());
		
		myStmt.execute();			
	}
	finally {
		this.close(myConn, myStmt);
	}
    }
    
//Mostrar
    public List<Department> getDepartment() throws Exception {

        List<Department> Deps = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
                myConn = this.getConnection();

                String sql = "select * from department order by code";

                myStmt = myConn.createStatement();

                myRs = myStmt.executeQuery(sql);

                while (myRs.next()) {

                        int code = myRs.getInt("code");
                        String name = myRs.getString("name");
                        String manager = myRs.getString("manager");
                        String location = myRs.getString("location");
                        int phone = myRs.getInt("phone");
                        String email = myRs.getString("email");

                        Department tempDepartment = new Department(code, name, manager, location, phone, email);

                        Deps.add(tempDepartment);
                }

                return Deps;		
        }
        finally {
                this.close (myConn, myStmt, myRs);
        }
    }
    
//Modificar
    public Department getDepartment(int depCode) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = this.getConnection();

			String sql = "select * from department where code=?";

			myStmt = myConn.prepareStatement(sql);
			
			
			myStmt.setInt(1, depCode);
			
			myRs = myStmt.executeQuery();

			Department dep = null;
			
			if (myRs.next()) {
				
                                int code = myRs.getInt("code");
                                String name = myRs.getString("name");
                                String manager = myRs.getString("manager");
                                String location = myRs.getString("location");
                                int phone = myRs.getInt("phone");
                                String email = myRs.getString("email");
                                
				dep = new Department(code, name, manager, location, phone, email);
                        }
			else {
				throw new Exception("Could not find department code: " + depCode);
			}

			return dep;
		}
		finally {
			this.close (myConn, myStmt, myRs);
		}
	}
    
    public void updateDepartment(Department pDepartment) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
                myConn = this.getConnection();

                String sql = "update department "
                                        + " set code=?, name=?, manager=?, location=?, phone=?, email=? where code=?";
                myStmt = myConn.prepareStatement(sql);

                myStmt.setInt(1, pDepartment.getCode());
		myStmt.setString(2, pDepartment.getName());
		myStmt.setString(3, pDepartment.getManager());
		myStmt.setString(4, pDepartment.getLocation());
		myStmt.setInt(5, pDepartment.getPhone());
		myStmt.setString(6, pDepartment.getEmail());
                myStmt.setInt(7, pDepartment.getCode());

                myStmt.execute();
        }
        finally {
                this.close (myConn, myStmt);
        }
    }
    
//Eliminar
    public void deleteDepartment(int DepCode) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = this.getConnection();

            String sql = "delete from department where code=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, DepCode);

            myStmt.execute();
        }
        finally {
            this.close (myConn, myStmt);
        }		
    }

}
