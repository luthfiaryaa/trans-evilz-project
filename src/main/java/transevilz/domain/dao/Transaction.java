package transevilz.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "transaction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 3, nullable = false)
    private Long id;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_transaction",
//            joinColumns = @JoinColumn(name = "transaction_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> user_transaction = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @Column(name = "TARGET_ID", length = 200)
    private String target_id;

    @Column(name = "NOMINAL", length = 200)
    private Long nominal;

    @Column(name = "TYPE", length = 200, columnDefinition = "varchar(200) default 'Lokal'")
    private String type;

    @Column(name = "ADMIN_FEE", length = 200, columnDefinition = "integer default 5000")
    private Long admin_fee;

    @Column(name = "CREATED_AT", length = 200)
    private LocalDate create_at;

    @Column(name = "UPDATE_AT", length = 200)
    private LocalDate update_at;

    @Column(name = "EXPIRED_AT", length = 200)
    private LocalDate expired_at;
}
