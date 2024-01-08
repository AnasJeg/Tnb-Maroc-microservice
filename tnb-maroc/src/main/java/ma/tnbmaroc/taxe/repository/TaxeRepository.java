package ma.tnbmaroc.taxe.repository;

import ma.tnbmaroc.taxe.domain.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxeRepository extends JpaRepository<Taxe,Long> {
    Taxe findByTerrainNomAndAnnee(String nom,int annee);
}
