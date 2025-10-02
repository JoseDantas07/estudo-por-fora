package estudo.searchMovie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieModel {
    private String title;
    private String release_year;
    private String director;
    private String writer;
    private String locations;
    private String longitude;
    private String latitude;
}
