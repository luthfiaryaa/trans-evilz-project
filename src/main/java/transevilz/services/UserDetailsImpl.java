package transevilz.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import transevilz.domain.dao.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    //private String citizenship;
    private String doc_type;
    private String doc_number;
    private String firstname;
    private String lastname;
    private String birth_place;
    private LocalDate birth_date;
    private String address;
    private String sex;
    private String phone_number;
    private String mpin;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password, String doc_type, String doc_number,
                           String firstname, String lastname, String birth_place, LocalDate birth_date, String address,
                           String sex, String phone_number, String mpin,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.doc_type = doc_type;
        this.doc_number = doc_number;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_place = birth_place;
        this.birth_date = birth_date;
        this.address = address;
        this.sex = sex;
        this.phone_number = phone_number;
        this.mpin = mpin;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

//        return new UserDetailsImpl(
//                user.getId(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities);
        return new UserDetailsImpl(
                  user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                //user.getCitizenship(),
                user.getDoc_type(),
                user.getDoc_number(),
                user.getFirstname(),
                user.getLastname(),
                user.getBirth_place(),
                user.getBirth_date(),
                user.getAddress(),
                user.getSex(),user.getPhone_number(),
                user.getMpin(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getMpin() {
        return mpin;
    }

//    public String getCitizenship() {
//        return citizenship;
//    }

    public String getDoc_type() {
        return doc_type;
    }

    public String getDoc_number() {
        return doc_number;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getFullname(){
        return firstname + " " + lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
