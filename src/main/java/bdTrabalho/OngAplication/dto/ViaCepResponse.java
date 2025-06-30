package bdTrabalho.OngAplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponse(String cep, String logradouro, String bairro, String localidade, String uf, Boolean erro) {
}