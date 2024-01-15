package ma.tnbmaroc.taxe.service;


import ma.tnbmaroc.taxe.domain.Taxe;
import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaxeService {
    Taxe save(Taxe taxe);
    Page<Taxe> getAll(Pageable pageable);
    Taxe update(Taxe taxe);
    boolean delete(Long id);
    Taxe getById(Long id);

    Double TaxeTnb(String cin, int annee, Terrain terrain);

    Taxe findByTnbYear(int tnbYear);
    List<Taxe> findByTerrain(String nom);

    List<Taxe> findByTnbYearAndTerrain(int year, String nom);
}
