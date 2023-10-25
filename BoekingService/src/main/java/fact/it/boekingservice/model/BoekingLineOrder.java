package fact.it.boekingservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
    private String code;
    private BigDecimal prijs;
    private Integer hoeveelheid;
}
