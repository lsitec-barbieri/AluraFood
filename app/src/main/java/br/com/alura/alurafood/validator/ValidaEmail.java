package br.com.alura.alurafood.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaEmail implements Validador{

    private final TextInputLayout textInputEmail;
    private final EditText campoEmail;
    private final ValidacaoPadrao validador;

    public ValidaEmail(TextInputLayout textInputEmail) {
        this.textInputEmail = textInputEmail;
        campoEmail = textInputEmail.getEditText();
        validador = new ValidacaoPadrao(textInputEmail);
    }

    private boolean validaPadrao(String email){
        if(email.matches(".+@.+\\..+")){
            return true;

        }
        textInputEmail.setError("E-mail inválido");
        return false;
    }

    @Override
    public boolean estaValido(){
        if(!validador.estaValido()) return false;
        String email = campoEmail.getText().toString();
        if(!validaPadrao(email)) return false;
        return true;
    }
}
