package ma.tnbmaroc.terrain.service;


import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TerrainService {
    Terrain save(Terrain terrain);
    Page<Terrain> getAll(Pageable pageable);
    Terrain update(Terrain terrain);
    boolean delete(Long id);
    Terrain getById(Long id);
}
