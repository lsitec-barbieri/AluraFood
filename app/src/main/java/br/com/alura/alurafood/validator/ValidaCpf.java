package br.com.alura.alurafood.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf implements Validador{

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

    @Override
    public boolean estaValido(){
        if(!validadorPadrao.estaValido()) return false;
        String cpf = campoCpf.getText().toString();
        String cpfSemformato = cpf;
        try {
            cpfSemformato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemformato);
        } catch (IllegalArgumentException e) {

        }
        if(validaCampoCom11Digitos(cpfSemformato)) return false;
        if(validaCalculoCpf(cpfSemformato)) return false;

        adicionaFormatacao(cpfSemformato);

        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }
}
