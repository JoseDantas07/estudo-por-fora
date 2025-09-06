package estudos.querySql.repository;

import estudos.querySql.entity.Artists;
import estudos.querySql.nativeQuery.ArtistsMinProjection;
import estudos.querySql.nativeQuery.ArtistsMinProjectionJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistsRepository extends JpaRepository<Artists,Long> {

    @Query(nativeQuery = true, value = "select * from artists where country like :country")
    List<ArtistsMinProjection> findByCountry(String country);

    @Query(nativeQuery = true, value = "select a.name,a.country from artists as a join genres as g on g.id = a.genre_id where g.name = :genreName")
    List<ArtistsMinProjectionJoin> findByGenres(String genreName);
}
