package transevilz.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "target")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 3, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "NO_REKENING", length = 200)
    private String no_rekening;

    @ManyToOne
    @JoinColumn(name="bank_id", nullable=false)
    private Bank bank;

    public Target(String no_rekening) {
        this.no_rekening = no_rekening;
    }
}
