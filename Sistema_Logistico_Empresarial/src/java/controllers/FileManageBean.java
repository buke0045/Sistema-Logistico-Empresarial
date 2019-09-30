
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
public class FileManageBean{
    public void upload(FileUploadEvent event){
        try{
           cargar(event.getFile().getFileName(),event.getFile().getInputstream()); 
           FacesMessage msg = new FacesMessage("El archivo se ha subido con exito");
           FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(IOException e){
           
        }
    }
    private void cargar(String fileName,InputStream in){
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
            
        }catch(IOException ex){
             ex.printStackTrace();
        }
    }
}
