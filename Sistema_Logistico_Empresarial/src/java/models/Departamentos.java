
package models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Departamentos {
    private int codigo;
    private String nombre;
    private String encargado;
    private String ubicacion;
    private int telefono;
    private String correo;
    
    public Departamentos(){
    
    }
    public Departamentos(int codigo, String nombre, String encargado, String ubicacion, int telefono, String correo){
        this.codigo = codigo;
        this.nombre = nombre;
        this.encargado = encargado;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.correo = correo;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the encargado
     */
    public String getEncargado() {
        return encargado;
    }

    /**
     * @param encargado the encargado to set
     */
    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
