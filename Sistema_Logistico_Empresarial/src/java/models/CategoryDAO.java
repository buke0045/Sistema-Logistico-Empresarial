
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*

->Crear base de datos:

USE sistema_logistico_empresarial;
CREATE TABLE categorias(
    codigo int(255) AUTO_INCREMENT NOT NULL,
    descripcion varchar(50) NOT NULL,
    bloque varchar(100) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY(codigo)
)ENGINE=INNODB;

-> Llenar 1 dato:

INSERT INTO categorias(codigo, descripcion, bloque)
VALUES(1,'Prod1','B1')
*/

public class CategoryDAO extends Conexion{

    public CategoryDAO() throws Exception{
        super();
    }
    
//Agregar
    public void addCategory(Category pCategories) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		myConn = this.getConnection();

		String sql = "insert into category (code, description, block) values (?, ?, ?)";

		myStmt = myConn.prepareStatement(sql);

		myStmt.setInt(1, pCategories.getCode());
		myStmt.setString(2, pCategories.getDescription());
		myStmt.setString(3, pCategories.getBlock());
		
		myStmt.execute();			
	}
	finally {
		this.close(myConn, myStmt);
	}
    }
    
//Mostrar
    public List<Category> getCategory() throws Exception {

        List<Category> Categs = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
                myConn = this.getConnection();

                String sql = "select * from category order by code";

                myStmt = myConn.createStatement();

                myRs = myStmt.executeQuery(sql);

                while (myRs.next()) {

                        int code = myRs.getInt("code");
                        String description = myRs.getString("description");
                        String block = myRs.getString("block");

                        Category tempCategory = new Category(code, description, block);

                        Categs.add(tempCategory);
                }

                return Categs;		
        }
        finally {
                this.close (myConn, myStmt, myRs);
        }
    }
    
//Modificar
    public Category getCategory(int categCode) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = this.getConnection();

			String sql = "select * from category where code=?";

			myStmt = myConn.prepareStatement(sql);
			
			
			myStmt.setInt(1, categCode);
			
			myRs = myStmt.executeQuery();

			Category categ = null;
			
			if (myRs.next()) {
				int code = myRs.getInt("code");
				String description = myRs.getString("description");
				String block = myRs.getString("block");

				categ = new Category(code, description, block);
			}
			else {
				throw new Exception("Could not find category code: " + categCode);
			}

			return categ;
		}
		finally {
			this.close (myConn, myStmt, myRs);
		}
	}
    
    public void updateCategory(Category Categ) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
                myConn = this.getConnection();

                String sql = "update category "
                                        + " set code=?, description=?, block=? where code=?";
                myStmt = myConn.prepareStatement(sql);

                myStmt.setInt(1, Categ.getCode());
                myStmt.setString(2, Categ.getDescription());
                myStmt.setString(3, Categ.getBlock());
                myStmt.setInt(4, Categ.getCode());

                myStmt.execute();
        }
        finally {
                this.close (myConn, myStmt);
        }
    }
    
//Eliminar
    public void deleteCategory(int CategCode) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = this.getConnection();

            String sql = "delete from category where code=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, CategCode);

            myStmt.execute();
        }
        finally {
            this.close (myConn, myStmt);
        }		
    }
}
