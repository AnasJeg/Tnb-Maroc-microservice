package ma.tnbmaroc.redevable.web;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import ma.tnbmaroc.redevable.domain.Redevable;
import ma.tnbmaroc.redevable.service.RedevableService;
import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin
public class RedevableController {
    private final RedevableService redevableService;

    @PostMapping("/save")
    public ResponseEntity<Redevable> save(@RequestBody Redevable redevable){
URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/save").toUriString());
        return  ResponseEntity.created(uri).body(redevableService.save(redevable));
    }

    @GetMapping("/")
    public ResponseEntity<Page<Redevable>> getAll(Pageable pageable){
        return ResponseEntity.ok().body(redevableService.getAll(pageable));
    }

    @PutMapping("/update")
    public  ResponseEntity<Redevable> update(@RequestBody Redevable redevable){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        return ResponseEntity.created(uri).body(redevableService.update(redevable));
    }

    @GetMapping("/get")
    public ResponseEntity<Redevable> getById(@PathParam(value = "id") Long id){
        return ResponseEntity.ok().body(redevableService.getById(id));
    }

    @GetMapping("/by")
    public ResponseEntity<Redevable> getByCin(@PathParam(value = "cin") String cin){
        return ResponseEntity.ok().body(redevableService.findByCin(cin));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@PathParam(value = "id") Long id){
        this.redevableService.delete(id);
        return ResponseEntity.ok().build();
    }
}
