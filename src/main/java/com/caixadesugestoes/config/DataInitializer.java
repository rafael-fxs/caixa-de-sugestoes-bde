package com.caixadesugestoes.config;

import com.caixadesugestoes.model.Sugestao;
import com.caixadesugestoes.model.Usuario;
import com.caixadesugestoes.repository.SugestaoRepository;
import com.caixadesugestoes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, SugestaoRepository sugestaoRepository) {
        return args -> {
            if (usuarioRepository.count() == 0 && sugestaoRepository.count() == 0) {
                // Criar usuários
                Usuario usuario1 = new Usuario(1L, "Alice");
                Usuario usuario2 = new Usuario(2L, "Bob");
                Usuario usuario3 = new Usuario(3L, "Charlie");
                Usuario usuario4 = new Usuario(4L, "Diana");

                usuarioRepository.saveAll(List.of(usuario1, usuario2, usuario3, usuario4));

                // Criar sugestões
                Sugestao sugestao1 = new Sugestao("Melhorar atendimento", "Serviço", usuario1);
                Sugestao sugestao2 = new Sugestao("Adicionar mais produtos", "Produto", usuario2);
                Sugestao sugestao3 = new Sugestao("Reduzir preços", "Preço", usuario3);
                Sugestao sugestao4 = new Sugestao("Expandir suporte", "Serviço", usuario4);

                // Adicionar curtidas
                sugestao1.getCurtidas().add(usuario2);
                sugestao1.getCurtidas().add(usuario3);

                sugestao2.getCurtidas().add(usuario1);
                sugestao2.getCurtidas().add(usuario3);
                sugestao2.getCurtidas().add(usuario4);

                sugestao3.getCurtidas().add(usuario1);
                sugestao3.getCurtidas().add(usuario2);

                sugestao4.getCurtidas().add(usuario1);
                sugestao4.getCurtidas().add(usuario2);
                sugestao4.getCurtidas().add(usuario3);

                sugestaoRepository.saveAll(List.of(sugestao1, sugestao2, sugestao3, sugestao4));
            }
        };
    }
}
