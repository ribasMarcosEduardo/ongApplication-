package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.Vacinas;

public record VacinaDTO(
        int id,
        String nome,
        String codigo
) {

    public Vacinas mapearVacina(){
        Vacinas vacina = new Vacinas();
        vacina.setId(this.id);
        vacina.setNome(this.nome);
        vacina.setCodigo(this.codigo);
        return vacina;
    }

    public static VacinaDTO fromEntity(Vacinas vacina) {
        return new VacinaDTO(vacina.getId(), vacina.getCodigo(), vacina.getNome());
    }

}
