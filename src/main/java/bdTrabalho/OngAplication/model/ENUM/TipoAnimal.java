package bdTrabalho.OngAplication.model.ENUM;

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