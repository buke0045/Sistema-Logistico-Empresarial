
package models;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/*
-> Creacion de base:
USE sistema_logistico_empresarial;
CREATE TABLE product(
    code int(255) AUTO_INCREMENT NOT NULL,
    description varchar(50) NOT NULL,
    category varchar(100) NOT NULL,
    currentExist int(50) NOT NULL,
    minExist int(50) NOT NULL,
    maxExist int(50) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY(code)
)ENGINE=INNODB;

->Primer dato:
INSERT INTO product(code, description, category, currentExist, minExist, maxExist)
VALUES(1,'1','1', '1', 1, '1@1.com')

*/

public class ProductDAO extends Conexion{
    
    public ProductDAO() throws Exception{
        super();
    }

    //Agregar
    public void addProduct(Product pProduct) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		myConn = this.getConnection();

		String sql = "insert into product (code, description, category, currentExist, minExist, maxExist) values (?, ?, ?, ?, ?, ?)";

		myStmt = myConn.prepareStatement(sql);

		myStmt.setInt(1, pProduct.getCode());
		myStmt.setString(2, pProduct.getDescription());
		myStmt.setString(3, pProduct.getCategory());
		myStmt.setInt(4, pProduct.getCurrentExist());
		myStmt.setInt(5, pProduct.getMinExist());
		myStmt.setInt(6, pProduct.getMaxExist());
		myStmt.execute();			
	}
	finally {
		this.close(myConn, myStmt);
	}
    }
    
//Mostrar
    public List<Product> getProduct() throws Exception {

        List<Product> Prods = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
                myConn = this.getConnection();

                String sql = "select * from product order by code";

                myStmt = myConn.createStatement();

                myRs = myStmt.executeQuery(sql);

                while (myRs.next()) {

                        int code = myRs.getInt("code");
                        String description = myRs.getString("description");
                        String category = myRs.getString("category");
                        int currentExist = myRs.getInt("currentExist");
                        int minExist = myRs.getInt("minExist");
                        int maxExist = myRs.getInt("maxExist");
                        Product tempProduct = new Product(code, description, category, currentExist, minExist, maxExist);

                        Prods.add(tempProduct);
                }

                return Prods;		
        }
        finally {
                this.close (myConn, myStmt, myRs);
        }
    }
    
//Modificar
    public Product getProduct(int prodCode) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = this.getConnection();

			String sql = "select * from product where code=?";

			myStmt = myConn.prepareStatement(sql);
			
			
			myStmt.setInt(1, prodCode);
			
			myRs = myStmt.executeQuery();

			Product prod = null;
			
			if (myRs.next()) {
				
                                int code = myRs.getInt("code");
                                String description = myRs.getString("description");
                                String category = myRs.getString("category");
                                int currentExist = myRs.getInt("currentExist");
                                int minExist = myRs.getInt("minExist");
                                int maxExist = myRs.getInt("maxExist");
				prod = new Product(code, description, category, currentExist, minExist, maxExist);
                        }
			else {
				throw new Exception("Could not find product code: " + prodCode);
			}

			return prod;
		}
		finally {
			this.close (myConn, myStmt, myRs);
		}
	}
    
    public void updateProduct(Product pProduct) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
                myConn = this.getConnection();

                String sql = "update product "
                                        + " set code=?, description=?, category=?, currentExist=?, minExist=?, maxExist=? where code=?";
                myStmt = myConn.prepareStatement(sql);

                myStmt.setInt(1, pProduct.getCode());
		myStmt.setString(2, pProduct.getDescription());
		myStmt.setString(3, pProduct.getCategory());
		myStmt.setInt(4, pProduct.getCurrentExist());
		myStmt.setInt(5, pProduct.getMinExist());
		myStmt.setInt(6, pProduct.getMaxExist());
                myStmt.setInt(7, pProduct.getCode());

                myStmt.execute();
        }
        finally {
                this.close (myConn, myStmt);
        }
    }
    
//Eliminar
    public void deleteProduct(int DepCode) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = this.getConnection();

            String sql = "delete from product where code=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, DepCode);

            myStmt.execute();
        }
        finally {
            this.close (myConn, myStmt);
        }		
    }
    
}
