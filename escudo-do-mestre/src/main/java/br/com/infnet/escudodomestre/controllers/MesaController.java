package br.com.infnet.escudodomestre.controllers;

import br.com.infnet.escudodomestre.client.FichaMicroserviceClient;
import br.com.infnet.escudodomestre.dto.FichaDTO;
import br.com.infnet.escudodomestre.model.Mesa;
import br.com.infnet.escudodomestre.services.MesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
@CrossOrigin(origins = "http://localhost:5173")
public class MesaController {

    private final MesaService mesaService;
    private final FichaMicroserviceClient fichaMicroserviceClient;

    public MesaController(MesaService mesaService, FichaMicroserviceClient fichaMicroserviceClient) {
        this.mesaService = mesaService;
        this.fichaMicroserviceClient = fichaMicroserviceClient;
    }

    @PostMapping("/")
    public ResponseEntity<Mesa> criarMesa(@RequestBody Mesa mesa) {
        return ResponseEntity.ok(this.mesaService.criar(mesa));
    }

    @GetMapping("/mestre/{mestre}")
    public ResponseEntity<List<Mesa>> listarPorMestre(@PathVariable String mestre) {
        return ResponseEntity.ok(this.mesaService.listarPorMestre(mestre));
    }

    @GetMapping("/{mesaId}/fichas")
    public ResponseEntity<List<FichaDTO>> listarFichas(@PathVariable Long mesaId) {
        return ResponseEntity.ok(this.fichaMicroserviceClient.listarPorMesa(mesaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscarMesaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.mesaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> atualizarMesa(@PathVariable Long id, @RequestBody Mesa mesa) {
        return ResponseEntity.ok(this.mesaService.atualizar(id, mesa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable Long id) {
        this.mesaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
