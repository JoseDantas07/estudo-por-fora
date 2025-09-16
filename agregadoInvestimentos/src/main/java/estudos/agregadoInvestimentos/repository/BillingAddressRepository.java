package estudos.agregadoInvestimentos.repository;

import estudos.agregadoInvestimentos.entity.BillingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddressEntity, UUID> {


}
