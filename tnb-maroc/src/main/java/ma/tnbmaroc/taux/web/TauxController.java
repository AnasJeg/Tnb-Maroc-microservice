package ma.tnbmaroc.taux.web;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import ma.tnbmaroc.taux.domain.Taux;
import ma.tnbmaroc.taux.service.TauxService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/taux")
@AllArgsConstructor
@CrossOrigin
public class TauxController {
    private final TauxService tauxService;
    @PostMapping("/save")
    public ResponseEntity<Taux> save(@RequestBody Taux taux){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/save").toUriString());
        return  ResponseEntity.created(uri).body(tauxService.save(taux));
    }

    @GetMapping("/")
    public ResponseEntity<Page<Taux>> getAll(Pageable pageable){
        return ResponseEntity.ok().body(tauxService.getAll(pageable));
    }

    @PutMapping("/update")
    public  ResponseEntity<Taux> update(@RequestBody Taux taux){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        return ResponseEntity.created(uri).body(tauxService.update(taux));
    }
    @GetMapping("/get")
    public ResponseEntity<Taux> getById(@PathParam(value = "id") Long id){
        return ResponseEntity.ok().body(tauxService.getById(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@PathParam(value = "id") Long id){
        this.tauxService.delete(id);
        return ResponseEntity.ok().build();
    }
}
