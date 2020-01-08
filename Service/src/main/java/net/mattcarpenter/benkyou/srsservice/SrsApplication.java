package net.mattcarpenter.benkyou.srsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrsApplication.class, args);
	}

}
