package com.example.asmtrangsuc2.utilities;

import com.example.asmtrangsuc2.entities.Account;
import com.example.asmtrangsuc2.repositories.IAccountRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptUtils {

    @Autowired
    private IAccountRepository accountRepository;

    public String encrypt(String origin) {

        return BCrypt.hashpw(origin, BCrypt.gensalt());
    }

    	public boolean check1(String origin, String encrypted) {
		return BCrypt.checkpw(origin, encrypted);
	}


    public boolean checkPassword(String username, String password) {
        Account account = accountRepository.findByUsernameAndPassword(username, password);
        if (account != null) {
            String hashedPassword = account.getPassword();
            return BCrypt.checkpw(password, hashedPassword);
        }
        return true;
    }
}
