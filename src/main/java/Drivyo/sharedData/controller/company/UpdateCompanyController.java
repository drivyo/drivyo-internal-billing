package Drivyo.sharedData.controller.company;

import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.services.company.UpdateCompanySrv;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/company")
public class UpdateCompanyController {

    private final UpdateCompanySrv updateCompanySrv;

    @PutMapping(path = "{id}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public CompanyDTO create(
            @PathVariable final Long id,
            @RequestBody final CompanyDTO companyDTO
    ) {
        companyDTO.setId(id);
        return this.updateCompanySrv.update(companyDTO);
    }
}
