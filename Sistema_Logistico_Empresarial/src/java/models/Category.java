
package models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Category {
    private int code;
    private String description;
    private String block;
    
    public Category(){
    
    }
    public Category(int code, String description, String block){
        this.code = code;
        this.description = description;
        this.block = block;
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
     * @return the block
     */
    public String getBlock() {
        return block;
    }

    /**
     * @param block the block to set
     */
    public void setBlock(String block) {
        this.block = block;
    }
    
    @Override
	public String toString() {
		return "Category [codigo=" + code + ", descripcion=" + description + ", bloque=" + block + "]";
	}
    
}
