package pl.wsb.fitnesstracker.user.api;

import pl.wsb.fitnesstracker.user.internal.UserEmailDto;
import pl.wsb.fitnesstracker.user.internal.UserShortDto;

import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    List<UserShortDto> getAllUsersShort();

    Optional<UserDto> getUserById(Long id);

    UserDto createUser(UserDto userDto);

    void deleteUser(Long id);

    List<UserEmailDto> findByEmailContainingIgnoreCase(String emailFragment);

    List<User> findAllUsers();

    List<UserShortDto> getUsersOlderThan(int age);

    UserDto updateUser(Long id, UserDto userDto);

}
