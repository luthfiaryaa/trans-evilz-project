package transevilz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    private LocalDate birth_date;
    private String address;
    private String phone_number;

    @NotBlank
    private String password;
    private String sex;

    private Set<String> role;
}
