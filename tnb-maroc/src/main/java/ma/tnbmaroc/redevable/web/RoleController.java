package ma.tnbmaroc.redevable.web;

import lombok.RequiredArgsConstructor;
import ma.tnbmaroc.redevable.domain.Role;
import ma.tnbmaroc.redevable.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<Role> save(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/save").toUriString());
        return ResponseEntity.created(uri).body(roleService.save(role));
    }

}