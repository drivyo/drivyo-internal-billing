package Drivyo.sharedData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

import static java.util.Collections.emptyList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class RoleDTO {
    private Long id;
    private String roleName;
    @Builder.Default
    private List<AuthorityDTO> authorities = emptyList();
}
