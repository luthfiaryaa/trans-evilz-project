package transevilz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import transevilz.domain.dao.Bank;
import transevilz.domain.dto.FindBankDTO;
import transevilz.repository.BankRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    BankRepository bankRepository;

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
