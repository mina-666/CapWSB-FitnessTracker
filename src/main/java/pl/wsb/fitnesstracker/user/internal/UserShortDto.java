package pl.wsb.fitnesstracker.user.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Skrócona reprezentacja użytkownika wykorzystywana w DTO treningu.
 * Zawiera podstawowe dane identyfikacyjne użytkownika.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {

    /**
     * Identyfikator użytkownika.
     */
    private Long id;

    /**
     * Imię użytkownika.
     */
    private String firstName;

    /**
     * Nazwisko użytkownika.
     */
    private String lastName;

    /**
     * Adres e-mail użytkownika.
     */
    private String email;
}
