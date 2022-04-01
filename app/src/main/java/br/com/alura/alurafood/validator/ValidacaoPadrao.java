package br.com.alura.alurafood.validator;

import android.text.Editable;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidacaoPadrao {

    private final TextInputLayout textInputCampo;
    private final EditText campo;

    public ValidacaoPadrao(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.campo = this.textInputCampo.getEditText();
    }

    public boolean validaCampoObrigatorio() {
        String texto = campo.getText().toString();
        if (texto.isEmpty()) {
            textInputCampo.setError("Campo obrigatório");
            return true;
        }
        return false;

    }

    public boolean estaValido() {
        if (validaCampoObrigatorio()) return true;
        removeErro();
        return false;
    }

    public void removeErro() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }
}
