
package models;


import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {
    private int code;
    private String description;
    private String category;
    private int currentExist;
    private int minExist;
    private int maxExist;
    
    public Product(){
    
    }
    public Product(int code, String description, String category, int currentExist, int minExist, int maxExist){
        this.code = code;
        this.description = description;
        this.category = category;
        this.currentExist = currentExist;
        this.minExist = minExist;
        this.maxExist = maxExist;
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
}
