package Drivyo.sharedData.converters.entity;

import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CompanyEntityToDtoConverter implements Converter<CompanyEntity, CompanyDTO> {

    @Override
    public CompanyDTO convert(final CompanyEntity entity) {
        return CompanyDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cif(entity.getCif())
                .enabled(entity.getEnabled())
                .build();
    }
}