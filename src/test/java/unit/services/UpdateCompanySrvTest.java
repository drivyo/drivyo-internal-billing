package unit.services;

import Drivyo.sharedData.converters.CompanyConversionManager;
import Drivyo.sharedData.db.CompanyRepo;
import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.entity.CompanyEntity;
import Drivyo.sharedData.exceptions.HttpException;
import Drivyo.sharedData.services.company.UpdateCompanySrv;
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
public class UpdateCompanySrvTest {

    @Mock
    private CompanyRepo companyRepo;

    @Mock
    private CompanyConversionManager companyConversionManager;

    private UpdateCompanySrv updateCompanySrv;

    @BeforeEach
    public void setup(){
        this.updateCompanySrv = new UpdateCompanySrv(this.companyRepo, this.companyConversionManager);
    }

    @Test
    void givenExistingUserWhenUpdateThenUserIsUpdated(){

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepito","12345678X",true);
        final CompanyEntity companyEntityInput = new CompanyEntity(4L, "Pepito","12345678X",true);

        final CompanyDTO companyDTOExpected = new CompanyDTO(4L,"Pepito","12345678X",true);
        final CompanyEntity companyEntityExpected = new CompanyEntity(4L,"Pepito","12345678X",true);

        final Optional<CompanyEntity> optExtra = Optional.of(companyEntityInput);

        when(this.companyRepo.findById(4L)).thenReturn(optExtra);
        when(this.companyRepo.findByCifDifferentThanId("12345678X", 4L)).thenReturn(Optional.empty());
        when(this.companyRepo.findByNameDifferentThanId("Pepito", 4L)).thenReturn(Optional.empty());
        when(this.companyConversionManager.toEntity(companyDTOInput)).thenReturn(companyEntityInput);
        when(this.companyRepo.save(companyEntityInput)).thenReturn(companyEntityExpected);
        when(this.companyConversionManager.toDTO(companyEntityExpected)).thenReturn(companyDTOExpected);

        final CompanyDTO result = this.updateCompanySrv.update(companyDTOInput);

        assertEquals(result.getId(), companyDTOInput.getId());
        assertEquals(result.getName(), companyDTOInput.getName());
        assertEquals(result.getCif(), companyDTOInput.getCif());
    }

    @Test
    void givenOtherUserWithDuplicatedNameWhenUpdateThenThrowDTA005(){

        final String descripcionDUPLICATED_COMPANY_NAME = "Duplicated data: the given company name already exists in the database";
        final String codigoDUPLICATED_COMPANY_NAME = "DTA-005";

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe","05301542",true);
        final CompanyEntity companyEntityInput = new CompanyEntity(4L,"Pepe","05301542",true);
        final CompanyEntity companyEntityFound = new CompanyEntity(6L, "Pepe","05301543X",true);

        final Optional<CompanyEntity> optInput = Optional.of(companyEntityInput);
        final Optional<CompanyEntity> optFound = Optional.of(companyEntityFound);

        when(this.companyRepo.findByCifDifferentThanId("05301542", 4L)).thenReturn(Optional.empty());
        when(this.companyRepo.findByNameDifferentThanId("Pepe", 4L)).thenReturn(optFound);

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.updateCompanySrv.update(companyDTOInput));

        assertEquals(descripcionDUPLICATED_COMPANY_NAME, exceptionThrown.getGenericMessage());
        assertEquals(codigoDUPLICATED_COMPANY_NAME, exceptionThrown.getCode());

    }

    @Test
    void givenOtherUserWithDuplicatedCifWhenUpdateThenThrowDTA004(){

        final String descripcionDUPLICATED_COMPANY_CIF = "Duplicated data: the given company cif already exists in the database";
        final String codigoDUPLICATED_COMPANY_CIF = "DTA-004";

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe","05301542",true);
        final CompanyEntity companyEntityFound = new CompanyEntity(6L, "Pepito","05301542",true);

        final Optional<CompanyEntity> optFound = Optional.of(companyEntityFound);

        when(this.companyRepo.findByCifDifferentThanId("05301542", 4L)).thenReturn(optFound);

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.updateCompanySrv.update(companyDTOInput));

        assertEquals(descripcionDUPLICATED_COMPANY_CIF, exceptionThrown.getGenericMessage());
        assertEquals(codigoDUPLICATED_COMPANY_CIF, exceptionThrown.getCode());
    }

    @Test
    void givenNoIdWhenUpdateThenThrowDTA006(){

        final String descripcionUPDATE_WITHOUT_ID = "PUT operations require an id to identify the resource to be replaced.";
        final String codigoUPDATE_WITHOUT_ID = "DTA-006";

        final CompanyDTO companyDTOInput = new CompanyDTO(null,"Pepe","05301542",true);

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.updateCompanySrv.update(companyDTOInput));

        assertEquals(descripcionUPDATE_WITHOUT_ID, exceptionThrown.getGenericMessage());
        assertEquals(codigoUPDATE_WITHOUT_ID, exceptionThrown.getCode());
    }

    @Test
    void givenNoExistingCompanyThenThrowDTA001(){

        final String descripcionELEMENT_NOT_FOUND = "The element identified with the provided id could not be found.";
        final String codigoELEMENT_NOT_FOUND = "DTA-001";

        final CompanyDTO companyDTOInput = new CompanyDTO(1L,"Pepe","05301542",true);

        when(this.companyRepo.findByCifDifferentThanId("05301542", 1L)).thenReturn(Optional.empty());
        when(this.companyRepo.findByNameDifferentThanId("Pepe", 1L)).thenReturn(Optional.empty());
        when(this.companyRepo.findById(1L)).thenReturn(Optional.empty());

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.updateCompanySrv.update(companyDTOInput));

        assertEquals(descripcionELEMENT_NOT_FOUND, exceptionThrown.getGenericMessage());
        assertEquals(codigoELEMENT_NOT_FOUND, exceptionThrown.getCode());

    }




}
