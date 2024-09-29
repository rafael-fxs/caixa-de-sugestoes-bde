package com.caixadesugestoes.controller;

import com.caixadesugestoes.dto.SugestaoRequest;
import com.caixadesugestoes.model.Sugestao;
import com.caixadesugestoes.model.Usuario;
import com.caixadesugestoes.service.SugestaoService;
import com.caixadesugestoes.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sugestoes")
public class SugestaoController {

    private final SugestaoService sugestaoService;

    public SugestaoController(SugestaoService sugestaoService) {
        this.sugestaoService = sugestaoService;
    }

    @GetMapping
    public List<Sugestao> listarSugestoes() {
        return sugestaoService.listarTodas();
    }

    @PostMapping
    public void enviarSugestao(@RequestBody SugestaoRequest sugestaoRequest) {
        sugestaoService.enviarSugestao(sugestaoRequest);
    }

    @PutMapping("/{id}/curtir")
    public ResponseEntity<Void> curtirSugestao(@PathVariable Long id, @RequestParam Long usuarioId) {
        sugestaoService.curtirOuDescurtirSugestao(id, usuarioId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/top3")
    public List<Sugestao> listarTop3Sugestoes() {
        return sugestaoService.listarTop3DaSemana();
    }

    @DeleteMapping("/{id}")
    public void enviarSugestao(@PathVariable Long id) {
        sugestaoService.deletarSugestao(id);
    }
}
