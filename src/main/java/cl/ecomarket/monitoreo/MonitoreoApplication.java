package cl.ecomarket.monitoreo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MonitoreoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoreoApplication.class, args);
	}

}
