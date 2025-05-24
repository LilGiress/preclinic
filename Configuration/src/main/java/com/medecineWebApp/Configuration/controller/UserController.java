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
@RequestMapping("user")

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
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            String responseMessage = userService.requestPasswordReset(request.getEmail());
            return ResponseEntity.ok().body(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue lors de la demande de réinitialisation du mot de passe.");
        }
    }
    @PostMapping("/reset-password")
    ResponseEntity<?> updatePassword(@RequestBody ResetPasswordRequest request) {
      return ResponseEntity.ok(userService.resetPassword(request))  ;
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
    public ResponseEntity<Users> getCurentUser(@RequestHeader("Authorization")String tokenHeader){
       Users user = userService.getCurrentUser(tokenHeader);
       if(user == null){
           throw new UsernameNotFoundException("User not found");
       }
       return  ResponseEntity.ok(user);
    }

    @GetMapping("/user-roles")
    public ResponseEntity<List<UserDTO>> getUsersByRoles(@RequestParam List<String> roles) {
        return ResponseEntity.ok(userService.getUsersByRole(roles));
    }

}
