package root.customExceptions;

public class UnsupportedOperationExceptionCustom extends Exception{

    private String operation;

    public UnsupportedOperationExceptionCustom(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
