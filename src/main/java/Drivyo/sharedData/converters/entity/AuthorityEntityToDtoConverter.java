package Drivyo.sharedData.converters.entity;

import Drivyo.sharedData.dto.AuthorityDTO;
import Drivyo.sharedData.entity.AuthorityEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AuthorityEntityToDtoConverter implements Converter<AuthorityEntity, AuthorityDTO> {

    @Override
    public AuthorityDTO convert(final AuthorityEntity entity) {
        return AuthorityDTO.builder()
                .id(entity.getId())
                .authorityName(entity.getName())
                .build();
    }

    public List<AuthorityDTO> convert(List<AuthorityEntity> authorities) {
        return authorities.stream().map(this::convert).toList();
    }
}
