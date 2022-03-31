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

        TextInputLayout textInputNome = findViewById(R.id.formulario_cadastro_campo_nome);
        EditText campoNome = textInputNome.getEditText();
        adicionaValidacaoPadrao(campoNome);

        TextInputLayout textInputCpf = findViewById(R.id.formulario_cadastro_campo_cpf);
        EditText campoCpf = textInputCpf.getEditText();
        adicionaValidacaoPadrao(campoCpf);

        TextInputLayout textInputTelefone = findViewById(R.id.formulario_cadastro_campo_telefone);
        EditText campoTelefone = textInputTelefone.getEditText();
        adicionaValidacaoPadrao(campoTelefone);

        TextInputLayout textInputEmail = findViewById(R.id.formulario_cadastro_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        adicionaValidacaoPadrao(campoEmail);

        TextInputLayout textInputSenha = findViewById(R.id.formulario_cadastro_campo_senha);
        EditText campoSenha = textInputSenha.getEditText();
        adicionaValidacaoPadrao(campoSenha);

    }

    private void adicionaValidacaoPadrao(EditText campo) {
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                String texto = campo.getText().toString();

                if (texto.isEmpty()) {
                    campo.setError("Campo obrigatório");
                }
            }
        });
    }
}