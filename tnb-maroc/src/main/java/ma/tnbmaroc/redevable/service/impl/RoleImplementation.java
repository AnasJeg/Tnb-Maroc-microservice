package ma.tnbmaroc.redevable.service.impl;

import ma.tnbmaroc.redevable.domain.Role;
import ma.tnbmaroc.redevable.repository.RoleRepository;
import ma.tnbmaroc.redevable.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Page<Role> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
