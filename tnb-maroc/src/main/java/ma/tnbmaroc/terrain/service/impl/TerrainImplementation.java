package ma.tnbmaroc.terrain.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.tnbmaroc.redevable.repository.RedevableRepository;
import ma.tnbmaroc.terrain.domain.Terrain;
import ma.tnbmaroc.terrain.dto.TerrainWithPaymentStatus;
import ma.tnbmaroc.terrain.repository.TerrainRepository;
import ma.tnbmaroc.terrain.service.TerrainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TerrainImplementation implements TerrainService {

    private final TerrainRepository terrainRepository;

    private final RedevableRepository redevableRepository;

    public TerrainImplementation(TerrainRepository terrainRepository,RedevableRepository redevableRepository) {
        this.terrainRepository = terrainRepository;
        this.redevableRepository=redevableRepository;
    }

    @Override
    public Terrain save(Terrain terrain) {
        log.info("save terrain : {}",terrain);
        return this.terrainRepository.save(terrain);
    }

    @Override
    public Terrain findByNom(String nom) {
       // this.redevableRepository.findByCin(terrain)
        //String userCIN = terrain.getRedevable().setId(userCIN);
        log.info("save terrain: {}", nom);
        return this.terrainRepository.findByNom(nom);
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

    @Override
    public List<Terrain> getAllByRedevable(String cin) {
        return this.terrainRepository.findTerrainsByRedevableCin(cin);
    }

    @Override
    public List<Terrain> getTerrainsByRedevableCin(String cin, boolean isPaid) {
        return terrainRepository.findByRedevableCinAndTaxesIsPaid(cin, isPaid);
    }
}
