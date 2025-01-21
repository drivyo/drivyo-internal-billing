package E2E;

import Drivyo.sharedData.controller.HttpExceptionController;
import Drivyo.sharedData.controller.company.AddCompanyController;
import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.exceptions.ErrorCatalog;
import Drivyo.sharedData.services.company.AddCompanySrv;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HttpExceptionController.class, AddCompanyController.class })
@WebMvcTest(AddCompanyController.class)
public class AddCompanySrvIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddCompanySrv addCompanySrv;

    @Test
    public void givenNoDuplicatedUserWhenCreateThenUserIsCreated() throws Exception{

        final CompanyDTO companyDTOInput = new CompanyDTO(5L,"Pepe","12345678C",true);
        final CompanyDTO companyDTOExpected = new CompanyDTO(1L, "Pepe","12345678C",true);

        when(addCompanySrv.create(companyDTOInput)).thenReturn(companyDTOExpected);

        this.mockMvc
                .perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 5,\"name\":\"Pepe\", \"cif\": \"12345678C\", \"enabled\": \"true\"}"))
                .andExpect(status().isOk());
    }
    @Test
    public void givenNoNameWhenCreateThenThrowDTA002() throws Exception {

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,null,"05301542L",true);

       when(addCompanySrv.create(companyDTOInput)).thenThrow(ErrorCatalog.INCOMPLETE_COMPANY_DATA_NO_NAME.getException());

        this.mockMvc
                .perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 4, \"cif\": \"05301542L\", \"enabled\": \"true\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenNoCifWhenCreateThenThrowDTA003() throws Exception {

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe",null,true);

        when(addCompanySrv.create(companyDTOInput)).thenThrow(ErrorCatalog.INCOMPLETE_COMPANY_DATA_NO_CIF.getException());

        this.mockMvc
                .perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":4 ,\"name\":\"Pepe\", \"enabled\": \"true\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenDuplicatedNameWhenCreateThenThrowDTA005() throws Exception {

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe","12345678C",true);

        when(addCompanySrv.create(companyDTOInput)).thenThrow(ErrorCatalog.DUPLICATED_COMPANY_NAME.getException());

        this.mockMvc
                .perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 4,\"name\":\"Pepe\", \"cif\": \"12345678C\", \"enabled\": \"true\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void givenDuplicatedCifWhenCreateThenThrowDTA004() throws Exception {

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe","12345678C",true);

        when(addCompanySrv.create(companyDTOInput)).thenThrow(ErrorCatalog.DUPLICATED_COMPANY_CIF.getException());

        this.mockMvc
                .perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 4,\"name\":\"Pepe\", \"cif\": \"12345678C\", \"enabled\": \"true\"}"))
                .andExpect(status().isConflict());
    }
}
