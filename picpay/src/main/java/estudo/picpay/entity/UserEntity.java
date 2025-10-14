package estudo.picpay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "userId")
    private UUID userId;

    @Column(name = "name",nullable = false)
    protected String name;

    @Column(name = "password",nullable = false)
    protected String password;

    @Column(name = "cpfOrCnpj",unique = true , nullable = false)
    protected String cpfOrCnpj;

    @Column(name = "email",unique = true,nullable = false)
    protected String email;

    @Column(name = "userType", nullable = false)
    @Enumerated(EnumType.STRING)
    protected UserType userType;

    @Column(name = "balance")
    protected BigDecimal balance;

    @OneToMany(mappedBy = "sender")
    private List<TransactionEntity> senderTransaction;

    @OneToMany(mappedBy = "receiver")
    private List<TransactionEntity> receiverTransaction;

}
