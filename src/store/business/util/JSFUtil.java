package store.business.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

	public static void createSuccessMessage(String message) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", message);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}
	
	public static void createErrorMessage(String message) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}
	
}
