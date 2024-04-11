package com.eliasjr.sicredi.votacaoapi.mapper

import com.eliasjr.sicredi.votacaoapi.controller.request.VotoRequest
import com.eliasjr.sicredi.votacaoapi.entity.Associado
import com.eliasjr.sicredi.votacaoapi.entity.Sessao
import com.eliasjr.sicredi.votacaoapi.entity.Voto
import java.sql.Timestamp

object VotoMapper {
    fun votoRequest(): VotoRequest {
        return VotoRequest.builder()
            .cpf("321564878")
            .voto(true)
            .sessaoId(1L)
            .build()
    }

    fun voto(associado: Associado, sessao: Sessao): Voto {
        val voto = Voto()
        voto.associado = associado
        voto.voto = true
        voto.sessao = sessao
        voto.dataCreate = Timestamp(0)
        voto.id = 1L
        return voto
    }
}