package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.Model.Doencas;
import bdTrabalho.OngAplication.Model.Vacinas;

public record DoencaDTO(
        int id,
        String cid,
        String nome
) {

    public Doencas mapearDoenca() {
        Doencas doenca = new Doencas();
        doenca.setId(this.id);
        doenca.setCid(this.cid);
        doenca.setNome(this.nome);
        return doenca;
    }
}
