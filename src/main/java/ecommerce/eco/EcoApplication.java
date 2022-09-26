package ecommerce.eco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoApplication.class, args);
/*
		Product p = new Product(1L,"description",10,2,20.0,"nike", SizeEnum.S, ColorEnum.RED, SexEnum.MEN,false,
				false);
		System.out.println(p);
*/
	}


}
