package br.com.alura.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.formatter.FormataTelefoneComDdd;
import br.com.alura.alurafood.validator.ValidaCpf;
import br.com.alura.alurafood.validator.ValidaTelefoneComDdd;
import br.com.alura.alurafood.validator.ValidacaoPadrao;
import br.com.caelum.stella.format.CPFFormatter;

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
        EditText campoTelefoneComDdd = textInputTelefone.getEditText();
        ValidaTelefoneComDdd validador = new ValidaTelefoneComDdd(textInputTelefone);
        final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        campoTelefoneComDdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String telefoneComDdd = campoTelefoneComDdd.getText().toString();
                if(!hasFocus){
                    validador.estaValido();
                }else {
                    String telefoneComDddSemFormatacao = formatador.removeFormato(telefoneComDdd);
                    campoTelefoneComDdd.setText(telefoneComDddSemFormatacao);
                }
            }
        });
    }



    private void configuraCampoCpf() {
        TextInputLayout textInputCpf = findViewById(R.id.formulario_cadastro_campo_cpf);
        EditText campoCpf = textInputCpf.getEditText();
        CPFFormatter cpfFormatter = new CPFFormatter();

        ValidaCpf validador = new ValidaCpf(textInputCpf, campoCpf);
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String cpf = campoCpf.getText().toString();

                if (!hasFocus) {
                    validador.estaValido();
                } else {
                    removeFormatacao(cpfFormatter, campoCpf);
                }
            }
        });
    }

    private void removeFormatacao(CPFFormatter cpfFormatter, EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemformato = cpfFormatter.unformat(cpf);
            campoCpf.setText(cpfSemformato);
        } catch (IllegalArgumentException e) {

        }
    }


    private void configuraCampoNome() {
        TextInputLayout textInputNome = findViewById(R.id.formulario_cadastro_campo_nome);
        adicionaValidacaoPadrao(textInputNome);
    }

    private void adicionaValidacaoPadrao(TextInputLayout textInputCampo) {
        EditText campo = textInputCampo.getEditText();
        ValidacaoPadrao validador = new ValidacaoPadrao(textInputCampo);
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    validador.estaValido();
                }
            }
        });
    }


}