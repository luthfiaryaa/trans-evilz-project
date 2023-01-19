package transevilz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transevilz.domain.dao.Bank;
import transevilz.domain.dao.User;
import transevilz.domain.dto.BackOfficeUserResponseDTO;
import transevilz.domain.dto.FindBankDTO;
import transevilz.domain.dto.FindUserDTO;
import transevilz.jwt.JwtUtils;
import transevilz.repository.BankRepository;
import transevilz.repository.RoleRepository;
import transevilz.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<Object> getBank() {
        List<Bank> bankList = bankRepository.findAll();
        List<FindBankDTO> bankDTOS = new ArrayList<>();
        for (Bank bank : bankList){
            FindBankDTO item = FindBankDTO.builder().name(bank.getName())
                    .code(bank.getCode())
                    .build();
            bankDTOS.add(item);
        }

        return new ResponseEntity<>(bankDTOS, HttpStatus.OK);
    }
}
