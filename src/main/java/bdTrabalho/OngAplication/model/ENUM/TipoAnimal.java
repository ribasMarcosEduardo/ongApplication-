package bdTrabalho.OngAplication.model.EMUN;

public enum TipoAnimal {
    CACHORRO("Cachorro"),
    GATO("Gato");

    private final String displayName;

    TipoAnimal(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}