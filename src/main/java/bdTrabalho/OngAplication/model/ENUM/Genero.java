package bdTrabalho.OngAplication.model.ENUM;

public enum Genero {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_BINARIO("Não-Binário"),
    NAO_INFORMAR("Não Informar");

    private final String displayName;

    Genero(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}