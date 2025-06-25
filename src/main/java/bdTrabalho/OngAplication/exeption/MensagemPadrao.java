package locadoraFilmes.application.exeption;

public class MensagemPadrao extends RuntimeException {
    public MensagemPadrao(String message){
        super(message);
    }
}
