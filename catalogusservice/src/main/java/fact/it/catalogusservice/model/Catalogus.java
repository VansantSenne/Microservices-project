package fact.it.catalogusservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "catalogus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catalogus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vluchtNummer;
    private BigDecimal prijs;
    private boolean isVolgeboekt;
}
