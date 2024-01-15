package ma.tnbmaroc.terrain.repository;

import ma.tnbmaroc.redevable.domain.Redevable;
import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Long> {
    Terrain findByNom(String nom);

    List<Terrain> findTerrainsByRedevableCin(String cin);

    List<Terrain> findByRedevableCinAndTaxesIsPaid(String cin, Boolean isPaid);

}
