package pl.wsb.fitnesstracker.user.api;

/**
 * Exception indicating that the {@link User} was not found.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }
}
