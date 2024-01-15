package ma.tnbmaroc.taxe.web;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import ma.tnbmaroc.taxe.domain.Taxe;
import ma.tnbmaroc.taxe.service.TaxeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/taxe")
@AllArgsConstructor
@CrossOrigin
public class TaxeController {
    private final TaxeService taxeService;
    @PostMapping("/save")
    public ResponseEntity<Taxe> save(@RequestBody Taxe taxe){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/save").toUriString());
        return  ResponseEntity.created(uri).body(taxeService.save(taxe));
    }

    @GetMapping("/")
    public ResponseEntity<Page<Taxe>> getAll(Pageable pageable){
        return ResponseEntity.ok().body(taxeService.getAll(pageable));
    }

    @PutMapping("/update")
    public  ResponseEntity<Taxe> update(@RequestBody Taxe taxe){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        return ResponseEntity.created(uri).body(taxeService.update(taxe));
    }
    @GetMapping("/get")
    public ResponseEntity<Taxe> getById(@PathParam(value = "id") Long id){
        return ResponseEntity.ok().body(taxeService.getById(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@PathParam(value = "id") Long id){
        this.taxeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byYear/{tnbYear}")
    public ResponseEntity<Taxe> getByTnbYear(@PathVariable int tnbYear) {
        return ResponseEntity.ok().body(taxeService.findByTnbYear(tnbYear));
    }

    @GetMapping("/byTerrain/{nom}")
    public ResponseEntity<List<Taxe>> getByTerrain(@PathVariable String nom) {
        return ResponseEntity.ok().body(taxeService.findByTerrain(nom));
    }

    @GetMapping("/byYearAndTerrain")
    public ResponseEntity<List<Taxe>> getByYearAndTerrain(
            @RequestParam int year,
            @RequestParam String nom
    ) {
        return ResponseEntity.ok().body(taxeService.findByTnbYearAndTerrain(year, nom));
    }
}
