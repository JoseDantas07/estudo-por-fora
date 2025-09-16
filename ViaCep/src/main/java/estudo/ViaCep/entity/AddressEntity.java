package estudo.ViaCep.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID addressId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userFk;

    @ManyToOne
    @JoinColumn(name = "cepId")
    private CepEntity cepFk;

    @Column(name = "number",nullable = false)
    private Long number;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description")
    private String description;

}
