package ma.tnbmaroc.redevable.repository;

import ma.tnbmaroc.redevable.domain.Redevable;
import ma.tnbmaroc.security.AuthenticationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedevableRepository extends JpaRepository<Redevable, Long> {
   Redevable findByCin(String cin);
/*
    @Query(nativeQuery = true, value = "SELECT cin, password FROM redevable WHERE cin = ?1")
    Optional<AuthenticationRequest> getByCin(String cin);
*/
}
