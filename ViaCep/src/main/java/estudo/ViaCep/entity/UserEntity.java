package estudo.ViaCep.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "age")
    private Integer age;

    @OneToMany(mappedBy = "userFk" , cascade = CascadeType.ALL)
    List<AddressEntity> addressEntities;
}
