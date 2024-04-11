package com.eliasjr.sicredi.votacaoapi.mapper

import com.eliasjr.sicredi.votacaoapi.entity.Sessao
import java.sql.Timestamp


object SessaoMapper {

    fun sessao(date: Timestamp?): Sessao {
        return Sessao.builder()
            .id(1L)
            .duration(60L)
            .dataCreate(date)
            .build();
    }
}