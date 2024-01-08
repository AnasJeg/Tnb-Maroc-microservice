package ma.tnbmaroc.taux.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tnbmaroc.categorie.domain.Categorie;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Taux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double prix;

    @ManyToOne
    private Categorie categorie;
}
