package ma.tnbmaroc.terrain.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tnbmaroc.categorie.domain.Categorie;
import ma.tnbmaroc.redevable.domain.Redevable;
import ma.tnbmaroc.taxe.domain.Taxe;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Terrain{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Double surface;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "redevable_id")
    private Redevable redevable;

    @OneToMany(mappedBy = "terrain")
    @JsonIgnore
    private List<Taxe> taxes= new ArrayList<>();
}
