package Drivyo.sharedData.converters.dto;

import Drivyo.sharedData.dto.RoleDTO;
import Drivyo.sharedData.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleDtoToEntityConverter implements Converter<RoleDTO, RoleEntity> {

    private final AuthorityDtoToEntityConverter authorityDtoToEntityConverter;

    @Override
    public RoleEntity convert(final RoleDTO dto) {
        final RoleEntity.RoleEntityBuilder roleEntityBuilder = RoleEntity.builder()
                .id(dto.getId())
                .name(dto.getRoleName());
        if (dto.getAuthorities() != null) {
            roleEntityBuilder.authorities(this.authorityDtoToEntityConverter.convert(dto.getAuthorities()));
        }
        return roleEntityBuilder.build();
    }

}