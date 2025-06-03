package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Serwis obsługujący logikę biznesową dotyczącą treningów.
 * Pozwala na pobieranie, tworzenie i aktualizację danych treningowych.
 */
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl {

    /**
     * Repozytorium JPA do operacji na encji Training.
     */
    private final TrainingRepository trainingRepository;

    /**
     * Repozytorium użytkowników do pobierania danych o właścicielu treningu.
     */
    private final UserRepository userRepository;

    /**
     * Pobiera wszystkie treningi.
     *
     * @return lista treningów jako TrainingDto
     */
    public List<TrainingDto> getAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(TrainingMapper::toDto)
                .collect(toList());
    }

    /**
     * Pobiera treningi dla wskazanego użytkownika.
     *
     * @param userId ID użytkownika
     * @return lista treningów danego użytkownika
     */
    public List<TrainingDto> getTrainingsByUserId(Long userId) {
        return trainingRepository.findAll().stream()
                .filter(t -> t.getUser().getId().equals(userId))
                .map(TrainingMapper::toDto)
                .collect(toList());
    }

    /**
     * Pobiera treningi, które zakończyły się po podanej dacie.
     *
     * @param date data graniczna
     * @return lista treningów zakończonych po dacie
     */
    public List<TrainingDto> getTrainingsAfterDate(Date date) {
        return trainingRepository.findAll().stream()
                .filter(t -> t.getEndTime().after(date))
                .map(TrainingMapper::toDto)
                .collect(toList());
    }

    /**
     * Pobiera treningi o określonym typie aktywności.
     *
     * @param activity typ aktywności (np. RUNNING)
     * @return lista treningów z daną aktywnością
     */
    public List<TrainingDto> getTrainingsByActivity(ActivityType activity) {
        return trainingRepository.findAll().stream()
                .filter(t -> t.getActivityType().equals(activity))
                .map(TrainingMapper::toDto)
                .collect(toList());
    }

    /**
     * Tworzy nowy trening na podstawie danych DTO.
     *
     * @param dto dane treningu
     * @return utworzony trening jako TrainingDto
     */
    public TrainingDto createTraining(TrainingDto dto) {
        User user = userRepository.findById(dto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Training training = TrainingMapper.toEntity(dto, user);
        Training saved = trainingRepository.save(training);
        return TrainingMapper.toDto(saved);
    }

    /**
     * Aktualizuje trening o podanym ID.
     *
     * @param id  ID treningu do aktualizacji
     * @param dto nowe dane treningu
     * @return zaktualizowany trening jako TrainingDto
     */
    public TrainingDto updateTraining(Long id, TrainingDto dto) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException(id));

        TrainingMapper.updateEntity(training, dto);
        Training saved = trainingRepository.save(training);
        return TrainingMapper.toDto(saved);
    }

    /**
     * Pobiera pojedynczy trening na podstawie ID.
     *
     * @param id ID treningu
     * @return trening jako TrainingDto
     */
    public TrainingDto getTrainingById(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException(id));
        return TrainingMapper.toDto(training);
    }
}
