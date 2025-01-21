package Drivyo.sharedData.controller.company;

import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.services.company.GetCompanySrv;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/company")
public class GetCompanyController {

    private final GetCompanySrv getCompanySrv;

    @GetMapping(path = "{id}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public CompanyDTO modify(
            @PathVariable final Long id
    ) {
        return this.getCompanySrv.getById(id);
    }
}
