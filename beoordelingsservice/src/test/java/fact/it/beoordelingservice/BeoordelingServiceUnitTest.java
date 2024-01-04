package fact.it.beoordelingservice;

import fact.it.beoordelingservice.dto.BeoordelingsResponse;
import fact.it.beoordelingservice.model.Beoordeling;
import fact.it.beoordelingservice.repository.BeoordelingsRepository;
import fact.it.beoordelingservice.service.BeoordelingsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeoordelingServiceUnitTest {

    @InjectMocks
    private BeoordelingsService beoordelingsService;

    @Mock
    private BeoordelingsRepository beoordelingsRepository;

    @Test
    public void testGetAllBeoordelingen() {
        Beoordeling beoordeling = new Beoordeling();
        beoordeling.setProfielId(Long.valueOf(3));
        beoordeling.setVluchtNummer("ABC123");
        beoordeling.setSter(5);
        beoordeling.setBeschrijving("mooi");


        when(beoordelingsRepository.findAll()).thenReturn(Arrays.asList(beoordeling));

        List<BeoordelingsResponse> beoordelingen = beoordelingsService.getAllBeoordelingen();

        assertEquals(1, beoordelingen.size());
        verify(beoordelingsRepository, times(1)).findAll();
    }

}
