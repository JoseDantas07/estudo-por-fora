package estudo.searchMovie.controller;

import estudo.searchMovie.model.MovieModel;
import estudo.searchMovie.service.MovieService;
import estudo.searchMovie.url.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private Url url;
    @Autowired
    private MovieService movieService;

    @GetMapping
    ResponseEntity<List<MovieModel>> getAllMovie(){
        return ResponseEntity.ok(url.getAllMovie());
    }

    @GetMapping("/title/{title}")
    ResponseEntity<List<MovieModel>> getByTitle(@PathVariable String title){
        return ResponseEntity.ok(url.getlByTitle(title));
    }

    @GetMapping("/autocomplete")
    ResponseEntity<List<MovieModel>> findByPrefix(@RequestParam("q") String prefix){
        return ResponseEntity.ok(movieService.autoComplete(prefix));
    }

    @GetMapping("/director/{director}")
    ResponseEntity<List<MovieModel>> getByDirector(@PathVariable String director){
        return ResponseEntity.ok(url.getByDirector(director));
    }

    @GetMapping("/writer/{writer}")
    ResponseEntity<List<MovieModel>> getByWriter(@PathVariable String writer){
       return ResponseEntity.ok(url.getByWriter(writer));
    }


}
