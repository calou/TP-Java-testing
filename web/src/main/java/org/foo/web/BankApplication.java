package org.foo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@SpringBootApplication(scanBasePackages = "org.foo")
public class BankApplication {
	public static void main(String[] args){
		SpringApplication.run(BankApplication.class);
	}
}
