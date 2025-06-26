package com.compass.challange.voting.system.exception.handler;

import lombok.Getter;

@Getter
public enum ProblemType {

    DADOS_INVALIDOS("/invalid-data","Invalid data"),
    ERRO_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro invalido"),
    REQUISICAO_INVALIDA("/requisicao-invalida", "Requisição inválida"),
    MENSAGEM_INCOMPREENSIVEL("/menssagem-incompreensivel", "Mensagem incompreensivel"),
    RECURSO_NAO_ENCONTRADO("/resourc-not-found", "Resource not found"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/regra-de-negocio-violada", "Regra de negocio foi violada");

    private String path;
    private String title;

    ProblemType(String path, String title) {
        this.path = "https://voting-system.com" + path;
        this.title = title;
    }
}
