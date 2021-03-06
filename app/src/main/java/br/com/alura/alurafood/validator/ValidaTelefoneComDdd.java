package br.com.alura.alurafood.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.alura.alurafood.formatter.FormataTelefoneComDdd;

public class ValidaTelefoneComDdd implements Validador{

    private final TextInputLayout textInputTelefoneComDdd;
    private final ValidacaoPadrao validacaoPadrao;
    private final EditText campoTelefoneComDdd;
    private final FormataTelefoneComDdd formatador;

    public ValidaTelefoneComDdd(TextInputLayout textInputTelefoneComDdd) {
        this.textInputTelefoneComDdd = textInputTelefoneComDdd;
        this.validacaoPadrao = new ValidacaoPadrao(textInputTelefoneComDdd);
        this.campoTelefoneComDdd = textInputTelefoneComDdd.getEditText();
        this.formatador = new FormataTelefoneComDdd();
    }

    private boolean validaEntreDezouOnzeDigitos (String telefoneComDdd){
        int digitos = telefoneComDdd.length();
        if(digitos < 10 || digitos > 11){
            textInputTelefoneComDdd.setError("Telefone deve ter entre 10 e 11 digitos");
            return true;
        }
        return false;
    }

    @Override
    public boolean estaValido(){
        if(!validacaoPadrao.estaValido()) return false;
        String telefoneComDdd = campoTelefoneComDdd.getText().toString();
        String telefoneComDddSemFormatacao = formatador.removeFormato(telefoneComDdd);
        if(validaEntreDezouOnzeDigitos(telefoneComDddSemFormatacao)) return false;
        adicionaFormatacao(telefoneComDddSemFormatacao);
        return true;
    }

    private void adicionaFormatacao(String telefoneComDdd) {
        String telefoneComDddFormatado = formatador.formata(telefoneComDdd);
        campoTelefoneComDdd.setText(telefoneComDddFormatado);
    }


}
