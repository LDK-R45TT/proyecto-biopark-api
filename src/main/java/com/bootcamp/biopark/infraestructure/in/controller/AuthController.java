package com.bootcamp.biopark.infraestructure.in.controller;

import com.bootcamp.biopark.application.port.in.LoginUseCase;
import com.bootcamp.biopark.application.port.in.RegisterUseCase;
import com.bootcamp.biopark.application.port.in.UserCommand;
import com.bootcamp.biopark.application.port.in.shared.BaseResponse;
import com.bootcamp.biopark.infraestructure.in.dto.request.LoginRequest;
import com.bootcamp.biopark.infraestructure.in.dto.request.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    // records dto

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }


    @PostMapping("/register")
    public ResponseEntity<BaseResponse<String>> register(@Valid @RequestBody RegisterRequest request) {
        String tokenResponse =registerUseCase
                .register(new UserCommand(request.name(), request.email(), request.password()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.created("usuario registrado!", tokenResponse));

    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> login(@Valid @RequestBody LoginRequest request) {
        String tokenResponse =loginUseCase.login(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success("usuario logueado con exito!", tokenResponse));
    }


}
