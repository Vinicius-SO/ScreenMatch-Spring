package br.com.alura.screenmatch;

import br.com.alura.screenmatch.services.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		var response = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=cf857f84");
		System.out.println(response);
	}
}
