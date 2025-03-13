package inventory.exceptions;

public class AddPartControllerException extends InventoryControllerException {
    public AddPartControllerException(String message) {
        super(message);
    }

    public AddPartControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
