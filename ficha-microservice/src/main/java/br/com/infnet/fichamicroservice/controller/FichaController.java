package br.com.infnet.fichamicroservice.controller;

import br.com.infnet.fichamicroservice.model.Ficha;
import br.com.infnet.fichamicroservice.service.FichaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fichas")
@CrossOrigin(origins = "http://localhost:5173")
public class FichaController {

    private final FichaService fichaService;

    public FichaController(FichaService fichaService) {
        this.fichaService = fichaService;
    }

    @PostMapping()
    public ResponseEntity<Ficha> criarFicha(@RequestBody Ficha ficha) {
        return ResponseEntity.ok(fichaService.cadastrar(ficha));
    }

    @GetMapping("/jogador/{jogador}")
    public ResponseEntity<List<Ficha>> listarFichasPorJogador(@PathVariable String jogador) {
        return ResponseEntity.ok(fichaService.listarPorJogador(jogador));
    }

    @GetMapping("/mesa/{mesaId}")
    public ResponseEntity<List<Ficha>> listarFichasPorMesa(@PathVariable Long mesaId) {
        return ResponseEntity.ok(fichaService.listarPorMesa(mesaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ficha> buscarFichaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fichaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ficha> atualizarFicha(@PathVariable Long id, @RequestBody Ficha ficha) {
        return ResponseEntity.ok(fichaService.atualizar(id, ficha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFicha(@PathVariable Long id) {
        this.fichaService.remover(id);

        return ResponseEntity.noContent().build();
    }
}
