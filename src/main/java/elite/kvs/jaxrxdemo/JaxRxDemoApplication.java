package elite.kvs.jaxrxdemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JaxRxDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new JaxRxDemoApplication()
				.configure(new SpringApplicationBuilder(JaxRxDemoApplication.class))
				.run(args);
	}
}
