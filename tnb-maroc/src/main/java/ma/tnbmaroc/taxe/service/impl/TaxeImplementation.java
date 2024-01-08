package ma.tnbmaroc.taxe.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.tnbmaroc.taxe.domain.Taxe;
import ma.tnbmaroc.taxe.repository.TaxeRepository;
import ma.tnbmaroc.taxe.service.TaxeService;
import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class TaxeImplementation implements TaxeService {

    private final TaxeRepository taxeRepository;
    public TaxeImplementation(TaxeRepository tr){
        this.taxeRepository=tr;
    }
    @Override
    public Taxe save(Taxe taxe) {
        log.info("save taxe : {}",taxe);
        return this.taxeRepository.save(taxe);
    }

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
        
        return null;
    }


}
