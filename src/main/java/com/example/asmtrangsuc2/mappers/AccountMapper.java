package com.example.asmtrangsuc2.mappers;

import com.example.asmtrangsuc2.entities.Account;
import com.example.asmtrangsuc2.models.AccountModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountMapper {

    @Autowired
    private ModelMapper mapper;

    public Account convertToEntity(AccountModel accountModel) {
        return mapper.map(accountModel, Account.class);
    }

    public AccountModel convertToDTO(Account account) {
        return mapper.map(account, AccountModel.class);
    }
}
