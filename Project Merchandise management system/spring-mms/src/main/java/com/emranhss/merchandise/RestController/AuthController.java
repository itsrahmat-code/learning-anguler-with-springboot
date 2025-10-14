package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.dto.AuthDTO;
import com.emranhss.merchandise.dto.UserDTO;
import com.emranhss.merchandise.entity.User;
import com.emranhss.merchandise.repository.TokenRepo;
import com.emranhss.merchandise.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenRepo tokenRepo;


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUserDTO() {
        List<UserDTO> users = authService.getAllUsersDTOS();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/active/{id}")
    public ResponseEntity<String> activeUser(@PathVariable("id") int id){

        String response= authService.activeUser(id);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody User request){
        return ResponseEntity.ok(authService.authenticate(request));

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header.");
        }

        String token = authHeader.substring(7);  // Strip "Bearer "

        tokenRepo.findByToken(token).ifPresent(savedToken -> {
            savedToken.setLogout(true);  // Mark token as logged out
            tokenRepo.save(savedToken);
        });

        return ResponseEntity.ok("Logged out successfully.");
    }

}
