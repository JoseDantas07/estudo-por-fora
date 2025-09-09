package estudo.sistemaCadastro.service.impl;

import org.springframework.stereotype.Service;

@Service
public class ValidateCpf {
    public boolean isCpf(String cpf){
        boolean whileFist = true;
        boolean whileSecond = true;
        while (whileFist) {
            while(whileSecond) {
                try {
                    Long cpfNumero = Long.parseLong(cpf);
                } catch (NumberFormatException ex) {
                    throw new RuntimeException("cpf tem que ser numero");
                }
                whileSecond = false;
            }

            if (cpf.length() != 11){
                throw new RuntimeException("O cpf tem que ter 11 digitos");
            }else {
                whileFist = false;
            }

        }
        return true;
    }
}
