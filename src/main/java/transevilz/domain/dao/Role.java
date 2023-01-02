package transevilz.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 3, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;
}
