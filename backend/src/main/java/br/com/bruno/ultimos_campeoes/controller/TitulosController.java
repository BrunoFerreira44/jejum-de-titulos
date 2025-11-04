package br.com.bruno.ultimos_campeoes.controller;

import br.com.bruno.ultimos_campeoes.domain.dto.TitulosRespondeDTO;
import br.com.bruno.ultimos_campeoes.service.TitulosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TitulosController {

    private final TitulosService service;

    @GetMapping("/geral")
    public ResponseEntity<List<TitulosRespondeDTO>> getQtdDiasSemTitulo () {
        return ResponseEntity.ok(service.getQtdDiasSemTitulo());
    }

    @GetMapping("/geral-sem-estadual")
    public ResponseEntity<List<TitulosRespondeDTO>> getQtdDiasSemTituloExluindoEstadual () {
        return ResponseEntity.ok(service.getQtdDiasSemTituloExluindoEstadual());
    }

    @GetMapping("/libertadores")
    public ResponseEntity<List<TitulosRespondeDTO>> getQtdDiasSemTituloPorLibertadores () {
        return ResponseEntity.ok(service.getQtdDiasSemTituloPorCampeonato("Libertadores"));
    }

    @GetMapping("/cdb")
    public ResponseEntity<List<TitulosRespondeDTO>> getQtdDiasSemTituloPorCdb () {
        return ResponseEntity.ok(service.getQtdDiasSemTituloPorCampeonato("Copa do Brasil"));
    }

    @GetMapping("/brasileirao")
    public ResponseEntity<List<TitulosRespondeDTO>> getQtdDiasSemTituloPorBrasileirao () {
        return ResponseEntity.ok(service.getQtdDiasSemTituloPorCampeonato("Brasileirão"));
    }

    @GetMapping("/estadual")
    public ResponseEntity<List<TitulosRespondeDTO>> getQtdDiasSemTituloPorEstadual () {
        return ResponseEntity.ok(service.getQtdDiasSemTituloPorCampeonato("Estadual"));
    }



}
