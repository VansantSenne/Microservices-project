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
        // Arrange

        Profiel profiel1 = new Profiel(true);
        Profiel profiel2 = new Profiel(false);

        profielRepository.save(profiel1);
        profielRepository.save(profiel2);
        // Act
        List<Profiel> profielen = profielService.getAllProfielen();

        // Assert
        assertEquals(2, profielen.size());
        assertTrue(profielen.get(0).getActief());
        assertTrue(profielen.get(1).getActief());
    }

    @Test
    public void testGetAllProfielenNoNull() {
        // Arrange
        Profiel profiel1 = new Profiel(true);
        Profiel profiel2 = new Profiel(false);

        profielRepository.save(profiel1);
        profielRepository.save(profiel2);
        // Act
        List<Profiel> profielen = profielService.getAllProfielen();

        // Assert
        assertNotNull(profielen);
    }
    @Test
    public void testGetAllProfielenEmpty() {
        // Arrange
        Profiel profiel1 = new Profiel(true);
        Profiel profiel2 = new Profiel(false);

        profielRepository.save(profiel1);
        profielRepository.save(profiel2);
        profielRepository.deleteAll();

        // Act
        List<Profiel> profielen = profielService.getAllProfielen();

        // Assert
        assertTrue(profielen.isEmpty());
    }


}
