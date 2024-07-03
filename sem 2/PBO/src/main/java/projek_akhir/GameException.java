package projek_akhir;

public class GameException extends Exception {
    private String message;

    public GameException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

class ValueOutOfRangeException extends GameException {
    public ValueOutOfRangeException(String message) {
        super(message);
    }
}

class InvalidElementChangeException extends GameException {
    private Element attemptedElement;

    public InvalidElementChangeException(String message, Element attemptedElement) {
        super(message);
        this.attemptedElement = attemptedElement;
    }

    public Element getAttemptedElement() {
        return attemptedElement;
    }
}

class MonsterCapacityExceededException extends GameException {
    private int capacity;

    public MonsterCapacityExceededException(String message, int capacity) {
        super(message);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
