//package com.example.applicationcontroller.api;
//
//import exceptions.LoginInUseException;
//import lombok.RequiredArgsConstructor;
//import model.AccessLevel;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import service.UserService;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import java.util.UUID;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/user")
//@Validated
//public class UserController {
//
//
//    private final UserService userService;
//
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity readAllUser() {
//        return ResponseEntity.ok(userService.readAllUser());
//    }
//
//
//    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity readUser(@PathVariable("uuid") @NotBlank @Pattern(regexp =
//            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
//        return ResponseEntity.ok(userService.readOneUser(UUID.fromString(uuid)));
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity addUser(@RequestParam("accessLevel") @NotBlank @Pattern(regexp = "Administrator|Client|ResourceAdministrator", message = "Field accessLevel must be Administrator|Client|ResourceAdministrator.") String accessLevel,
//                                  @RequestParam("login") @NotBlank String login,
//                                  @RequestParam("password") @NotBlank String password) {
//        try {
//            return ResponseEntity.ok(userService.addUser(AccessLevel.valueOf(accessLevel), login, password));
//        } catch (LoginInUseException e) {
//            return ResponseEntity.status(400).build();
//        }
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity updateUser(@RequestParam("id") @NotBlank @Pattern(regexp =
//            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
//                                     @RequestParam("login") String login,
//                                     @RequestParam("password") String password) {
//        try {
//            return ResponseEntity.ok(userService.updateUser(UUID.fromString(uuid), login, password));
//        } catch (LoginInUseException e) {
//            return ResponseEntity.status(400).build();
//        }
//    }
//
//    @RequestMapping(value = "/readMany/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity readManyUsers(@PathVariable("login") @NotBlank String login) {
//        return ResponseEntity.ok(userService.readManyUser(login));
//    }
//
//    @RequestMapping(value = "/deactivate/{uuid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity deactivateUser(@PathVariable @NotBlank @Pattern(regexp =
//            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
//        return ResponseEntity.ok(userService.deactivateUser(UUID.fromString(uuid)));
//    }
//
//    @RequestMapping(value = "/activate/{uuid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity activateUser(@PathVariable @NotBlank @Pattern(regexp =
//            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
//        return ResponseEntity.ok(userService.activateUser(UUID.fromString(uuid)));
//    }
//
//}
