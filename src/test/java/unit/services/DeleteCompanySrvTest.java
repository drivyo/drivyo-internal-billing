package unit.services;

import Drivyo.sharedData.db.CompanyRepo;
import Drivyo.sharedData.services.company.DeleteCompanySrv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCompanySrvTest {

    @Mock
    private CompanyRepo companyRepo;

    private DeleteCompanySrv deleteCompanySrv;

    @BeforeEach
    public void setup() {
        this.deleteCompanySrv = new DeleteCompanySrv(this.companyRepo);
    }

    @Test
    void givenExistingUserWhenDeleteThenSucess(){

        doNothing().when(this.companyRepo).deleteById(1L);

        assertDoesNotThrow(() -> this.deleteCompanySrv.deleteById(1L));
    }
}
