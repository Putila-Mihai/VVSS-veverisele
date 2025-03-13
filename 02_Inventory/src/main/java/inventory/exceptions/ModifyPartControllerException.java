package inventory.exceptions;

public class ModifyPartControllerException extends InventoryControllerException {
    public ModifyPartControllerException(String message) {
        super(message);
    }

    public ModifyPartControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
