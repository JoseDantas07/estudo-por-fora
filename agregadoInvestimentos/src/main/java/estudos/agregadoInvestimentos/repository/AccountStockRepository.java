package estudos.agregadoInvestimentos.repository;

import estudos.agregadoInvestimentos.entity.AccountStockEntity;
import estudos.agregadoInvestimentos.entity.AccountStockIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStockEntity, AccountStockIdEntity> {


}
