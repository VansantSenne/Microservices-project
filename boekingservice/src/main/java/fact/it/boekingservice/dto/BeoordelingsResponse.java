package fact.it.boekingservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeoordelingsResponse {
    private Long id;
    private String vluchtNummer;
    private Long profielId;
    private int ster;
    private String opmerking;
}
