package ma.tnbmaroc.redevable.repository;

import ma.tnbmaroc.redevable.domain.Redevable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedevableRepository extends JpaRepository<Redevable, Long> {
    Redevable findByCin(String cin);
}
