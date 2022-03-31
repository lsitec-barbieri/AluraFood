package br.com.alura.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.alura.alurafood.R;

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
        adicionaValidacaoPadrao(textInputCpf);
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
                if (!hasFocus && texto.isEmpty()) {
                    textInputCampo.setError("Campo obrigatório");

                } else {
                    textInputCampo.setErrorEnabled(false);
                }
            }
        });
    }
}