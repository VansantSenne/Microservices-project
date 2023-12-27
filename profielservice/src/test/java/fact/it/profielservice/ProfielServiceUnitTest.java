package fact.it.profielservice;

import fact.it.profielservice.dto.ProfielResponse;
import fact.it.profielservice.model.Profiel;
import fact.it.profielservice.repository.ProfielRepository;
import fact.it.profielservice.service.ProfielService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfielServiceUnitTest {
    @InjectMocks
    private ProfielService  profielService;

    @Mock
    private ProfielRepository profielRepository;

    @Test
    public void testGetAllProfielen() {
        Profiel profiel1 = new Profiel();
        profiel1.setActief(true);


        when(profielRepository.findAll()).thenReturn(Arrays.asList(profiel1));

        List<ProfielResponse> profiel = profielService.getAllProfielen();

        assertEquals(1, profiel.size());
        verify(profielRepository, times(1)).findAll();
    }



}
