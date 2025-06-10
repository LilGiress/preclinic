package com.medecineWebApp.Configuration.controller;


import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.payload.request.AuthenticationRequest;
import com.medecineWebApp.Configuration.payload.request.RegistrationRequest;
import com.medecineWebApp.Configuration.payload.response.AuthenticationResponse;
import com.medecineWebApp.Configuration.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
//@Tag(name = "Authentication")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegistrationRequest request) throws MessagingException {
        log.info("Roles récupérés: {}", request.getRoles());
        return ResponseEntity.ok(authenticationService.register(request));

    }

    //@PostMapping("/registerOther")
  //  public ResponseEntity<?> register(@RequestBody RegistrationOtherRequest request) throws MessagingException {
       /* if (authenticationService.existsByFirstnameOrLastname(request.getFirstname(), request.getLastname())) {
            return ResponseEntity
					.badRequest()
                    .body(new ResponseMessage<>(404,"Error: User with this Fisrtname or Lastname is already taken!"));

        }*/

//        if (authenticationService.existsByEmail(request.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new ResponseMessage<>(404,"Error: Email is already in use!"));
//        }
//        //  AuthenticationResponse response= authenticationService.register(request);
//        authenticationService.registerOther(request);
//        return ResponseEntity.ok().build();
//
//    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
            return ResponseEntity.ok( authenticationService.authenticate(request));
    }
    @GetMapping("/activate-account/{token}")
    public void confirmedAuthentication(@PathVariable String token) throws MessagingException {
                authenticationService.activateAccount(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Authentication authentication) {
        authenticationService.logout(request, response, authentication);
        return ResponseEntity.ok("Déconnexion réussie.");
    }



}
