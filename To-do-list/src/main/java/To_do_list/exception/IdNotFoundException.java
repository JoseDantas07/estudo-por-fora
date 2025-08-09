package To_do_list.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
        super("Id nao encontrado");
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}