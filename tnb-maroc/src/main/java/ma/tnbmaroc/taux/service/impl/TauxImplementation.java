package ma.tnbmaroc.taux.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.tnbmaroc.taux.domain.Taux;
import ma.tnbmaroc.taux.repository.TauxRepository;
import ma.tnbmaroc.taux.service.TauxService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class TauxImplementation implements TauxService {

    private final TauxRepository tauxRepository;

    public TauxImplementation(TauxRepository tr){
        this.tauxRepository=tr;
    }
    @Override
    public Taux save(Taux taux) {
        log.info("save taux : {}",taux);
        return this.tauxRepository.save(taux);
    }

    @Override
    public Page<Taux> getAll(Pageable pageable) {
        log.info("get all taux page {} size {}",pageable.getPageNumber(), pageable.getPageSize());
        return this.tauxRepository.findAll(pageable);
    }

    @Override
    public Taux update(Taux taux) {
        log.info("update taux {}",taux);
        return this.tauxRepository.saveAndFlush(taux);
    }

    @Override
    public boolean delete(Long id) {
        if(this.tauxRepository.findById(id).isPresent()){
            log.info("delete taux {}",id);
            this.tauxRepository.delete(this.tauxRepository.findById(id).get());
            return  true;
        }
        log.error("error delete taux {}",id);
        return false;
    }

    @Override
    public Taux getById(Long id) {
        return this.tauxRepository.findById(id).get();
    }

    @Override
    public Taux findByCategorieLabel(String cat) {
        return this.tauxRepository.findByCategorieLabel(cat);
    }
}
