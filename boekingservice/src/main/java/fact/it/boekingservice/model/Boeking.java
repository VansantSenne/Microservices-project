package fact.it.boekingservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "boekingen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boeking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boekingNummer;
    private Long profielId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BoekingLineOrder> boekingLineOrdersList;
}
