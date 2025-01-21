package Drivyo.sharedData.controller.company;

import Drivyo.sharedData.services.company.DeleteCompanySrv;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/company")
public class DeleteCompanyController {

    private final DeleteCompanySrv deleteCompanySrv;

    @DeleteMapping(path = "{id}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public void delete(
            @PathVariable final Long id
    ) {
        this.deleteCompanySrv.deleteById(id);
    }
}

