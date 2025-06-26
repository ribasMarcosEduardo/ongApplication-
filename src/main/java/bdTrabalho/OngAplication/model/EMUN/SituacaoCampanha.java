package bdTrabalho.OngAplication.model.EMUN;

public enum SituacaoCampanha {

    REALIZADA("Realizada"),
    CANCELADA("Cancelada"),
    EM_OCORRENCIA("Em Ocorrência"),
    AGUARDANDO("Aguardando");

    private final String displayName;

    SituacaoCampanha(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}