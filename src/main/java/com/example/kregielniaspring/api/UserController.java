package com.example.kregielniaspring.api;

import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.managers.UserManager;

import com.example.kregielniaspring.model.Lane;
import com.example.kregielniaspring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class UserController {


    private final UserManager userManager;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readAllUser() {
        return ResponseEntity.ok(userManager.readAllUser());
    }


    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readUser(@PathVariable("uuid") @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid){
        return ResponseEntity.ok(userManager.readOneUser(UUID.fromString(uuid)));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestParam("accessLevel") @NotBlank @Pattern(regexp = "Administrator|Client|ResourceAdministrator", message = "Field accessLevel must be Administrator|Client|ResourceAdministrator.") String accessLevel,
                                  @RequestParam("login") @NotBlank String login,
                                  @RequestParam("password") @NotBlank String password) {
        try {
            return ResponseEntity.ok(userManager.addUser(accessLevel, login, password));
        } catch (LoginInUseException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestParam("id") @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
                                     @RequestParam("login") String login,
                                     @RequestParam("password") String password) {
        try {
            return ResponseEntity.ok(userManager.updateUser(UUID.fromString(uuid), login, password));
        } catch (LoginInUseException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @RequestMapping(value = "/readMany/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readManyUsers(@PathVariable("login") @NotBlank String login){
        return ResponseEntity.ok(userManager.readManyUser(login));
    }

    @RequestMapping(value = "/deactivate/{uuid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deactivateUser(@PathVariable @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return ResponseEntity.ok(userManager.deactivateUser(UUID.fromString(uuid)));
    }

    @RequestMapping(value = "/activate/{uuid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity activateUser(@PathVariable @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return ResponseEntity.ok(userManager.activateUser(UUID.fromString(uuid)));
    }

}
