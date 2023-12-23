package fact.it.boekingservice.dto;
import fact.it.boekingservice.model.BoekingLineOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoekingRequest {
    private Long profielId;
    private List<BoekingLineOrderDto> boekingLineOrderDtoList;
}
