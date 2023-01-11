package transevilz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import transevilz.domain.dao.User;

import javax.persistence.Id;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    //private User user;
    @Id
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String mpin;

    private List<User> users;
}
