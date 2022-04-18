package applicationcontroller.api;

import model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.ReservationService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> readAllReservation() {
        return reservationService.readAllReservation();
    }


    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation readOneReservation(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationService.readOneReservation(UUID.fromString(uuid));

    }


    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation addReservation(@RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String clientsUUID,
                                      @RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String laneUUID,
                                      @RequestParam @NotBlank @Pattern(regexp =
                                              "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String start,
                                      @RequestParam @NotBlank @Pattern(regexp =
                                              "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String end) {
        return reservationService.addReservation(UUID.fromString(clientsUUID), UUID.fromString(laneUUID), LocalDateTime.parse(start), LocalDateTime.parse(end));
    }


    @GetMapping(value = "/pastClientReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> pastClientReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationService.pastClientReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/presentClientReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> presentClientReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationService.presentClientReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/pastLaneReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> pastLaneReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationService.pastLaneReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/presentLaneReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> presentLaneReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationService.presentLaneReservations(UUID.fromString(uuid));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation updateReservation(@RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
                                         @RequestParam @NotBlank @Pattern(regexp =
                                                 "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String end) {
        return reservationService.endReservation(UUID.fromString(uuid), LocalDateTime.parse(end));
    }

    @PostMapping(value = "/end/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation endReservation(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationService.endReservation(UUID.fromString(uuid), LocalDateTime.now());

    }

    @DeleteMapping(value = "/delete/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation deleteReservation(@PathVariable("param") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationService.delete(UUID.fromString(uuid));
    }

}
