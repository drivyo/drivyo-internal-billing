package Drivyo.sharedData.services.company;


import Drivyo.sharedData.db.CompanyRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCompanySrv {

    private final CompanyRepo companyRepo;

    public void deleteById(final Long id) {
        this.companyRepo.deleteById(id);
    }
}


