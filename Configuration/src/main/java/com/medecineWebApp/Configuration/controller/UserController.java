package com.medecineWebApp.Configuration.controller;


import com.medecineWebApp.Configuration.config.jwt.JwtService;
import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.mapper.UserMapper;
import com.medecineWebApp.Configuration.models.user.User;
import com.medecineWebApp.Configuration.payload.request.ChangePasswordRequest;
import com.medecineWebApp.Configuration.payload.response.ResponseMessage;
import com.medecineWebApp.Configuration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<UserDTO>> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok( userService.getUserById(id));
    }
    @GetMapping("/forgot-password")
    public ResponseEntity<Optional<User>> getUserByEmail(@RequestParam(name = "email") String email) {
        return  ResponseEntity.ok(userService.forgotPassword(email));
    }
    @PostMapping("/reset-password")
    void updatePassword(@RequestBody ChangePasswordRequest request) {
        userService.updatepassword(request);
    }
    @GetMapping("/allUsers")
    public ResponseEntity<Page<UserDTO>> getAllUser(
            @RequestParam(name = "pagenum", defaultValue = "0") int pagenum,
            @RequestParam(name = "pagesize",defaultValue = "10") int pageSize,
            @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(userService.getAllUsers(pagenum,pageSize));
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseMessage<UserDTO>> updateUserById(@PathVariable("id") Long id, @RequestBody User user,@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(userService.updateUser(id, user));

    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable("id") Long id,@RequestHeader("Authorization")String token){
        userService.deleteUserById(id);
    }
    @GetMapping("/current-user")
    public ResponseEntity<Optional<User>> getCurentUser(@RequestHeader("Authorization")String tokenHeader){
       Optional<User> user = userService.getCurrentUser(tokenHeader);
       if(user == null){
           throw new UsernameNotFoundException("User not found");
       }
       return  ResponseEntity.ok(user);
    }


}
