package br.com.bruno.ultimos_campeoes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "titulos")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Titulos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeTime;
    private String campeonato;
    private LocalDate dataUltimaConquista;
    private Integer quantidadeDeTitulos;
    private String imgUrl;

}
