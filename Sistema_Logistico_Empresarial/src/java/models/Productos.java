
package models;

import java.io.File;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Productos {
    private String codigo;
    private String descripcion;
    private String categoria;
    private int ExistAct;
    private int ExistMin;
    private int ExistMax;
    private File imagen;
    
    public Productos(){
    
    }
    public Productos(String codigo, String descripcion, String categoria, int ExistAct, int ExistMin, int ExistMax, File imagen){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ExistAct = ExistAct;
        this.ExistMin = ExistMin;
        this.ExistMax = ExistMax;
        this.imagen = imagen;
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
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the ExistAct
     */
    public int getExistAct() {
        return ExistAct;
    }

    /**
     * @param ExistAct the ExistAct to set
     */
    public void setExistAct(int ExistAct) {
        this.ExistAct = ExistAct;
    }

    /**
     * @return the ExistMin
     */
    public int getExistMin() {
        return ExistMin;
    }

    /**
     * @param ExistMin the ExistMin to set
     */
    public void setExistMin(int ExistMin) {
        this.ExistMin = ExistMin;
    }

    /**
     * @return the ExistMax
     */
    public int getExistMax() {
        return ExistMax;
    }

    /**
     * @param ExistMax the ExistMax to set
     */
    public void setExistMax(int ExistMax) {
        this.ExistMax = ExistMax;
    }

    /**
     * @return the imagen
     */
    public File getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(File imagen) {
        this.imagen = imagen;
    }
    
    
}
