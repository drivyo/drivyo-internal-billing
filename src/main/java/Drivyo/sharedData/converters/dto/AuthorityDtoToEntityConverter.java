package Drivyo.sharedData.converters.dto;

import Drivyo.sharedData.dto.AuthorityDTO;
import Drivyo.sharedData.entity.AuthorityEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AuthorityDtoToEntityConverter implements Converter<AuthorityDTO, AuthorityEntity> {

    @Override
    public AuthorityEntity convert(final AuthorityDTO dto) {
        return AuthorityEntity.builder()
                .id(dto.getId())
                .name(dto.getAuthorityName())
                .build();
    }

    public List<AuthorityEntity> convert(final List<AuthorityDTO> authorities) {
        return authorities.stream().map(this::convert).toList();
    }
}