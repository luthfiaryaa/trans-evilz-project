package transevilz.domain.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

//    @Column(name = "URL_PICTURE", length = 200)
//    private String picture;

    @Column(name = "EMAIL", length = 200, unique = true)
    private String email;

//    @Column(name = "CITIZENSHIP", length = 15)
//    private String citizenship;

    @Column(name = "DOCUMENT_TYPE", length = 10)
    private String doc_type;

    @Column(name = "DOCUMENT_NUMBER", length = 200)
    private String doc_number;

    @Column(name = "FIRST_NAME", length = 200)
    private String firstname;

    @Column(name = "LAST_NAME", length = 200)
    private String lastname;

    @Column(name = "PLACE_OF_BIRTH", length = 200)
    private String birth_place;

    @Column(name = "BIRTH_DATE", length = 200)
    private LocalDate birth_date;

    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Column(name = "SEX", length = 200)
    private String sex;

    @Column(name = "PHONE_NUMBER", length = 50)
    private String phone_number;

    @Column(name = "PASSWORD", length = 120)
    private String password;

    @Column(name = "M_PIN", length = 200)
    private String mpin;

    @Column(name = "IS_DISABLE", length = 200, columnDefinition = "varchar(200) default 'false'")
    private boolean is_disable;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transaction;

    public User(String email, String doc_number, String doc_type, String firstname, String lastname,
                String birth_place, LocalDate birth_date, String address, String phone_number, String password,
                String sex) {
        this.email = email;
        this.doc_number = doc_number;
        this.doc_type = doc_type;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_place = birth_place;
        this.birth_date = birth_date;
        this.address = address;
        this.phone_number = phone_number;
        this.password = password;
        this.sex = sex;
    }

}
