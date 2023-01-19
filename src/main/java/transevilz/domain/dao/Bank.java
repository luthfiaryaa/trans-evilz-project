package transevilz.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "public", name = "bank")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 3, nullable = false)
    private Long id;

//    @OneToMany
//    @JoinColumn(name = "transaction_id")
//    private Transaction transaction;

//    @OneToMany(mappedBy = "bank")
//    private Set<Target> target;

    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "CODE", length = 200)
    private String code;

    @Column(name = "COUNTRY_NAME", length = 200, columnDefinition = "varchar(200) default 'Indonesia'")
    private String country_name;
}
