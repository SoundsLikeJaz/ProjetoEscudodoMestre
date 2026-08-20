package br.com.infnet.escudodomestre;

import br.com.infnet.escudodomestre.model.Mesa;
import br.com.infnet.escudodomestre.repositories.MesaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MesaRepositoryTest {

    @Autowired
    private MesaRepository mesaRepository;

    @Test
    void deveSalvarMesa() {
        Mesa mesa = new Mesa();

        mesa.setNome("Mesa do Terror");
        mesa.setDescricao("Uma aventura sombria");
        mesa.setMestre("Jackson");

        Mesa salva = mesaRepository.save(mesa);

        assertNotNull(salva.getId());
        assertEquals("Mesa do Terror", salva.getNome());
        assertEquals("Jackson", salva.getMestre());
    }

    @Test
    void deveBuscarMesasPorMestre() {
        Mesa mesa1 = new Mesa();
        mesa1.setNome("Mesa 1");
        mesa1.setDescricao("Descrição 1");
        mesa1.setMestre("Jackson");

        Mesa mesa2 = new Mesa();
        mesa2.setNome("Mesa 2");
        mesa2.setDescricao("Descrição 2");
        mesa2.setMestre("Jackson");

        Mesa mesa3 = new Mesa();
        mesa3.setNome("Mesa 3");
        mesa3.setDescricao("Descrição 3");
        mesa3.setMestre("Mariana");

        mesaRepository.save(mesa1);
        mesaRepository.save(mesa2);
        mesaRepository.save(mesa3);

        List<Mesa> mesas = mesaRepository.findByMestre("Jackson");

        assertEquals(2, mesas.size());

        assertTrue(mesas.stream()
                .allMatch(mesa -> mesa.getMestre().equals("Jackson")));
    }

    @Test
    void deveBuscarMesaPorId() {
        Mesa mesa = new Mesa();

        mesa.setNome("Mesa do Terror");
        mesa.setMestre("Jackson");

        Mesa salva = mesaRepository.save(mesa);

        Optional<Mesa> resultado =
                mesaRepository.findById(salva.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Mesa do Terror", resultado.get().getNome());
    }

    @Test
    void naoDeveEncontrarMesaInexistente() {
        Optional<Mesa> resultado =
                mesaRepository.findById(999L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveDeletarMesa() {
        Mesa mesa = new Mesa();

        mesa.setNome("Mesa do Terror");
        mesa.setMestre("Jackson");

        Mesa salva = mesaRepository.save(mesa);

        mesaRepository.deleteById(salva.getId());

        assertTrue(
                mesaRepository.findById(salva.getId()).isEmpty()
        );
    }

    @Test
    void deveRetornarListaVaziaQuandoMestreNaoPossuirMesas() {
        List<Mesa> mesas =
                mesaRepository.findByMestre("MestreInexistente");

        assertTrue(mesas.isEmpty());
    }
}
