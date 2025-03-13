package inventory.exceptions;

public class AddProductControllerException extends InventoryControllerException {
    public AddProductControllerException(String message) {
        super(message);
    }

    public AddProductControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
