package estudo.searchMovie.url;

import estudo.searchMovie.model.MovieModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "SanFrancisco" , url = "https://data.sfgov.org/resource/yitu-d5am.json")
public interface Url {

    @GetMapping
    List<MovieModel> getAllMovie();

    @GetMapping(value = "?title={title}")
    List<MovieModel> getlByTitle(@PathVariable String title);

    @GetMapping(value = "?director={director}")
    List<MovieModel> getByDirector(@PathVariable String director);

    @GetMapping(value = "?writer={writer}")
    List<MovieModel> getByWriter(@PathVariable String writer);
}
