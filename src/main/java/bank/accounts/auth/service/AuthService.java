package bank.accounts.auth.service;

import bank.accounts.auth.dto.request.AuthenticationRequest;
import bank.accounts.auth.dto.request.RegisterRequest;
import bank.accounts.auth.dto.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
