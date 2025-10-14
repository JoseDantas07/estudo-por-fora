package estudo.picpay.service.impl;

import estudo.picpay.entity.UserEntity;
import estudo.picpay.entity.UserType;
import estudo.picpay.exception.CpfOrCnpjException;
import estudo.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Checks {
    @Autowired
    UserRepository userRepository;


    public String chekccpfOrCnpj(String cpfOrCnpj){

        cpfOrCnpj = cpfOrCnpj.replace("-","").replace(".","").replace("/","").replace(" ", "");

        if (cpfOrCnpj.isEmpty()) {throw new IllegalArgumentException("cpf ou cnpj esta vazio");}
        try{
            var cpfOrCnpjNumber = Long.parseLong(cpfOrCnpj);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Não pode conter letras");
        }

        if (cpfOrCnpj.length() == 11 || cpfOrCnpj.length() == 14) {return cpfOrCnpj;}

        throw new CpfOrCnpjException("O cpf ou cnpj foi digitado incorreto");
    }
    public String checkEmail(String email){
        if (email.isEmpty()) {throw new IllegalArgumentException("O email esta vazio");}
        if (email.endsWith("@gmail.com")) {return email;}
        if (email.endsWith("@hotmail.com")) {return email;}

        throw new IllegalArgumentException("Email invalido");
    }

    public void verifyUserType(UserEntity userEntity) {
        if (userEntity.getUserType().equals(UserType.MERCHANT)) {
            throw new IllegalArgumentException("Usuario lojista não pode fazer transferencia. apenas receber");
        }
    }
    public void verifyMoney(UserEntity userEntity, BigDecimal amount){
        if (userEntity.getBalance().compareTo(amount) < 0){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
}
