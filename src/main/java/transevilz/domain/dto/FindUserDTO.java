package transevilz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import transevilz.domain.dao.User;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindUserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String doc_type;
}
