
package models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Categorias {
    private int codigo;
    private String descripcion;
    private String bloque;
    
    public Categorias(){
    
    }
    public Categorias(int codigo, String descripcion, String bloque){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.bloque = bloque;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the bloque
     */
    public String getBloque() {
        return bloque;
    }

    /**
     * @param bloque the bloque to set
     */
    public void setBloque(String bloque) {
        this.bloque = bloque;
    }
    
    @Override
	public String toString() {
		return "Categories [codigo=" + codigo + ", descripcion=" + descripcion + ", bloque=" + bloque + "]";
	}
    
}
