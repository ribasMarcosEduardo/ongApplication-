package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.Campanhas;
import bdTrabalho.OngAplication.model.ENUM.SituacaoCampanha;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CampanhaDTO(
        int id,
        String nome,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataTermino,
        String meta,
        SituacaoCampanha situacao,
        String localizacao,
        BigDecimal custo,
        BigDecimal lucro
) {

    public Campanhas mapearCampanha(){
        Campanhas campanha = new Campanhas();
        campanha.setId(this.id);
        campanha.setNome(this.nome);
        campanha.setDescricao(this.descricao);
        campanha.setDataInicio(this.dataInicio);
        campanha.setDataTermino(this.dataTermino);
        campanha.setMeta(this.meta);
        campanha.setSituacao(this.situacao);
        campanha.setLocalizacao(this.localizacao);
        campanha.setCusto(this.custo);
        campanha.setLucro(this.lucro);
        return campanha;
    }

    public static CampanhaDTO fromEntity(Campanhas campanha) {
        return new CampanhaDTO(
                campanha.getId(),
                campanha.getNome(),
                campanha.getDescricao(),
                campanha.getDataInicio(),
                campanha.getDataTermino(),
                campanha.getMeta(),
                campanha.getSituacao(),
                campanha.getLocalizacao(),
                campanha.getCusto(),
                campanha.getLucro()
        );
    }

}
