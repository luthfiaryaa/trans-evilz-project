package transevilz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String doc_type;
    private String doc_number;
    private String firstname;
    private String lastname;
    private String birth_place;
    private LocalDateTime birth_date;
    private String address;



    private String sex;
    @NotBlank
    private String password;
    private String phone_number;
    private Set<String> role;
}
