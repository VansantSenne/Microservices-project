package fact.it.beoordelingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "beoordeling")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Beoordeling {
    private String id;
    private Long profielId;
    private String vluchtNummer;
    private Integer ster;
    private String beschrijving;
}
