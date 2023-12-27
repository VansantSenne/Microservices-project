package fact.it.boekingservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table(name = "boekingLineOrder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoekingLineOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vluchtNummer;
    private BigDecimal prijs;
    private Integer hoeveelheid;
    private Long beoordelingId;
}
