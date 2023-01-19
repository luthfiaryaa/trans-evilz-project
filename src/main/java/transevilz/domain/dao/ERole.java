package transevilz.domain.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

public enum ERole {
    ROLE_USER,
    @JsonIgnore
    ROLE_ADMIN
}
