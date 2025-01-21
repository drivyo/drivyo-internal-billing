package Drivyo.sharedData.converters.dto;

import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CompanyDtoToEntityConverter implements Converter<CompanyDTO, CompanyEntity> {

    @Override
    public CompanyEntity convert(final CompanyDTO dto) {
        return CompanyEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .cif(dto.getCif())
                .enabled(dto.getEnabled())
                .build();
    }

}
