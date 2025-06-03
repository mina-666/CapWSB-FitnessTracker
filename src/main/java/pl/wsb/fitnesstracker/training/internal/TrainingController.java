package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Kontroler REST obsługujący operacje na treningach.
 * Umożliwia tworzenie, pobieranie i aktualizowanie treningów.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    /**
     * Serwis obsługujący logikę biznesową dotyczącą treningów.
     */
    private final TrainingServiceImpl trainingService;

    /**
     * Pobiera wszystkie dostępne treningi.
     *
     * @return lista wszystkich treningów
     */
    @GetMapping
    public List<TrainingDto> getAll() {
        return trainingService.getAllTrainings();
    }

    /**
     * Pobiera treningi dla danego użytkownika.
     *
     * @param userId ID użytkownika
     * @return lista treningów użytkownika
     */
    @GetMapping("/user/{userId}")
    public List<TrainingDto> getByUser(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId);
    }

    /**
     * Pobiera treningi zakończone po podanej dacie.
     *
     * @param date data graniczna (format ISO)
     * @return lista treningów zakończonych po dacie
     */
    @GetMapping("/after/{date}")
    public List<TrainingDto> getAfterDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return trainingService.getTrainingsAfterDate(date);
    }

    /**
     * Pobiera treningi o określonym typie aktywności.
     *
     * @param activity typ aktywności (np. RUNNING)
     * @return lista treningów danego typu
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getByActivity(@RequestParam("activityType") ActivityType activity) {
        return trainingService.getTrainingsByActivity(activity);
    }

    /**
     * Tworzy nowy trening.
     *
     * @param dto dane nowego treningu
     * @return utworzony trening
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto create(@RequestBody TrainingDto dto) {
        return trainingService.createTraining(dto);
    }

    /**
     * Aktualizuje istniejący trening.
     *
     * @param id  ID treningu do aktualizacji
     * @param dto nowe dane treningu
     * @return zaktualizowany trening
     */
    @PutMapping("/{id}")
    public TrainingDto update(@PathVariable Long id, @RequestBody TrainingDto dto) {
        return trainingService.updateTraining(id, dto);
    }

    /**
     * Pobiera treningi zakończone po podanej dacie.
     * (Alias do {@link #getAfterDate(Date)})
     *
     * @param date data graniczna (format ISO)
     * @return lista treningów zakończonych po dacie
     */
    @GetMapping("/finished/{date}")
    public List<TrainingDto> getFinishedAfter(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return trainingService.getTrainingsAfterDate(date);
    }

    /**
     * Pobiera trening o podanym ID.
     *
     * @param id ID treningu
     * @return trening z podanym ID
     */
    @GetMapping("/{id}")
    public TrainingDto getById(@PathVariable Long id) {
        return trainingService.getTrainingById(id);
    }
}
