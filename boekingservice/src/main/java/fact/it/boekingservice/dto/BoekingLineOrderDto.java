package fact.it.boekingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BoekingLineOrderDto {
    private Long id;
    private String vluchtNummer;
    private BigDecimal prijs;
    private Integer hoeveelheid;
    private Optional<Long> beoordelingId;

}