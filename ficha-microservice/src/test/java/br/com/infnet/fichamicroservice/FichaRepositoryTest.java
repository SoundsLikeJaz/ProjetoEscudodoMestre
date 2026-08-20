package br.com.infnet.fichamicroservice;

import br.com.infnet.fichamicroservice.model.Ficha;
import br.com.infnet.fichamicroservice.repository.FichaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FichaRepositoryTest {

    @Autowired
    private FichaRepository fichaRepository;

    @Test
    void deveSalvarFicha() {
        Ficha ficha = new Ficha();

        ficha.setJogador("Jackson");
        ficha.setNome("Kael");
        ficha.setNivel(1);
        ficha.setMesaId(1L);

        Ficha salva = fichaRepository.save(ficha);

        assertNotNull(salva.getId());
        assertEquals("Jackson", salva.getJogador());
        assertEquals("Kael", salva.getNome());
    }

    @Test
    void deveBuscarFichasPorJogador() {
        Ficha ficha1 = new Ficha();
        ficha1.setJogador("Jackson");
        ficha1.setNome("Kael");

        Ficha ficha2 = new Ficha();
        ficha2.setJogador("Jackson");
        ficha2.setNome("Aldren");

        Ficha ficha3 = new Ficha();
        ficha3.setJogador("Mariana");
        ficha3.setNome("Lysandra");

        fichaRepository.save(ficha1);
        fichaRepository.save(ficha2);
        fichaRepository.save(ficha3);

        List<Ficha> fichas = fichaRepository.findByJogador("Jackson");

        assertEquals(2, fichas.size());
        assertTrue(fichas.stream()
                .allMatch(ficha -> ficha.getJogador().equals("Jackson")));
    }

    @Test
    void deveBuscarFichaPorId() {
        Ficha ficha = new Ficha();

        ficha.setJogador("Jackson");
        ficha.setNome("Kael");

        Ficha salva = fichaRepository.save(ficha);

        Optional<Ficha> resultado =
                fichaRepository.findById(salva.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Kael", resultado.get().getNome());
    }

    @Test
    void naoDeveEncontrarFichaInexistente() {
        Optional<Ficha> resultado =
                fichaRepository.findById(999L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveDeletarFicha() {
        Ficha ficha = new Ficha();

        ficha.setJogador("Jackson");
        ficha.setNome("Kael");

        Ficha salva = fichaRepository.save(ficha);

        fichaRepository.deleteById(salva.getId());

        assertTrue(
                fichaRepository.findById(salva.getId()).isEmpty()
        );
    }

    @Test
    void deveBuscarFichasPorMesa() {
        Ficha ficha1 = new Ficha();
        ficha1.setJogador("Jackson");
        ficha1.setNome("Kael");
        ficha1.setMesaId(1L);

        Ficha ficha2 = new Ficha();
        ficha2.setJogador("Mariana");
        ficha2.setNome("Lysandra");
        ficha2.setMesaId(1L);

        Ficha ficha3 = new Ficha();
        ficha3.setJogador("Pedro");
        ficha3.setNome("Aldren");
        ficha3.setMesaId(2L);

        fichaRepository.save(ficha1);
        fichaRepository.save(ficha2);
        fichaRepository.save(ficha3);

        List<Ficha> fichas = fichaRepository.findByMesaId(1L);

        assertEquals(2, fichas.size());

        assertTrue(fichas.stream()
                .allMatch(ficha -> ficha.getMesaId().equals(1L)));
    }

    @Test
    void deveRetornarListaVaziaQuandoMesaNaoPossuirFichas() {
        List<Ficha> fichas = fichaRepository.findByMesaId(999L);

        assertTrue(fichas.isEmpty());
    }
}
