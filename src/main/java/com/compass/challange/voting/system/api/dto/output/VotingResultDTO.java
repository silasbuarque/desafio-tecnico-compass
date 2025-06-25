package com.compass.challange.voting.system.api.dto.output;

public class VotingResultDTO {

    private Long pautaId;
    private String pautaTitulo;
    private Long totalYes;
    private Long totalNo;

    public VotingResultDTO(Long pautaId, String pautaTitulo, Long totalYes, Long totalNo) {
        this.pautaId = pautaId;
        this.pautaTitulo = pautaTitulo;
        this.totalYes = totalYes != null ? totalYes : 0L;
        this.totalNo = totalNo != null ? totalNo : 0L;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public String getPautaTitulo() {
        return pautaTitulo;
    }

    public Long getTotalYes() {
        return totalYes;
    }

    public Long getTotalNo() {
        return totalNo;
    }
}
