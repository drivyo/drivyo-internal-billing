package Drivyo.sharedData.controller.company;

import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.services.company.AddCompanySrv;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/company")
public class AddCompanyController {

    private final AddCompanySrv addCompanySrv;

    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"})
    public CompanyDTO create(
            @RequestBody final CompanyDTO companyDTO
    ) {
        return this.addCompanySrv.create(companyDTO);
    }
}
