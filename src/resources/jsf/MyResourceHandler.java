package resources.jsf;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;

public class MyResourceHandler extends ResourceHandlerWrapper {

    private ResourceHandler wrapped;

    public MyResourceHandler(ResourceHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Resource createResource(String resourceName, String libraryName) {
        return new MyResource(wrapped.createResource(resourceName, libraryName));
    }

    @Override
    public ResourceHandler getWrapped() {
        return wrapped;
    }

}