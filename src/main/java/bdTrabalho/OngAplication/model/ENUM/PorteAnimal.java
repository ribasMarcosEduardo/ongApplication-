package bdTrabalho.OngAplication.model.ENUM;

//TODO ajustar para maiusculo, e com isso, a lógica em outras partes :c
public enum PorteAnimal {
    grande("Grande"),
    medio("Médio"),
    pequeno("Pequeno");

    private final String displayName;

    PorteAnimal(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}