package br.com.alura.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.alura.alurafood.R;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class FormularioCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        inicializaCampos();

    }

    private void inicializaCampos() {
        configuraCampoNome();
        configuraCampoCpf();
        configuraCampoTelefone();
        configuraCampoEmail();
        configuraSenha();
    }

    private void configuraSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.formulario_cadastro_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.formulario_cadastro_campo_email);
        adicionaValidacaoPadrao(textInputEmail);
    }

    private void configuraCampoTelefone() {
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_cadastro_campo_telefone);
        adicionaValidacaoPadrao(textInputTelefone);
    }

    private void configuraCampoCpf() {
        TextInputLayout textInputCpf = findViewById(R.id.formulario_cadastro_campo_cpf);
        EditText campoCpf = textInputCpf.getEditText();
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                CPFFormatter cpfFormatter = new CPFFormatter();

                String cpf = campoCpf.getText().toString();
                if (!hasFocus) {
                    if (validaCampoObrigatorio(cpf, textInputCpf)) return;
                    if (validaCampoCom11Digitos(cpf, textInputCpf)) return;
                    if (validaCalculoCpf(cpf, textInputCpf)) return;
                    removeErro(textInputCpf);

                    String cpfFormatado = cpfFormatter.format(cpf);
                    campoCpf.setText(cpfFormatado);

                } else {

                    try {
                        String cpfSemformato = cpfFormatter.unformat(cpf);
                        campoCpf.setText(cpfSemformato);
                    } catch (IllegalArgumentException e) {
                        
                    }


                }
            }
        });
    }

    private boolean validaCalculoCpf(String cpf, TextInputLayout textInputCpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            textInputCpf.setError("CPF inválido");
            return true;
        }
        return false;
    }

    private boolean validaCampoCom11Digitos(String texto, TextInputLayout textInputCampo) {
        if (texto.length() != 11) {
            textInputCampo.setError("O CPF precisa ter 11 digitios");
            return true;
        }
        return false;

    }

    private boolean validaCampoObrigatorio(String texto, TextInputLayout textInputCampo) {
        if (texto.isEmpty()) {
            textInputCampo.setError("Campo obrigatório");
            return true;
        }
        return false;

    }

    private void configuraCampoNome() {
        TextInputLayout textInputNome = findViewById(R.id.formulario_cadastro_campo_nome);
        adicionaValidacaoPadrao(textInputNome);
    }

    private void adicionaValidacaoPadrao(TextInputLayout textInputCampo) {
        EditText campo = textInputCampo.getEditText();
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String texto = campo.getText().toString();
                if (!hasFocus) {
                    if (validaCampoObrigatorio(texto, textInputCampo)) return;

                    removeErro(textInputCampo);
                }
            }
        });
    }

    private void removeErro(TextInputLayout textInputCampo) {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }
}