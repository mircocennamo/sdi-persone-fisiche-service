package it.interno.personefisiche;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableEncryptableProperties
public class PersonafisicaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonafisicaServiceApplication.class, args);
	}

}
