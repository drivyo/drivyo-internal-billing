package Drivyo.sharedData.services.company;

import Drivyo.sharedData.converters.CompanyConversionManager;
import Drivyo.sharedData.db.CompanyRepo;
import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.entity.CompanyEntity;
import Drivyo.sharedData.exceptions.ErrorCatalog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCompanySrv {

    private final CompanyRepo companyRepo;
    private final CompanyConversionManager companyConversionManager;

    public CompanyDTO update(final CompanyDTO companyDTO) {

        if (companyDTO.getId() == null) {
            throw ErrorCatalog.UPDATE_WITHOUT_ID.getException();
        }

        final Optional<CompanyEntity> optExtra1 = this.companyRepo.findByCifDifferentThanId(companyDTO.getCif(),
                companyDTO.getId());
        if (optExtra1.isPresent()) {
            throw ErrorCatalog.DUPLICATED_COMPANY_CIF.getException();
        }
        final Optional<CompanyEntity> optExtra2 = this.companyRepo.findByNameDifferentThanId(companyDTO.getName(),
                companyDTO.getId());
        if(optExtra2.isPresent()){
            throw ErrorCatalog.DUPLICATED_COMPANY_NAME.getException();
        }

        final Optional<CompanyEntity> optCompanyEntity = this.companyRepo.findById(companyDTO.getId());

        if (optCompanyEntity.isEmpty()) {
            throw ErrorCatalog.ELEMENT_NOT_FOUND.getException();
        }

        return this.companyConversionManager.toDTO(this.companyRepo.save(this.companyConversionManager.toEntity(companyDTO)));
    }
}
