package ma.tnbmaroc.taxe.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.tnbmaroc.redevable.domain.Redevable;
import ma.tnbmaroc.redevable.service.RedevableService;
import ma.tnbmaroc.taux.domain.Taux;
import ma.tnbmaroc.taux.service.TauxService;
import ma.tnbmaroc.taxe.domain.Taxe;
import ma.tnbmaroc.taxe.repository.TaxeRepository;
import ma.tnbmaroc.taxe.service.TaxeService;
import ma.tnbmaroc.terrain.domain.Terrain;
import ma.tnbmaroc.terrain.service.TerrainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TaxeImplementation implements TaxeService {

    private final TaxeRepository taxeRepository;

    private final TauxService tauxService;
    private final RedevableService redevableService;
    private final TerrainService terrainService;
    public TaxeImplementation(TaxeRepository tr,TauxService tauxService1, RedevableService redevableService,TerrainService terrainService){
        this.taxeRepository=tr;
        this.tauxService=tauxService1;
        this.redevableService=redevableService;
        this.terrainService=terrainService;
    }
    @Override
    public Taxe save(Taxe taxe) {
        Redevable redevable = redevableService.findByCin(taxe.getRedevable().getCin());
        Terrain terrain = terrainService.getById(taxe.getTerrain().getId());
        Taux taux = tauxService.findByCategorieLabel(terrain.getCategorie().getLabel());
        log.info("save taxe - Input - redevable: {}, terrain: {}, taux: {}", redevable, terrain, taux);
        taxe.setRedevable(redevable);
        taxe.setTerrain(terrain);
        taxe.setTaux(taux);

        if (taux != null) {
            taxe.setMontant(terrain.getSurface() * taux.getPrix());
            log.info("save taxe - Calculated montant: {}", taxe.getMontant());
        } else {
            log.warn("save taxe - Taux not found for category label: {}", terrain.getCategorie().getLabel());
        }
        log.info("save taxe - Final - taxe: {}", taxe);
        return this.taxeRepository.save(taxe);
    }

  /*
      @Override
    public Taxe save(Taxe taxe) {
        Redevable redevable = redevableService.findByCin(taxe.getRedevable().getCin());
        Terrain terrain = terrainService.getById(taxe.getTerrain().getId());

        Taux taux = tauxService.findByCategorieLabel(terrain.getCategorie().getLabel());
        taxe.setRedevable(redevable);
        taxe.setTerrain(terrain);
        taxe.setTaux(taux);
        taxe.setMontant(terrain.getSurface() * taux.getPrix());
        log.info("save taxe : {}", taxe);
        return this.taxeRepository.save(taxe);
    }

   */

    @Override
    public Page<Taxe> getAll(Pageable pageable) {
        log.info("get all taxe page {} size {}",pageable.getPageNumber(), pageable.getPageSize());
        return this.taxeRepository.findAll(pageable);
    }

    @Override
    public Taxe update(Taxe taxe) {
        log.info("update taxe {}",taxe);
        return this.taxeRepository.saveAndFlush(taxe);
    }

    @Override
    public boolean delete(Long id) {
        if(this.taxeRepository.findById(id).isPresent()){
            log.info("delete taxe {}",id);
            this.taxeRepository.delete(this.taxeRepository.findById(id).get());
            return  true;
        }
        log.error("error delete taxe {}",id);
        return false;
    }

    @Override
    public Taxe getById(Long id) {
        return this.taxeRepository.findById(id).get();
    }

    @Override
    public Double TaxeTnb(String cin, int annee, Terrain terrain) {
        Redevable redevable = redevableService.findByCin(cin);
        Taux taux = tauxService.findByCategorieLabel(terrain.getCategorie().getLabel());

        if (taxeRepository.existsByRedevableAndTerrainAndAnnee(redevable, terrain, annee)) {
            log.warn("Tax for anne {} on terrain {} has already been paid !", annee, terrain.getId());
            return null;
        }

        Double taxeAmount = terrain.getSurface() * taux.getPrix();
        log.info("tax for annee {} on terrain {}: {}", annee, terrain.getId(), taxeAmount);
        return taxeAmount;
    }

    @Override
    public Taxe findByTnbYear(int tnbYear){
        return taxeRepository.findByAnnee(tnbYear);
    }
    @Override
    public List<Taxe> findByTerrain(String nom){
        Terrain terrain = terrainService.findByNom(nom);
        return taxeRepository.findByTerrain(terrain);
    }

    @Override
    public List<Taxe> findByTnbYearAndTerrain(int year, String nom){
        Terrain terrain = terrainService.findByNom(nom);
        return taxeRepository.findByAnneeAndTerrain(year, terrain);
    }

}
