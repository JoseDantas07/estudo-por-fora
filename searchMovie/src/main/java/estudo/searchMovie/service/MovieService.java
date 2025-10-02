package estudo.searchMovie.service;

import estudo.searchMovie.model.MovieModel;
import estudo.searchMovie.url.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private Url url;

    public List<MovieModel> autoComplete(String prefix){
        var movie = url.getAllMovie();
        var findMovie = movie.stream().filter(x -> x.getTitle().startsWith(prefix)).toList();
        return findMovie;
    }
}
