package inndata172.ejercicioTarea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//Se debe de agregar esta instruccion para que sirva la conexion con https://mockapi.io/
@EnableFeignClients

public class EjercicioTareaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioTareaApplication.class, args);
	}

}
