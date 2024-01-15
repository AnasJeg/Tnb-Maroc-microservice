package ma.tnbmaroc.terrain.service;


import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TerrainService {
    Terrain save(Terrain terrain);

    Terrain findByNom(String nom);
    Page<Terrain> getAll(Pageable pageable);
    Terrain update(Terrain terrain);
    boolean delete(Long id);
    Terrain getById(Long id);

    List<Terrain> getAllByRedevable(String cin);

    List<Terrain> getTerrainsByRedevableCin(String cin, boolean isPaid);
}
