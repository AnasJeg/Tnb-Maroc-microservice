package ma.tnbmaroc.redevable.service;


import ma.tnbmaroc.redevable.domain.Redevable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RedevableService {
    Redevable save(Redevable redevable);
    Page<Redevable> getAll(Pageable pageable);
    Redevable update(Redevable redevable);
    boolean delete(Long id);
    Redevable getById(Long id);

    Redevable findByCin(String cin);
}
