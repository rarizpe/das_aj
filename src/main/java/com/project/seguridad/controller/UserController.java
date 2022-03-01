package com.project.seguridad.controller;

import com.project.seguridad.dto.user.UserDto;
import com.project.seguridad.dto.user.UserInDto;
import com.project.seguridad.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController  {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/create", produces = "application/json")
    public ResponseEntity createUser(@RequestBody UserInDto dto) throws NotFoundException {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PutMapping(value = "/user/update", produces = "application/json")
    public ResponseEntity updateUser(@RequestBody UserDto dto) throws NotFoundException {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @GetMapping(value = "/user/getUserByUuid/{userUuid}", produces = "application/json")
    public ResponseEntity getUserByUuid(@PathVariable String userUuid) throws NotFoundException {
        return ResponseEntity.ok(userService.getUserByUuid(userUuid));
    }

    @DeleteMapping(value = "/user/delete", produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable String userUuid) throws NotFoundException {
        return ResponseEntity.ok(userService.deleteUser(userUuid));
    }
}
