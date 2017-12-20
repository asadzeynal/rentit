package az.pkg.rentit;

import az.pkg.rentit.webservice.PlantWS;
import az.pkg.rentit.webservice.PurchaseOrderWS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import javax.xml.ws.Endpoint;

@SpringBootApplication
public class RentitApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RentitApplication.class, args);
		Object implementor = context.getBean(PlantWS.class);
		String address = "http://localhost:9000/services/az/pkg/rentit/webservice/PlantWS";
		Endpoint.publish(address, implementor);
		implementor = context.getBean(PurchaseOrderWS.class);
		address = "http://localhost:9001/services/az/pkg/rentit/webservice/PurchaseOrderWS";
		Endpoint.publish(address, implementor);
	}
}
