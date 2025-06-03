package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserShortDto;

/**
 * Mapper odpowiedzialny za konwersję między encją Training a obiektem TrainingDto.
 */
public class TrainingMapper {

    /**
     * Konwertuje encję Training do obiektu DTO.
     *
     * @param training encja Training
     * @return TrainingDto zawierający dane z encji
     */
    public static TrainingDto toDto(Training training) {
        User user = training.getUser();
        UserShortDto userDto = new UserShortDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );

        return TrainingDto.builder()
                .id(training.getId())
                .user(userDto)
                .startTime(training.getStartTime())
                .endTime(training.getEndTime())
                .activityType(training.getActivityType())
                .distance(training.getDistance())
                .averageSpeed(training.getAverageSpeed())
                .build();
    }

    /**
     * Tworzy nową encję Training na podstawie DTO i użytkownika.
     *
     * @param dto  dane treningu
     * @param user użytkownik przypisany do treningu
     * @return nowa encja Training
     */
    public static Training toEntity(TrainingDto dto, User user) {
        return new Training(
                user,
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getActivityType(),
                dto.getDistance(),
                dto.getAverageSpeed()
        );
    }

    /**
     * Aktualizuje istniejącą encję Training na podstawie danych z DTO.
     *
     * @param training encja treningu do zaktualizowania
     * @param dto      dane treningu do zaktualizowania
     */
    public static void updateEntity(Training training, TrainingDto dto) {
        training.setStartTime(dto.getStartTime());
        training.setEndTime(dto.getEndTime());
        training.setActivityType(dto.getActivityType());
        training.setDistance(dto.getDistance());
        training.setAverageSpeed(dto.getAverageSpeed());
    }
}
