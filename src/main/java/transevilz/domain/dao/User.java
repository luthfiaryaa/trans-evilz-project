package transevilz.domain.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "users",
        uniqueConstraints ={
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 3, nullable = false)
    private Long id;

    @Column(name = "USERNAME", length = 200)
    private String username;

    @Column(name = "URL_PICTURE", length = 200)
    private String picture;

    @Column(name = "EMAIL", length = 20, unique = true)
    private String email;

    @Column(name = "CITIZENSHIP", length = 15)
    private Integer citizenship;

    @Column(name = "DOCUMENT", length = 10)
    private String document;

    @Column(name = "FIRST_NAME", length = 200)
    private String firstname;

    @Column(name = "LAST_NAME", length = 200)
    private String lastname;

    @Column(name = "PLACE_OF_BIRTH", length = 200)
    private String placeofbirth;

    @Column(name = "DATE_OF_BIRTH", length = 200)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateofbirth;

    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Column(name = "GENDER", length = 200)
    private String gender;

    @Column(name = "PHONE", length = 11)
    private Long phone;

    @Column(name = "PASSWORD", length = 120)
    private String password;

    @Column(name = "M_PIN", length = 200)
    private String mpin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
