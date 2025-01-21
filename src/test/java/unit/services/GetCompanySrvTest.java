package unit.services;

import Drivyo.sharedData.converters.CompanyConversionManager;
import Drivyo.sharedData.db.CompanyRepo;
import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.entity.CompanyEntity;
import Drivyo.sharedData.exceptions.HttpException;
import Drivyo.sharedData.services.company.GetCompanySrv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCompanySrvTest {

    @Mock
    private CompanyRepo companyRepo;

    @Mock
    private CompanyConversionManager companyConversionManager;

    private GetCompanySrv getCompanySrv;

    @BeforeEach
    public void setup(){
        this.getCompanySrv = new GetCompanySrv(this.companyRepo, this.companyConversionManager);
    }

    @Test
    void givenExistingUserWhenGetThenCompanyIsReturned(){

        final String expectedName = "Pepe";
        final String expectedCIF = "12345678C";
        final boolean expectedEnabled = true;

        final CompanyDTO companyDTOExpected = new CompanyDTO(1L,"Pepe","12345678C",true);
        final CompanyEntity companyEntityExpected = new CompanyEntity(1L, "Pepe","12345678C",true);

        final Optional<CompanyEntity> optExtra = Optional.of(companyEntityExpected);

        when(this.companyRepo.findById(1L)).thenReturn(optExtra);
        when(this.companyConversionManager.toDTO(companyEntityExpected)).thenReturn(companyDTOExpected);

        final CompanyDTO result = this.getCompanySrv.getById(1L);

        assertEquals(result.getName(), expectedName);
        assertEquals(result.getCif(), expectedCIF);
        assertEquals(result.getEnabled(), expectedEnabled);
    }

    @Test
    void givenWrongUserIdWhenGetThen404IsReturned(){

        final String descripcionELEMENT_NOT_FOUND = "The element identified with the provided id could not be found.";
        final String codigoELEMENT_NOT_FOUND = "DTA-001";

        when(this.companyRepo.findById(1L)).thenReturn(Optional.empty());

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.getCompanySrv.getById(1L));

        assertEquals(descripcionELEMENT_NOT_FOUND, exceptionThrown.getGenericMessage());
        assertEquals(codigoELEMENT_NOT_FOUND, exceptionThrown.getCode());
    }



}
