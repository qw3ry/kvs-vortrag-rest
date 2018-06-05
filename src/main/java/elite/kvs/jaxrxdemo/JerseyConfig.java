package elite.kvs.jaxrxdemo;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import elite.kvs.jaxrxdemo.v1.Controller;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        super(Controller.class, JacksonXMLProvider.class);
    }
}
