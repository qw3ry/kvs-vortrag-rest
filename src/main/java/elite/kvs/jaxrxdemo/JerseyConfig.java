package elite.kvs.jaxrxdemo;

import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import elite.kvs.jaxrxdemo.controller.ControllerV1;
import elite.kvs.jaxrxdemo.controller.ControllerV2;
import elite.kvs.jaxrxdemo.controller.ControllerV3;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        super(ControllerV1.class, ControllerV2.class, ControllerV3.class, JacksonXMLProvider.class);
    }
}
