package estudo.picpay.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;

    @Column(name = "time")
    private Instant time;

    @Column(name = "amount")
    private BigDecimal amount;


    @Column(name = "refundOfPayment")
    private boolean refundOfPayment;

    @ManyToOne
    @JoinColumn(name = "senderId")
    @JsonBackReference
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    @JsonBackReference
    private UserEntity receiver;
}
