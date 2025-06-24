package bdTrabalho.OngAplication.Model.EMUN;

public enum PorteAnimal {
    GRANDE("Grande"),
    MEDIO("Médio"),
    PEQUENO("Pequeno");

    private final String displayName; // Campo para armazenar o nome de exibição


    PorteAnimal(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}