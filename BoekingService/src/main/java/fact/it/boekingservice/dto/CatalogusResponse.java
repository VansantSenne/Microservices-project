package fact.it.boekingservice.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogusResponse {
    private String vluchtNummer;
    private BigDecimal prijs;
    private boolean isVolgeboekt ;
}

