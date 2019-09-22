
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Conexion{

    public UserDAO() throws Exception{
        super();
    }
    
   public void addUser(User pUser) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;
	try {
		myConn = this.getConnection();

		String sql = "insert into user (username, password, name, surname, gender, email) values (?, ?, ?, ?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, pUser.getUsername());
		myStmt.setString(2, pUser.getPassword());
		myStmt.setString(3, pUser.getName());
		myStmt.setString(4, pUser.getSurname());
		myStmt.setString(5, pUser.getGender());
		myStmt.setString(6, pUser.getEmail());
		myStmt.execute();			
	}
	finally {
		this.close(myConn, myStmt);
	}
		
    }
    public List<User> getUsers() throws Exception{
        List<User> Users = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = this.getConnection();
            String sql = "select * from user order by username";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                int id = myRs.getInt("id");
                String username = myRs.getString("username");
                String password = myRs.getString("password");
                String name = myRs.getString("name");
                String surname = myRs.getString("surname");
                String gender = myRs.getString("gender");
                String email = myRs.getString("email");
                User tempUser = new User(id, username, password, name, surname, gender, email);
                Users.add(tempUser);
            }
            return Users;		
        }
        finally {
                this.close (myConn, myStmt, myRs);
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
                        String name = myRs.getString("name");
                        String surname = myRs.getString("surname");
                        String gender = myRs.getString("gender");
                        String email = myRs.getString("email");

			pUser = new User(id, username, password, name, surname, gender, email);
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
     public void updateUser(User pUser) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
                myConn = this.getConnection();

                String sql = "update User " + " set username=?, password=?, name=?, surname=?, gender=?, email=?  where id=?";
                myStmt = myConn.prepareStatement(sql);

                myStmt.setInt(1, pUser.getId());
                myStmt.setString(1, pUser.getUsername());
		myStmt.setString(2, pUser.getPassword());
		myStmt.setString(3, pUser.getName());
		myStmt.setString(4, pUser.getSurname());
		myStmt.setString(5, pUser.getGender());
		myStmt.setString(6, pUser.getEmail());

                myStmt.execute();
        }
        finally {
                this.close (myConn, myStmt);
        }
    }
}
