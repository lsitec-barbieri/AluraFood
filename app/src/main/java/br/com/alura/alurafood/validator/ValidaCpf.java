package br.com.alura.alurafood.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf {

    private final TextInputLayout textInputCpf;
    private final EditText campoCpf;
    private final ValidacaoPadrao validadorPadrao;
    private final CPFFormatter formatador = new CPFFormatter();

    public ValidaCpf(TextInputLayout textInputCpf, EditText campoCpf) {
        this.textInputCpf = textInputCpf;
        this.campoCpf = campoCpf;
        this.validadorPadrao = new ValidacaoPadrao(textInputCpf);
    }

    private boolean validaCalculoCpf(String cpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            textInputCpf.setError("CPF inválido");
            return true;
        }
        return false;
    }

    private boolean validaCampoCom11Digitos(String cpf) {
        if (cpf.length() != 11) {
            textInputCpf.setError("O CPF precisa ter 11 digitios");
            return true;
        }
        return false;

    }

    public boolean estaValido(){
        String cpf = campoCpf.getText().toString();
        if(validadorPadrao.estaValido()) return true;
        if(validaCampoCom11Digitos(cpf)) return true;
        if(validaCalculoCpf(cpf)) return true;

        adicionaFormatacao(cpf);

        return false;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }
}
