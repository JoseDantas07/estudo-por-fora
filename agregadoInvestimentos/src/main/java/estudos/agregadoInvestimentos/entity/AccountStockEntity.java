package estudos.agregadoInvestimentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_accounts_stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStockEntity {

    @EmbeddedId
    private AccountStockIdEntity id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private StockEntity stockEntity;

    @Column(name = "quantity")
    private Integer quantity;
}
