package com.medecineWebApp.Configuration.controller;


import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.models.user.Users;

import com.medecineWebApp.Configuration.payload.request.ForgotPasswordRequest;
import com.medecineWebApp.Configuration.payload.request.ResetPasswordRequest;
import com.medecineWebApp.Configuration.payload.response.ResponseMessage;
import com.medecineWebApp.Configuration.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<UserDTO>> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok( userService.getUserById(id));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<UserDTO> forgotPassword(@RequestBody ForgotPasswordRequest request) {

            return ResponseEntity.ok().body(userService.forgotPassword(request.getEmail()));
    }
    @PostMapping("/reset-password")
   void updatePassword(@RequestBody ResetPasswordRequest request) {
     userService.resetPassword(request);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<Page<UserDTO>> getAllUser(
            @RequestParam(name = "pagenum", defaultValue = "0") int pagenum,
            @RequestParam(name = "pagesize",defaultValue = "10") int pageSize,
            @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(userService.getAllUsers(pagenum,pageSize));
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseMessage<UserDTO>> updateUserById(@PathVariable("id") Long id, @RequestBody Users user,@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(userService.updateUser(id, user));

    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable("id") Long id,@RequestHeader("Authorization")String token){
        userService.deleteUserById(id);
    }
    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurentUser(@RequestHeader("Authorization")String tokenHeader){
       return  ResponseEntity.ok( userService.getCurrentUser(tokenHeader));
    }

    @GetMapping("/user-roles")
    public ResponseEntity<List<UserDTO>> getUsersByRoles(@RequestParam List<String> roles) {
        return ResponseEntity.ok(userService.getUsersByRole(roles));
    }

}
