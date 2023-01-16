package transevilz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Id
    private Long id;
    private String email;
    private String fullname;
    private Boolean mpinFlag;

    public void setMpinFlag(Boolean mpinFlag) {
        setMpinFlag(mpinFlag != null ? true:false);
    }

//    public void setMpinFlags(Boolean mpinFlag) {
//        if (mpinFlag != null){
//            mpinFlag = true;
//        }
//    }

}
