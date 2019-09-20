/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Juana
 */
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
}
