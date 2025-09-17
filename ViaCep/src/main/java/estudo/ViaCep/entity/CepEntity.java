package estudo.ViaCep.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ceps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CepEntity {
    @Id
    @Column(nullable = false, name = "cepId")
    private String cepId;

    @Column(name = "street")
    private String logradouro;

    @Column(name = "neighborhood")
    private String bairro;

    @Column(name = "locality")
    private String localidade;

    @OneToMany(mappedBy = "cepFk",cascade = CascadeType.ALL)
    List<AddressEntity> addressEntity;
}
