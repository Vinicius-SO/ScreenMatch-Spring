package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.services.ConsumoAPI;
import br.com.alura.screenmatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=cf857f84");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie serie = conversor.obterDados(json, DadosSerie.class);
		System.out.println(serie);

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=cf857f84");
		DadosEpisodio episodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(episodio);

		ArrayList<DadosTemporada> dadosTemporadas = new ArrayList<>();

		for (int i = 1; i < serie.totalTemporadas(); i++) {
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season="+i+"&apikey=cf857f84");
			DadosTemporada temporada = conversor.obterDados(json,DadosTemporada.class);
			dadosTemporadas.add(temporada);
		}
		dadosTemporadas.forEach(System.out::println);
	}
}
