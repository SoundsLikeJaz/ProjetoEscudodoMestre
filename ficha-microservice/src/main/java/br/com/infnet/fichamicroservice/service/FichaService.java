package br.com.infnet.fichamicroservice.service;

import br.com.infnet.fichamicroservice.model.Ficha;
import br.com.infnet.fichamicroservice.repository.FichaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FichaService {

    private final FichaRepository fichaRepository;

    public FichaService(FichaRepository fichaRepository) {
        this.fichaRepository = fichaRepository;
    }

    public Ficha cadastrar(Ficha ficha) {
        return this.fichaRepository.save(ficha);
    }

    public List<Ficha> listarPorJogador(String jogador) {
        return this.fichaRepository.findByJogador(jogador);
    }

    public List<Ficha> listarPorMesa(Long mesaId) {
        return this.fichaRepository.findByMesaId(mesaId);
    }

    public Ficha buscarPorId(Long id) {
        return this.fichaRepository.findById(id)
                .orElseThrow(this::notFound);
    }

    public Ficha atualizar(Long id, Ficha ficha) {
        this.buscarPorId(id);

        return this.fichaRepository.save(ficha);
    }

    public void remover(Long id) {
        this.buscarPorId(id);

        this.fichaRepository.deleteById(id);
    }

    private ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
}
