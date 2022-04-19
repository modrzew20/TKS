package applicationcontroller.api;

import applicationcontroller.adapters.ReservationServiceAdapters;
import applicationcontroller.modelRest.modelView.ReservationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationServiceAdapters reservationServiceAdapters;

    // TODO change all functions in controllers ,
    //  all of them should return Response/ResponseEntity and it should return 400 when action is not possible

    @Autowired
    public ReservationController(ReservationServiceAdapters reservationService) {
        this.reservationServiceAdapters = reservationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> readAllReservation() {
        return reservationServiceAdapters.readAllReservation();
    }


    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationView readOneReservation(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.readOneReservation(UUID.fromString(uuid));

    }


    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addReservation(@RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String clientsUUID,
                                         @RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String laneUUID,
                                         @RequestParam @NotBlank @Pattern(regexp =
                                                  "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String start,
                                         @RequestParam @NotBlank @Pattern(regexp =
                                                  "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String end) {
        ReservationView reservation = reservationServiceAdapters.addReservation(UUID.fromString(clientsUUID), UUID.fromString(laneUUID),LocalDateTime.parse(start),LocalDateTime.parse(end));
        if(reservation== null)  return ResponseEntity.status(400).build();
        return ResponseEntity.ok(reservation);
    }


    @GetMapping(value = "/pastClientReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> pastClientReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.pastClientReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/presentClientReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> presentClientReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.presentClientReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/pastLaneReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> pastLaneReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.pastLaneReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/presentLaneReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> presentLaneReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.presentLaneReservations(UUID.fromString(uuid));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationView updateReservation(@RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
                                             @RequestParam @NotBlank @Pattern(regexp =
                                                     "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String end) {
        return reservationServiceAdapters.endReservation(UUID.fromString(uuid), LocalDateTime.parse(end));
    }

    @PostMapping(value = "/end/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationView endReservation(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.endReservation(UUID.fromString(uuid), LocalDateTime.now());

    }

    @DeleteMapping(value = "/delete/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteReservation(@PathVariable("param") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        ReservationView reservation = reservationServiceAdapters.delete(UUID.fromString(uuid));
        if(reservation==null) return ResponseEntity.status(400).build();
        return ResponseEntity.ok(reservation);
    }

}
