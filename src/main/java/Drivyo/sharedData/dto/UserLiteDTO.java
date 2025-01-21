package Drivyo.sharedData.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserLiteDTO {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String nif;
}