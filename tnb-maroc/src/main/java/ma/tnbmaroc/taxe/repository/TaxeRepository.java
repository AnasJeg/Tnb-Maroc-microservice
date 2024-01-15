package ma.tnbmaroc.taxe.repository;

import ma.tnbmaroc.redevable.domain.Redevable;
import ma.tnbmaroc.taxe.domain.Taxe;
import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxeRepository extends JpaRepository<Taxe,Long> {
    Taxe findByTerrainNomAndAnnee(String nom,int annee);
    boolean existsByRedevableAndTerrainAndAnnee(Redevable redevable, Terrain terrain, int annee);

    Taxe findByAnnee(int year);

    List<Taxe> findByTerrain(Terrain terrain);

    List<Taxe> findByAnneeAndTerrain(int year, Terrain terrain);
}
