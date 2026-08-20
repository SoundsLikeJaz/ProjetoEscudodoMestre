package br.com.infnet.escudodomestre.services;

import br.com.infnet.escudodomestre.model.Mesa;
import br.com.infnet.escudodomestre.repositories.MesaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public Mesa criar(Mesa mesa) {
        return this.mesaRepository.save(mesa);
    }

    public List<Mesa> listarPorMestre(String mestre) {
        return this.mesaRepository.findByMestre(mestre);
    }

    public Mesa buscarPorId(Long id) {
        return this.mesaRepository.findById(id)
                .orElseThrow(this::notFound);
    }

    public Mesa atualizar(Long id, Mesa mesa) {
        this.mesaRepository.findById(id)
                .orElseThrow(this::notFound);

        return this.mesaRepository.save(mesa);
    }

    public void excluir(Long id) {
        this.mesaRepository.findById(id)
                .orElseThrow(this::notFound);

        this.mesaRepository.deleteById(id);
    }

    private ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
}
