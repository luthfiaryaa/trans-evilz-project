package transevilz.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import transevilz.domain.dao.User;

import java.util.List;
import java.util.Optional;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private Optional<User> user;
    private String accessToken;
    private String type = "Bearer";
    private String expiredIn = "aaa";
//    private Long id;
//    private String username;
//    //    private String picture;
//    private String email;
//    //    private String citizenship;
//    private String doc_type;
//    private String doc_number;
//    private String firstname;
//    private String lastname;
//    private String birth_place;
//    private LocalDate birth_date;
//    private String address;
//    private String sex;
//    private Long phone_number;
//    private String password;
//    private String mpin;
//    private List<String> roles;

//    public JwtResponse(String jwt, Long id, String username, String email, String citizenship, String document,
//                       String firstname, String lastname, String placeofbirth, String address, String gender,
//                       LocalDate dateofbirth, Long phone, String mpin,
//                       List<String> roles) {
//        this.token = jwt;
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.citizenship = citizenship;
//        this.document = document;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.placeofbirth = placeofbirth;
//        this.dateofbirth = dateofbirth;
//        this.address = address;
//        this.gender = gender;
//        this.phone = phone;
//        this.mpin = mpin;
//        this.roles = roles;
//    }

}
