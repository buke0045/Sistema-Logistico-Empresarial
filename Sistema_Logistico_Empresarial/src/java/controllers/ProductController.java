
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.Product;
import models.ProductDAO;

@ManagedBean
@SessionScoped
public class ProductController {
    
    private List<Product> Products;
    private ProductDAO productDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    public ProductController()throws Exception{
        Products = new ArrayList<>();
        productDbUtil = ProductDAO.getInstanceP();
    }
    public List<Product> getProducts(){
        return Products;
    }
    public void loadProducts(){
        logger.info("Loading products");
        Products.clear();
        try {
            Products = productDbUtil.getProduct();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading users", e);
            addErrorMessage(e);
        }
    }
    public String addProduct(Product pProduct){
        
        try {
            productDbUtil.addProduct(pProduct);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding product", e);			
            addErrorMessage(e);
            return null;
        }
            return "AdmProducts?faces-redirect=true";
    }
    public String loadProduct(int prodCode) {

            logger.info("loading product: " + prodCode);

            try {
                    Product products = productDbUtil.getProduct(prodCode);
                    
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
                    Map<String, Object> requestMap = externalContext.getRequestMap();
                    requestMap.put("product", products);
                 
                    
            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error loading product code:" + prodCode, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "update-product-form.xhtml";
    }
    public String updateProduct(Product pProduct) {
            logger.info("updating User id: " + pProduct.getCode());		
            try {			
                    productDbUtil.updateProduct(pProduct);

            } catch (Exception exc) {
                    logger.log(Level.SEVERE, "Error updating product: " + pProduct, exc);
                    addErrorMessage(exc);

                    return null;
            }

            return "AdmProducts?faces-redirect=true";		
    }
    public String deleteProduct(int prodCode) {

	logger.info("Deleting product code: " + prodCode);
	
	try {
		productDbUtil.deleteProduct(prodCode);
		
	} catch (Exception exc) {
		logger.log(Level.SEVERE, "Error deleting User id: " + prodCode, exc);
		addErrorMessage(exc);
		
		return null;
	}
	
	return "AdmProducts";	
    }
    private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
