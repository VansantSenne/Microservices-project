package fact.it.profielservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "profiel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean actief;
}
