package ma.tnbmaroc.taxe.service;


import ma.tnbmaroc.taxe.domain.Taxe;
import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaxeService {
    Taxe save(Taxe taxe);
    Page<Taxe> getAll(Pageable pageable);
    Taxe update(Taxe taxe);
    boolean delete(Long id);
    Taxe getById(Long id);

    Double TaxeTnb(String cin, int annee, Terrain terrain);
}
