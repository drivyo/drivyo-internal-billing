package E2E;

import Drivyo.sharedData.BillingApplication;
import Drivyo.sharedData.dto.CompanyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BillingApplication.class})
//@WebMvcTest(GetCompanyController.class)
@WebAppConfiguration
public class GetCompanySrvIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenExistingUserWhenGetThenCompanyIsReturned() throws Exception {

        final CompanyDTO companyDTOExpected = new CompanyDTO(1L,"Pepe","12345678C",true);

        final Long expectedId = 1L;
        final String expectedName = "Pepe";
        final String expectedCif = "12345678C";
        final boolean expectedEnabled = true;

        //when(this.getCompanySrv.getById(1L)).thenReturn(companyDTOExpected);

        this.mockMvc
                .perform(get("/company/1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cif").value(expectedCif))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enabled").value(expectedEnabled));
    }

    @Test
    public void givenWrongUserIdWhenGetThen404IsReturned() throws Exception {

        this.mockMvc
                .perform(get("/company/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
