package estudos.querySql;


import estudos.querySql.nativeQuery.ArtistsMinProjection;
import estudos.querySql.nativeQuery.ArtistsMinProjectionJoin;
import estudos.querySql.repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class QuerySqlApplication implements CommandLineRunner {
	@Autowired
	private ArtistsRepository artistsRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuerySqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<ArtistsMinProjection> list = artistsRepository.findByCountry("Brazil");
		list.forEach(x -> System.out.println(x.getName()));
		List<ArtistsMinProjectionJoin> list1 = artistsRepository.findByGenres("samba	");
		list1.forEach(x -> System.out.println(x.getName() +" "+ x.getCountry()));

	}
}

