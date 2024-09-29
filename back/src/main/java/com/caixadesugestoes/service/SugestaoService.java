package com.caixadesugestoes.service;

import com.caixadesugestoes.dto.SugestaoRequest;
import com.caixadesugestoes.model.Sugestao;
import com.caixadesugestoes.model.Usuario;
import com.caixadesugestoes.repository.SugestaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SugestaoService {

    @Autowired
    UsuarioService usuarioService;

    private final SugestaoRepository sugestaoRepository;

    public SugestaoService(SugestaoRepository sugestaoRepository) {
        this.sugestaoRepository = sugestaoRepository;
    }

    public List<Sugestao> listarTodas() {
        return sugestaoRepository.listarTodas();
    }

    @Transactional
    public void enviarSugestao(SugestaoRequest sugestaoRequest) {
        Usuario usuario = usuarioService.buscarPorId(sugestaoRequest.getUsuarioId());
        sugestaoRepository.enviarSugestao(sugestaoRequest.getDescricao(), sugestaoRequest.getCategoria(), usuario.getId());
    }

    @Transactional
    public void curtirOuDescurtirSugestao(Long sugestaoId, Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        int curtidaExiste = sugestaoRepository.verificarCurtida(sugestaoId, usuario.getId());
        if (curtidaExiste > 0) {
            sugestaoRepository.removerCurtida(sugestaoId, usuario.getId());
        } else {
            sugestaoRepository.adicionarCurtida(sugestaoId, usuario.getId());
        }
    }

    public List<Sugestao> listarTop3DaSemana() {
        return sugestaoRepository.listarTop3DaSemana();
    }

    @Transactional
    public void deletarSugestao(Long sugestaoId) {
        sugestaoRepository.deletarCurtidasPorSugestao(sugestaoId);
        sugestaoRepository.deletarSugestao(sugestaoId);
    }
}
