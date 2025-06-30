package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.ViaCepResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController // <-- RestController para retornar dados, e não HTML
@RequestMapping("/api/cep") // Para diferenciar que é da api
@RequiredArgsConstructor
public class CepController {

    private final RestTemplate restTemplate;

    @GetMapping("/consultar/{cep}")
    public ResponseEntity<ViaCepResponse> consultarCep(@PathVariable String cep) {
        String viaCepUrl = "https://viacep.com.br/ws/" + cep + "/json/"; //TODO talvez não deixar exposto o link

        try {
            ViaCepResponse response = restTemplate.getForObject(viaCepUrl, ViaCepResponse.class);

            if (response == null || (response.erro() != null && response.erro())) {
                // Se o CEP não for encontrado, retorna um status 404 (Not Found)
                return ResponseEntity.notFound().build();
            }

            // Se o CEP for encontrado, retorna os dados com status 200 (OK)
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Se a API ViaCEP estiver fora do ar ou ocorrer outro erro
            return ResponseEntity.internalServerError().build();
        }
    }
}