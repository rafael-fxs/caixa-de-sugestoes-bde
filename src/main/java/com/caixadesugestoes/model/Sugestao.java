package com.caixadesugestoes.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sugestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String categoria;
    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToMany
    @JoinTable(
            name = "curtidas",
            joinColumns = @JoinColumn(name = "sugestao_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> curtidas = new HashSet<>();
    public Sugestao(String descricao, String categoria, Usuario usuario) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataEnvio = LocalDateTime.now();
        this.usuario = usuario;
    }

    public int getTotalCurtidas() {
        return curtidas.size();
    }

    @PrePersist
    protected void onCreate() {
        this.dataEnvio = LocalDateTime.now();
    }
}
