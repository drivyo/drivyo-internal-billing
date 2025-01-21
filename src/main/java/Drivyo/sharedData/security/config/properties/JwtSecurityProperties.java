package Drivyo.sharedData.security.config.properties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "backoffice.security.jwt")
@Data
@Validated
public class JwtSecurityProperties {

    private boolean enabled = true;
    private int jwtExpirationMinutes = 480;

    @NotEmpty
    private String publicKey;

    @NotEmpty
    private String privateKey;

}