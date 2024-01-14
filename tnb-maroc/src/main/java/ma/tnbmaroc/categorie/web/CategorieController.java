package ma.tnbmaroc.categorie.web;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import ma.tnbmaroc.categorie.domain.Categorie;
import ma.tnbmaroc.categorie.service.CategorieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
// import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categorie")
@RequiredArgsConstructor
@CrossOrigin
public class CategorieController {
    private final CategorieService categorieService;

    @PostMapping("/save")
    public ResponseEntity<Categorie> save(@RequestBody Categorie categorie){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/save").toUriString());
        return  ResponseEntity.created(uri).body(categorieService.save(categorie));
    }

    @GetMapping("/")
    public ResponseEntity<Page<Categorie>> getAll(Pageable pageable){
            return ResponseEntity.ok().body(categorieService.getAll(pageable));
    }

    @PutMapping("/update")
    public  ResponseEntity<Categorie> update(@RequestBody Categorie categorie){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        return ResponseEntity.created(uri).body(categorieService.update(categorie));
    }

    @GetMapping("/get")
    public ResponseEntity<Categorie> getById(@PathParam(value = "id") Long id){
        return ResponseEntity.ok().body(categorieService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.categorieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
