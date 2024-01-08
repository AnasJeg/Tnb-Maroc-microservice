package ma.tnbmaroc.taux.repository;

import ma.tnbmaroc.taux.domain.Taux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TauxRepository extends JpaRepository<Taux, Long> {
    Taux findByCategorieLabel(String label);
}
