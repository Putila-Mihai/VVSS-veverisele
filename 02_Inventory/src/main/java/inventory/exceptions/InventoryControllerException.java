package inventory.exceptions;

public class InventoryControllerException extends Exception {
    // Default constructor
    public InventoryControllerException() {
        super();
    }

    // Constructor with custom message
    public InventoryControllerException(String message) {
        super(message);
    }

    // Constructor with custom message and cause
    public InventoryControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause
    public InventoryControllerException(Throwable cause) {
        super(cause);
    }
}
