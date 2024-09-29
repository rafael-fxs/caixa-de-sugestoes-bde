package com.caixadesugestoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoRequest {

    private String descricao;
    private String categoria;
    private Long usuarioId;
}
