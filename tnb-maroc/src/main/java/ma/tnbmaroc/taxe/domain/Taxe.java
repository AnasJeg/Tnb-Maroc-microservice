package ma.tnbmaroc.taxe.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tnbmaroc.redevable.domain.Redevable;
import ma.tnbmaroc.taux.domain.Taux;
import ma.tnbmaroc.terrain.domain.Terrain;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taxe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private Double montant;
    private int annee;

    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "redevable_id")
    private Redevable redevable;

    @ManyToOne
    @JoinColumn(name = "terrain_id")
    private Terrain terrain;

    @ManyToOne
    @JoinColumn(name = "taux_id")
    private Taux taux;

}
