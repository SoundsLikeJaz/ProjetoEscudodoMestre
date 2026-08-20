package br.com.infnet.fichamicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jogador;
    private String nome;
    private String descricao;
    private String ancestralidade;
    private String aprendiz;
    private String especialista;
    private int nivel;
    private String profissao;
    private int poder;
    private int intelecto;
    private int agilidade;
    private int forca;
    private int saude;
    private int vontade;
    private int percepcao;
    private double deslocamento;
    private int tamanho;
    private int defesa;
    private int corrupcao;
    private int insanidade;

    private Long mesaId;
}
