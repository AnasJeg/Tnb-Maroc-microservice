package ma.tnbmaroc.taux.service;

import ma.tnbmaroc.taux.domain.Taux;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TauxService {
    Taux save(Taux taux);
    Page<Taux> getAll(Pageable pageable);
    Taux update(Taux taux);
    boolean delete(Long id);
    Taux getById(Long id);

    Taux findByCategorieLabel(String cat);
}
