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
public class AddCompanySrv {

    private final CompanyRepo companyRepo;
    private final CompanyConversionManager companyConversionManager;


    public CompanyDTO create(final CompanyDTO companyDTO) {

        final Optional<CompanyEntity> optExtra1 = this.companyRepo.findByCif(companyDTO.getCif());
        if (optExtra1.isPresent()) {
            throw ErrorCatalog.DUPLICATED_COMPANY_CIF.getException();
        }
        final Optional<CompanyEntity> optExtra2 = this.companyRepo.findByName(companyDTO.getName());
        if (optExtra2.isPresent()) {
            throw ErrorCatalog.DUPLICATED_COMPANY_NAME.getException();
        }
        companyDTO.setId(null);
        if(companyDTO.getName() == null  || companyDTO.getName().isEmpty()){
            throw ErrorCatalog.INCOMPLETE_COMPANY_DATA_NO_NAME.getException();
        }else if (companyDTO.getCif() == null  || companyDTO.getCif().isEmpty()) {
            throw ErrorCatalog.INCOMPLETE_COMPANY_DATA_NO_CIF.getException();
        }
        return this.companyConversionManager.toDTO(this.companyRepo.save(this.companyConversionManager.toEntity(companyDTO)));
    }
}
