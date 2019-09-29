
package models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import java.sql.Blob;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean
public class Product {
    private int code;
    private String description;
    private String category;
    private int currentExist;
    private int minExist;
    private int maxExist;
    private Blob image;
    
    public Product(){
    
    }
    public Product(int code, String description, String category, int currentExist, int minExist, int maxExist, Blob image){
        this.code = code;
        this.description = description;
        this.category = category;
        this.currentExist = currentExist;
        this.minExist = minExist;
        this.maxExist = maxExist;
        this.image = image;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the currentExist
     */
    public int getCurrentExist() {
        return currentExist;
    }

    /**
     * @param currentExist the currentExist to set
     */
    public void setCurrentExist(int currentExist) {
        this.currentExist = currentExist;
    }

    /**
     * @return the minExist
     */
    public int getMinExist() {
        return minExist;
    }

    /**
     * @param minExist the minExist to set
     */
    public void setMinExist(int minExist) {
        this.minExist = minExist;
    }

    /**
     * @return the maxExist
     */
    public int getMaxExist() {
        return maxExist;
    }

    /**
     * @param maxExist the maxExist to set
     */
    public void setMaxExist(int maxExist) {
        this.maxExist = maxExist;
    }

    /**
     * @return the image
     */
    public Blob getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String fileName,InputStream in) {
        
        try{  
            ServletContext servC= (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            String serverURL = servC.getRealPath("")+File.separatorChar+"resources"+
                    File.separatorChar+"img"+File.separatorChar; //\\localhost\WebApplication2\resources\img\
            OutputStream out= new FileOutputStream(new File(serverURL+fileName));
            int read=0;
            byte[] bytes=new byte[1024];
            while((read=in.read(bytes))!=-1){
                out.write(bytes,0,read);
            }
            in.close();
            out.flush();
            out.close();
            
            this.image = (Blob) out;
        }catch(IOException ex){
             ex.printStackTrace();
        }
    }
    
}
