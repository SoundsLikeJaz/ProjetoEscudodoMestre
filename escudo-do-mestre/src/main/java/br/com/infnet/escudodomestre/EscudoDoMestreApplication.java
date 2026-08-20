package br.com.infnet.escudodomestre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EscudoDoMestreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscudoDoMestreApplication.class, args);
	}

}
