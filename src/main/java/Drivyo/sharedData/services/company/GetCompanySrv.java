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
public class GetCompanySrv {

    private final CompanyRepo companyRepo;
    private final CompanyConversionManager companyConversionManager;

    public CompanyDTO getById(final Long id) {
        final CompanyDTO result;
        final Optional<CompanyEntity> optExtra = this.companyRepo.findById(id);
        if (optExtra.isEmpty()) {
            throw ErrorCatalog.ELEMENT_NOT_FOUND.getException();
        }
        result = this.companyConversionManager.toDTO(optExtra.get());
        return result;
    }

}
