package unit.services;

import Drivyo.sharedData.converters.CompanyConversionManager;
import Drivyo.sharedData.db.CompanyRepo;
import Drivyo.sharedData.dto.CompanyDTO;
import Drivyo.sharedData.entity.CompanyEntity;
import Drivyo.sharedData.exceptions.HttpException;
import Drivyo.sharedData.services.company.AddCompanySrv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddCompanySrvTest {

    @Mock
    private CompanyRepo companyRepo;

    @Mock
    private CompanyConversionManager companyConversionManager;

    private AddCompanySrv addCompanySrv;

    @BeforeEach
    public void setup(){
        this.addCompanySrv = new AddCompanySrv(this.companyRepo, this.companyConversionManager);
    }

    @Test
    void givenNoDuplicatedUserWhenCreateThenUserIsCreated(){

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe","12345678C",true);
        final CompanyEntity companyEntity = new CompanyEntity(null, "Pepe","12345678C",true);

        final CompanyDTO companyDTOExpected = new CompanyDTO(1L,"Pepe","12345678C",true);
        final CompanyEntity companyEntityExpected = new CompanyEntity(1L, "Pepe","12345678C",true);

        when(this.companyRepo.findByCif("12345678C")).thenReturn(Optional.empty());
        when(this.companyRepo.findByName("Pepe")).thenReturn(Optional.empty());
        when(this.companyConversionManager.toEntity(companyDTOInput)).thenReturn(companyEntity);
        when(this.companyRepo.save(companyEntity)).thenReturn(companyEntityExpected);
        when(this.companyConversionManager.toDTO(companyEntityExpected)).thenReturn(companyDTOExpected);

        final CompanyDTO result = this.addCompanySrv.create(companyDTOInput);

        assertEquals(result.getName(), companyDTOInput.getName());
        assertEquals(result.getCif(), companyDTOInput.getCif());
        assertEquals(result.getEnabled(), companyDTOInput.getEnabled());
    }

    @Test
    void givenNoNameWhenCreateThenThrowDTA002(){

        final String descripcionINCOMPLETE_COMPANY_DATA_NO_NAME = "Incomplete data: name should not be empty";
        final String codigoINCOMPLETE_COMPANY_DATA_NO_NAME = "DTA-002";

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,null,"05301542L",true);

        when(this.companyRepo.findByCif("05301542L")).thenReturn(Optional.empty());
        when(this.companyRepo.findByName(null)).thenReturn(Optional.empty());

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.addCompanySrv.create(companyDTOInput));

        assertEquals(descripcionINCOMPLETE_COMPANY_DATA_NO_NAME, exceptionThrown.getGenericMessage());
        assertEquals(codigoINCOMPLETE_COMPANY_DATA_NO_NAME, exceptionThrown.getCode());

    }

    @Test
    void givenNoCifWhenCreateThenThrowDTA003(){

        final String descripcionINCOMPLETE_COMPANY_DATA_NO_CIF = "Incomplete data: cif should not be empty";
        final String codigoINCOMPLETE_COMPANY_DATA_NO_CIF = "DTA-003";

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe",null,true);

        when(this.companyRepo.findByCif(null)).thenReturn(Optional.empty());
        when(this.companyRepo.findByName("Pepe")).thenReturn(Optional.empty());

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.addCompanySrv.create(companyDTOInput));

        assertEquals(descripcionINCOMPLETE_COMPANY_DATA_NO_CIF, exceptionThrown.getGenericMessage());
        assertEquals(codigoINCOMPLETE_COMPANY_DATA_NO_CIF, exceptionThrown.getCode());

    }

    @Test
    void givenDuplicatedNameWhenCreateThenThrowDTA005(){

        final String descripcionDUPLICATED_COMPANY_NAME = "Duplicated data: the given company name already exists in the database";
        final String codigoDUPLICATED_COMPANY_NAME = "DTA-005";

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe","05301542",true);
        final CompanyEntity companyEntity = new CompanyEntity(4L, "Pepe","12345678C",true);

        final Optional<CompanyEntity> optExtra = Optional.of(companyEntity);

        when(this.companyRepo.findByCif("05301542")).thenReturn(Optional.empty());
        when(this.companyRepo.findByName("Pepe")).thenReturn(optExtra);

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.addCompanySrv.create(companyDTOInput));

        assertEquals(descripcionDUPLICATED_COMPANY_NAME, exceptionThrown.getGenericMessage());
        assertEquals(codigoDUPLICATED_COMPANY_NAME, exceptionThrown.getCode());

    }

    @Test
    void givenDuplicatedCifWhenCreateThenThrowDTA004(){

        final String descripcionDUPLICATED_COMPANY_CIF = "Duplicated data: the given company cif already exists in the database";
        final String codigoDUPLICATED_COMPANY_CIF = "DTA-004";

        final CompanyDTO companyDTOInput = new CompanyDTO(4L,"Pepe","05301542",true);
        final CompanyEntity companyEntity = new CompanyEntity(4L, "Francisco","05301542",true);

        final Optional<CompanyEntity> optExtra = Optional.of(companyEntity);

        when(this.companyRepo.findByCif("05301542")).thenReturn(optExtra);

        final HttpException exceptionThrown = assertThrows(HttpException.class, () -> this.addCompanySrv.create(companyDTOInput));

        assertEquals(descripcionDUPLICATED_COMPANY_CIF, exceptionThrown.getGenericMessage());
        assertEquals(codigoDUPLICATED_COMPANY_CIF, exceptionThrown.getCode());
    }
}
