
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends Conexion{

    public UserDAO() throws Exception{
        super();
    }
    
    public void addUser(User pUser) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		myConn = this.getConnection();

		String sql = "insert into user (username, password) values (?, ?)";

		myStmt = myConn.prepareStatement(sql);

		myStmt.setString(1, pUser.getUsername());
		myStmt.setString(2, pUser.getPassword());
		
		myStmt.execute();			
	}
	finally {
		this.close(myConn, myStmt);
	}
		
    }
    public User getUser(String pUsername, String pPassword) throws Exception {
	
	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	
	try {
		myConn = this.getConnection();

		String sql = "select * from user where username=? AND password=?";

		myStmt = myConn.prepareStatement(sql);
		
		
		myStmt.setString(1, pUsername);
                myStmt.setString(2, pPassword);
		myRs = myStmt.executeQuery();

		User pUser = null;
		
		if (myRs.next()) {
			int id = myRs.getInt("id");
			String username = myRs.getString("username");
			String password = myRs.getString("password");

			pUser = new User(id, username, password);
		}
		else {
			throw new Exception("Usuario o Contraseña invalidos");
		}

		return pUser;
	}
	finally {
		this.close (myConn, myStmt, myRs);
	}
	}
}
