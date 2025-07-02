package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.dto.AdocaoDTO;
import bdTrabalho.OngAplication.model.Adocoes;
import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.ENUM.SituacaoAdocao;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.repository.AdocaoRepository;
import bdTrabalho.OngAplication.repository.AnimalRepository;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final AnimalRepository animalRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void solicitarAdocao(AdocaoDTO dto) {
        Animais animal = animalRepository.findById(dto.animalId())
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));

        Usuarios usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        if (animal.getSituacao() != 'D') {
            throw new IllegalStateException("Este animal não está mais disponível para adoção.");
        }

        Adocoes novaSolicitacao = new Adocoes();
        novaSolicitacao.setDataAdocao(dto.dataAdocao());
        novaSolicitacao.setObservacoes(dto.observacoes());
        novaSolicitacao.setAnimal(animal);
        novaSolicitacao.setUsuario(usuario);
        novaSolicitacao.setSituacao(SituacaoAdocao.EM_ANALISE);

        animal.setSituacao('T');

        animalRepository.save(animal);
        adocaoRepository.save(novaSolicitacao);
    }

    public List<Adocoes> findAll() {
        return adocaoRepository.findAll();
    }

    @Transactional
    public void aprovarAdocao(int adocaoId) {
        Adocoes adocao = adocaoRepository.findById(adocaoId)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação de adoção não encontrada."));

        Animais animal = adocao.getAnimal();
        if(animal == null) throw new IllegalStateException("Esta adoção não tem um animal associado.");

        adocao.setSituacao(SituacaoAdocao.APROVADA);
        animal.setSituacao('A');

        adocaoRepository.save(adocao);
        animalRepository.save(animal);
    }

    @Transactional
    public void reprovarAdocao(int adocaoId, String motivo) {
        Adocoes adocao = adocaoRepository.findById(adocaoId)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação de adoção não encontrada."));

        // Pega o animal associado
        Animais animal = adocao.getAnimal();
        if(animal == null) throw new IllegalStateException("Esta adoção não tem um animal associado.");

        adocao.setSituacao(SituacaoAdocao.REPROVADA);
        adocao.setMotivoReprovacao(motivo);
        animal.setSituacao('D');

        adocaoRepository.save(adocao);
        animalRepository.save(animal);
    }


}