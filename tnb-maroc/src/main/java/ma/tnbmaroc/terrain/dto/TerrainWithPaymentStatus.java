package ma.tnbmaroc.terrain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ma.tnbmaroc.terrain.domain.Terrain;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor @NoArgsConstructor
public class TerrainWithPaymentStatus {
    private Terrain terrain;
    private boolean paid;
}
