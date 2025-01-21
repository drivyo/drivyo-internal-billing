package Drivyo.sharedData.dto;

import Drivyo.sharedData.dto.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private UserStatus active;
    private RoleDTO role;
    private String email;
    private String nif;

    public List<AuthorityDTO> getAuthoritiesDTOs() {
        return this.role.getAuthorities();
    }
}
