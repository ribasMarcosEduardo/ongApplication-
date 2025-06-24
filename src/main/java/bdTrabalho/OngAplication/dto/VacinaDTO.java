package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.Model.Vacinas;

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
}
