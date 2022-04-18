package applicationcontroller.api;

import exceptions.LoginInUseException;
import model.AccessLevel;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> readAllUser() {
        return userService.readAllUser();
    }


    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User readUser(@PathVariable("uuid") @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return userService.readOneUser(UUID.fromString(uuid));
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestParam("accessLevel") @NotBlank @Pattern(regexp = "Administrator|Client|ResourceAdministrator", message = "Field accessLevel must be Administrator|Client|ResourceAdministrator.") String accessLevel,
                        @RequestParam("login") @NotBlank String login,
                        @RequestParam("password") @NotBlank String password) throws LoginInUseException {
        return userService.addUser(AccessLevel.valueOf(accessLevel), login, password);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestParam("id") @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password) throws LoginInUseException {

        return userService.updateUser(UUID.fromString(uuid), login, password);
    }

    @GetMapping(value = "/readMany/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> readManyUsers(@PathVariable("login") @NotBlank String login) {
        return userService.readManyUser(login);
    }

    @PostMapping(value = "/deactivate/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User deactivateUser(@PathVariable @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return userService.deactivateUser(UUID.fromString(uuid));
    }

    @PostMapping(value = "/activate/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User activateUser(@PathVariable @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return userService.activateUser(UUID.fromString(uuid));
    }

}
