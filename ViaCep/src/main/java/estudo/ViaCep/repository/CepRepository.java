package estudo.ViaCep.repository;

import estudo.ViaCep.entity.CepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<CepEntity, String> {
}
