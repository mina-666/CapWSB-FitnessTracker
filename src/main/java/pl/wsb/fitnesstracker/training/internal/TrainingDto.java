package pl.wsb.fitnesstracker.training.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.internal.UserShortDto;

import java.util.Date;

/**
 * Obiekt transferowy (DTO) reprezentujący dane treningu przesyłane przez API.
 * Zawiera informacje o użytkowniku, czasie trwania, rodzaju aktywności,
 * przebytej odległości oraz średniej prędkości.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingDto {

    /**
     * Identyfikator treningu.
     */
    private Long id;

    /**
     * Skrócone dane użytkownika powiązanego z treningiem.
     */
    private UserShortDto user;

    /**
     * Data i godzina rozpoczęcia treningu.
     */
    private Date startTime;

    /**
     * Data i godzina zakończenia treningu.
     */
    private Date endTime;

    /**
     * Typ aktywności fizycznej (np. RUNNING, CYCLING).
     */
    private ActivityType activityType;

    /**
     * Pokonana odległość w kilometrach.
     */
    private double distance;

    /**
     * Średnia prędkość w km/h.
     */
    private double averageSpeed;
}
