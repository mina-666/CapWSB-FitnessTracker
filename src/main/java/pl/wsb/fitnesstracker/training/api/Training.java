package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

/**
 * Encja reprezentująca trening użytkownika.
 * Zawiera informacje o czasie trwania treningu, rodzaju aktywności,
 * przebytej odległości oraz średniej prędkości.
 */
@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    /**
     * Identyfikator treningu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Użytkownik, do którego należy trening.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Czas rozpoczęcia treningu.
     */
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    /**
     * Czas zakończenia treningu.
     */
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    /**
     * Rodzaj aktywności wykonywanej podczas treningu.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    /**
     * Pokonana odległość w kilometrach.
     */
    @Column(name = "distance")
    private double distance;

    /**
     * Średnia prędkość w km/h.
     */
    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Tworzy nową instancję treningu.
     *
     * @param user          użytkownik powiązany z treningiem
     * @param startTime     czas rozpoczęcia
     * @param endTime       czas zakończenia
     * @param activityType  typ aktywności
     * @param distance      przebyta odległość
     * @param averageSpeed  średnia prędkość
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
