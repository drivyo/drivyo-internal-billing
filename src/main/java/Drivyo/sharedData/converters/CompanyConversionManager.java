package Drivyo.sharedData.converters;

import Drivyo.sharedData.converters.dto.CompanyDtoToEntityConverter;
import Drivyo.sharedData.converters.entity.CompanyEntityToDtoConverter;
import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CompanyConversionManager {

    private final CompanyDtoToEntityConverter companyDtoToEntityConverter;
    private final CompanyEntityToDtoConverter companyEntityToDtoConverter;

    public CompanyEntity toEntity(final CompanyDTO dto) {
        return this.companyDtoToEntityConverter.convert(dto);
    }

    public CompanyDTO toDTO(final CompanyEntity entity) {
        return this.companyEntityToDtoConverter.convert(entity);
    }
}