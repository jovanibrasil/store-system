package resources.jsf;

import javax.faces.application.Resource;
import javax.faces.application.ResourceWrapper;
import javax.faces.context.FacesContext;

public class MyResource extends ResourceWrapper {

    private Resource wrapped;

    public MyResource(Resource wrapped) {
        this.wrapped = wrapped; 
    }

    @Override
    public String getRequestPath() {
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        return "/store-system" + wrapped.getRequestPath().substring(contextPath.length());
    }

    @Override
    public Resource getWrapped() {
        return wrapped;
    }

}