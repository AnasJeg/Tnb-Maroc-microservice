package ma.tnbmaroc.redevable.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ma.tnbmaroc.terrain.domain.Terrain;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Redevable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String cin;

    private String password;
    @OneToMany(mappedBy = "redevable", cascade = CascadeType.ALL)
   @JsonIgnore
    private List<Terrain> terrains = new ArrayList<>();

    @ManyToOne
    private Role role;
}
