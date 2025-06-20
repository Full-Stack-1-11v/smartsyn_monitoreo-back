package cl.ecomarket.monitoreo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * FUNCIONA LA APLICACION SIN PROBLEMAS
 */
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = "cl.ecomarket.monitoreo")
public class MonitoreoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoreoApplication.class, args);
	}

}
