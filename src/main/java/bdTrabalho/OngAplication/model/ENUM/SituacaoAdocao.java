package bdTrabalho.OngAplication.model.ENUM;

public enum SituacaoAdocao {
    EM_ANALISE("Em Análise"),
    APROVADA("Aprovada"),
    REPROVADA("Reprovada");

    private final String displayName;

    SituacaoAdocao(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}