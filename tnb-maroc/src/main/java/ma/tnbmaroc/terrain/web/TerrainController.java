package ma.tnbmaroc.terrain.web;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import ma.tnbmaroc.terrain.domain.Terrain;
import ma.tnbmaroc.terrain.service.TerrainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/terrain")
@RequiredArgsConstructor
@CrossOrigin
public class TerrainController {
    private final TerrainService terrainService;
    @PostMapping("/save")
    public ResponseEntity<Terrain> save(@RequestBody Terrain terrain){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/save").toUriString());
        return  ResponseEntity.created(uri).body(terrainService.save(terrain));
    }

    @GetMapping("/")
    public ResponseEntity<Page<Terrain>> getAll(Pageable pageable){
        return ResponseEntity.ok().body(terrainService.getAll(pageable));
    }

    @PutMapping("/update")
    public  ResponseEntity<Terrain> update(@RequestBody Terrain terrain){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        return ResponseEntity.created(uri).body(terrainService.update(terrain));
    }
    @GetMapping("/get")
    public ResponseEntity<Terrain> getById(@PathParam(value = "id") Long id){
        return ResponseEntity.ok().body(terrainService.getById(id));
    }

    @GetMapping("/redevable")
    public ResponseEntity<List<Terrain>> getById(@PathParam(value = "cin") String cin){
        return ResponseEntity.ok().body(terrainService.getAllByRedevable(cin));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Terrain>> getTerrainsByRedevableCin(@RequestParam(value = "cin") String cin,
            @RequestParam(value = "isPaid", defaultValue = "true") boolean isPaid) {
        List<Terrain> terrains = terrainService.getTerrainsByRedevableCin(cin, isPaid);
        return ResponseEntity.ok().body(terrains);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@PathParam(value = "id") Long id){
        this.terrainService.delete(id);
        return ResponseEntity.ok().build();
    }
}
