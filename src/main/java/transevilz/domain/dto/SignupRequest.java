package transevilz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    private String password;

    private Set<String> role;
}
