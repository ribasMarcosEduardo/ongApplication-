package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.EMUN.PorteAnimal;
import bdTrabalho.OngAplication.model.EMUN.TipoAnimal;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record AnimalDTO(
        int id,
        String nome,
        String descricao,
        String raca,
        Integer idade,
        TipoAnimal tipo,
        String foto,
        Double peso,
        Character sexo,
        Character situacao,
        PorteAnimal porte,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataChegada,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataNascimento,
        String cor,
        String historia
) {
    public Animais mapearAnimal() {
        Animais animal = new Animais();
        animal.setId(this.id);
        animal.setNome(this.nome);
        animal.setDescricao(this.descricao);
        animal.setRaca(this.raca);
        animal.setIdade(this.idade);
        animal.setTipo(this.tipo);
        animal.setFoto(this.foto);
        animal.setPeso(this.peso);
        animal.setSexo(this.sexo);
        animal.setPorte(this.porte);
        animal.setDataChegada(this.dataChegada);
        animal.setDataNascimento(this.dataNascimento);
        animal.setCor(this.cor);
        animal.setHistoria(this.historia);
        return animal;
    }

    public static AnimalDTO fromEntity(Animais animal){
        System.out.println("SEXO DO ANIMAL AO EDITAR: " + animal.getSexo());
        return new AnimalDTO(
                animal.getId(),
                animal.getNome(),
                animal.getDescricao(),
                animal.getRaca(),
                animal.getIdade(),
                animal.getTipo(),
                animal.getFoto(),
                animal.getPeso(),
                animal.getSexo(),
                animal.getSituacao(),
                animal.getPorte(),
                animal.getDataChegada(),
                animal.getDataNascimento(),
                animal.getCor(),
                animal.getHistoria()
        );
    }
}