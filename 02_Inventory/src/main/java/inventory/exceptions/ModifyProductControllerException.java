package inventory.exceptions;

public class ModifyProductControllerException extends InventoryControllerException {
    public ModifyProductControllerException(String message) {
        super(message);
    }

    public ModifyProductControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
