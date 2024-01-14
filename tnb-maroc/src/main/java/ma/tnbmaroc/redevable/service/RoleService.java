package ma.tnbmaroc.redevable.service;

import ma.tnbmaroc.redevable.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    Role save(Role role);
    Page<Role> getAll(Pageable pageable);
    Role update(Role role);
    boolean delete(Long id);

}
