package com.soulcode.goserviceapp.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Document(collection = "mensagens")
public class Chat {
    @Id
    private String id;

    @Getter
    private String idAgendamento;

    @Getter
    private String remetente;

    @Getter
    private String destinatario;

    @Getter
    private String mensagem;

    @Getter
    private String classeRemetente;

    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime dataMensagem = LocalDateTime.now();

    public Chat() {
    }

    public Chat(String idAgendamento, String remetente, String destinatario, String mensagem, String classeRemetente) {
        this.idAgendamento = idAgendamento;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.classeRemetente = classeRemetente;
    }

    public void setClasseRemetente(String classeRemetente) {
        this.classeRemetente = classeRemetente;
    }

    public void setAgendamentoId(String idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public void setRemetenteId(String remetenteId) {
        this.remetente = remetenteId;
    }

    public void setDestinatarioId(String destinatarioId) {
        this.destinatario = destinatarioId;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataMensagem() {
        return dataMensagem;
    }

    public void setDataMensagem(LocalDateTime dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

}
