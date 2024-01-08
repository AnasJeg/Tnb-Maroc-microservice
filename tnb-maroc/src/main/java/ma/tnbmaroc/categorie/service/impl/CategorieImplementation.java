package ma.tnbmaroc.categorie.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.tnbmaroc.categorie.domain.Categorie;
import ma.tnbmaroc.categorie.repository.CategorieRepository;
import ma.tnbmaroc.categorie.service.CategorieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class CategorieImplementation implements CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieImplementation(CategorieRepository ct){
        this.categorieRepository=ct;
    }
    @Override
    public Categorie save(Categorie categorie) {
        log.info("save categorie : {}",categorie);
           return this.categorieRepository.save(categorie);
    }

    @Override
    public Page<Categorie> getAll(Pageable pageable) {
        log.info("get all categorie page {} size {}",pageable.getPageNumber(), pageable.getPageSize());
        return this.categorieRepository.findAll(pageable);
    }

    @Override
    public Categorie update(Categorie categorie) {
        log.info("update categorie {}",categorie);
       return this.categorieRepository.saveAndFlush(categorie);
    }

    @Override
    public boolean delete(Long id) {
       // Categorie ct=this.categorieRepository.findById(id).get();
        if(this.categorieRepository.findById(id).isPresent()){
            log.info("delete categorie {}",id);
            this.categorieRepository.delete(this.categorieRepository.findById(id).get());
            return  true;
        }
        log.error("error delete categorie {}",id);
        return false;
    }

    @Override
    public Categorie getById(Long id) {
        return this.categorieRepository.findById(id).get();
    }
}
