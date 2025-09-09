package estudo.sistemaCadastro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String cpf;

    private int age;

    public UserEntity(String name, String cpf, int age) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
    }
}
