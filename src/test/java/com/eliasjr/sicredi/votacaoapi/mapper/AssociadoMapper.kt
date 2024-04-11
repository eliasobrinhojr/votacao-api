package com.eliasjr.sicredi.votacaoapi.mapper

import com.eliasjr.sicredi.votacaoapi.entity.Associado
import java.sql.Timestamp

object AssociadoMapper {

    fun associado(): Associado {
        val associado = Associado()
        associado.id = 1L
        associado.dataCreate = Timestamp(0)
        associado.cpf = "1332131231"
        associado.nome = "fulano"
        return associado
    }
}