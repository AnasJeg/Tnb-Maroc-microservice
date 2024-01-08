package ma.tnbmaroc.categorie.repository;

import ma.tnbmaroc.categorie.domain.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}
