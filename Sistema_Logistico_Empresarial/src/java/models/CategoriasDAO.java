
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

public class CategoriasDAO extends Conexion{

    public CategoriasDAO() throws Exception{
        super();
    }
    
//Agregar
    public void addCategoria(Categorias pCategorias) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		myConn = this.getConnection();

		String sql = "insert into categorias (codigo, descripcion, bloque) values (?, ?, ?)";

		myStmt = myConn.prepareStatement(sql);

		myStmt.setInt(1, pCategorias.getCodigo());
		myStmt.setString(2, pCategorias.getDescripcion());
		myStmt.setString(3, pCategorias.getBloque());
		
		myStmt.execute();			
	}
	finally {
		this.close(myConn, myStmt);
	}
    }
    
//Mostrar
    public List<Categorias> getCategorias() throws Exception {

        List<Categorias> Categs = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
                myConn = this.getConnection();

                String sql = "select * from categorias order by codigo";

                myStmt = myConn.createStatement();

                myRs = myStmt.executeQuery(sql);

                while (myRs.next()) {

                        int codigo = myRs.getInt("codigo");
                        String descripcion = myRs.getString("descripcion");
                        String bloque = myRs.getString("bloque");

                        Categorias tempStudent = new Categorias(codigo, descripcion, bloque);

                        Categs.add(tempStudent);
                }

                return Categs;		
        }
        finally {
                this.close (myConn, myStmt, myRs);
        }
    }
    
//Modificar
    public Categorias getCategory(int categCodigo) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = this.getConnection();

			String sql = "select * from categorias where codigo=?";

			myStmt = myConn.prepareStatement(sql);
			
			
			myStmt.setInt(1, categCodigo);
			
			myRs = myStmt.executeQuery();

			Categorias categ = null;
			
			if (myRs.next()) {
				int codigo = myRs.getInt("codigo");
				String descripcion = myRs.getString("descripcion");
				String bloque = myRs.getString("bloque");

				categ = new Categorias(codigo, descripcion, bloque);
			}
			else {
				throw new Exception("Could not find category codigo: " + categCodigo);
			}

			return categ;
		}
		finally {
			this.close (myConn, myStmt, myRs);
		}
	}
    
    public void updateCategoria(Categorias Categ) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
                myConn = this.getConnection();

                String sql = "update categorias "
                                        + " set codigo=?, descripcion=?, bloque=? where codigo=?";
                myStmt = myConn.prepareStatement(sql);

                myStmt.setInt(1, Categ.getCodigo());
                myStmt.setString(2, Categ.getDescripcion());
                myStmt.setString(3, Categ.getBloque());
                myStmt.setInt(4, Categ.getCodigo());

                myStmt.execute();
        }
        finally {
                this.close (myConn, myStmt);
        }
    }
    
//Eliminar
    public void deleteCategoria(int CategCodigo) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = this.getConnection();

            String sql = "delete from categorias where codigo=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, CategCodigo);

            myStmt.execute();
        }
        finally {
            this.close (myConn, myStmt);
        }		
    }
}
