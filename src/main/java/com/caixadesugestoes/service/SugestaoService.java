package com.caixadesugestoes.service;

import com.caixadesugestoes.dto.SugestaoRequest;
import com.caixadesugestoes.model.Sugestao;
import com.caixadesugestoes.model.Usuario;
import com.caixadesugestoes.repository.SugestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SugestaoService {

    @Autowired
    UsuarioService usuarioService;

    private final SugestaoRepository sugestaoRepository;

    public SugestaoService(SugestaoRepository sugestaoRepository) {
        this.sugestaoRepository = sugestaoRepository;
    }

    public List<Sugestao> listarTodas() {
        return sugestaoRepository.findAll();
    }

    public Sugestao enviarSugestao(SugestaoRequest sugestaoRequest) {
        Usuario usuario = usuarioService.buscarPorId(sugestaoRequest.getUsuarioId());

        Sugestao sugestao = new Sugestao();
        sugestao.setDescricao(sugestaoRequest.getDescricao());
        sugestao.setCategoria(sugestaoRequest.getCategoria());
        sugestao.setUsuario(usuario);

        Sugestao savedSugestao = sugestaoRepository.save(sugestao);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSugestao).getBody();
    }

    public void curtirOuDescurtirSugestao(Long sugestaoId, Usuario usuario) {
        Optional<Sugestao> sugestao = sugestaoRepository.findById(sugestaoId);
        sugestao.ifPresent(s -> {
            s.curtirOuDescurtir(usuario);
            sugestaoRepository.save(s);
        });
    }

    public List<Sugestao> listarTop3DaSemana() {
        return sugestaoRepository.findTop3ByOrderByCurtidasDesc();
    }

    public void deletarSugestao(Long sugestaoId) {
        sugestaoRepository.deleteById(sugestaoId);
    }
}
