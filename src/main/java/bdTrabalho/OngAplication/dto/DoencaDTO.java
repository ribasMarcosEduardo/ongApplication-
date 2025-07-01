package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.Doencas;

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

    public static DoencaDTO fromEntity(Doencas doenca) {
        return new DoencaDTO(doenca.getId(), doenca.getCid(), doenca.getNome());
    }
}
