
package models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Categoria {
    private String codigo;
    private String descripcion;
    private String bloque;
    
    public Categoria(){
    
    }
    public Categoria(String codigo, String descripcion, String bloque){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.bloque = bloque;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
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
    
}
