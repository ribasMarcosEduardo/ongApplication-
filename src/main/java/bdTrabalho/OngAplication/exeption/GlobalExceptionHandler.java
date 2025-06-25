package locadoraFilmes.application.exeption;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MensagemPadrao.class)
    public String handleMensagemPadrao(MensagemPadrao e, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("MensagemPadrao", e.getMessage());
        return getRedirectUrl(request);
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "cadastro/cadastroFilme");
    }
}
