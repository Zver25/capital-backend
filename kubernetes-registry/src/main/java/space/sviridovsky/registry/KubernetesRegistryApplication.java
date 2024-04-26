package space.sviridovsky.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KubernetesRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubernetesRegistryApplication.class, args);
	}

}
