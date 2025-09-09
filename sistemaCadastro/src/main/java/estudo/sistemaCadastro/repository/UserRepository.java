package estudo.sistemaCadastro.repository;

import estudo.sistemaCadastro.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    boolean existsByCpf(String cpf);

    void deleteByCpf(String cpf);

    Optional<UserEntity> findByCpf(String cpf);
}
