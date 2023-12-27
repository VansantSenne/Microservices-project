package fact.it.profielservice;

import fact.it.profielservice.dto.InventoryResponse;
import fact.it.profielservice.model.Profiel;
import fact.it.profielservice.repository.InventoryRepository;
import fact.it.profielservice.service.InventoryService;
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


        when(catalogusRepository.findAll()).thenReturn(Arrays.asList(catalogus1));

        List<ProfielResponse> profiel = profielService.getAllProfielen();

        assertEquals(1, profiel.size());
        verify(profielRepository, times(1)).findAll();
    }



}
