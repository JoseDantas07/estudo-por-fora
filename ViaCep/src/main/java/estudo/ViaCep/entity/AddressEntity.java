package estudo.ViaCep.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userFk", nullable = false)
    private UserEntity userFk;

    @ManyToOne
    @JoinColumn(name = "cepFk", nullable = false)
    private CepEntity cepFk;

    @Column(name = "number",nullable = false)
    private Long number;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description")
    private String description;

}
