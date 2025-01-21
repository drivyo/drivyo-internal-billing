package Drivyo.sharedData.converters.entity;

import Drivyo.sharedData.dto.RoleDTO;
import Drivyo.sharedData.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleEntityToDtoConverter implements Converter<RoleEntity, RoleDTO> {

    private final AuthorityEntityToDtoConverter authorityEntityToDtoConverter;

    @Override
    public RoleDTO convert(final RoleEntity entity) {
        final RoleDTO.RoleDTOBuilder roleDTOBuilder = RoleDTO.builder()
                .id(entity.getId())
                .roleName(entity.getName());
        if (entity.getAuthorities() != null && !entity.getAuthorities().isEmpty()) {
            roleDTOBuilder.authorities(this.authorityEntityToDtoConverter.convert(entity.getAuthorities()));

        }
        return roleDTOBuilder.build();
    }

}

