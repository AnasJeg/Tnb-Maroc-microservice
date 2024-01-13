package ma.tnbmaroc.terrain.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.tnbmaroc.terrain.domain.Terrain;
import ma.tnbmaroc.terrain.repository.TerrainRepository;
import ma.tnbmaroc.terrain.service.TerrainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class TerrainImplementation implements TerrainService {

    private final TerrainRepository terrainRepository;

    public TerrainImplementation(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }

    @Override
    public Terrain save(Terrain terrain) {
        log.info("save terrain : {}",terrain);
        return this.terrainRepository.save(terrain);
    }

    @Override
    public Page<Terrain> getAll(Pageable pageable) {
        log.info("get all terrain page {} size {}",pageable.getPageNumber(), pageable.getPageSize());
        return this.terrainRepository.findAll(pageable);
    }

    @Override
    public Terrain update(Terrain terrain) {
        log.info("update terrain {}",terrain);
        return this.terrainRepository.saveAndFlush(terrain);
    }

    @Override
    public boolean delete(Long id) {
        if(this.terrainRepository.findById(id).isPresent()){
            log.info("delete terrain {}",id);
            this.terrainRepository.delete(this.terrainRepository.findById(id).get());
            return  true;
        }
        log.error("error delete terrain {}",id);
        return false;
    }

    @Override
    public Terrain getById(Long id) {
        return this.terrainRepository.findById(id).get();
    }
}