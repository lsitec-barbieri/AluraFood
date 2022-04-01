package br.com.alura.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.formatter.FormataTelefoneComDdd;
import br.com.alura.alurafood.validator.ValidaCpf;
import br.com.alura.alurafood.validator.ValidaEmail;
import br.com.alura.alurafood.validator.ValidaTelefoneComDdd;
import br.com.alura.alurafood.validator.ValidacaoPadrao;
import br.com.alura.alurafood.validator.Validador;
import br.com.caelum.stella.format.CPFFormatter;

public class FormularioCadastroActivity extends AppCompatActivity {
    private final List<Validador> validadores = new ArrayList<>();

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
        Button botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean formularioEstaValido = true;
                for (Validador validador :
                        validadores) {
                    if(!validador.estaValido()){
                        formularioEstaValido = false;
                    }
                }
                if(formularioEstaValido){
                    cadastroRealizado();
                }
            }
        });
    }

    private void cadastroRealizado() {
        Toast.makeText(FormularioCadastroActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
    }

    private void configuraSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.formulario_cadastro_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.formulario_cadastro_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        ValidaEmail validador = new ValidaEmail(textInputEmail);
        validadores.add(validador);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCampoTelefone() {
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_cadastro_campo_telefone);
        EditText campoTelefoneComDdd = textInputTelefone.getEditText();
        ValidaTelefoneComDdd validador = new ValidaTelefoneComDdd(textInputTelefone);
        final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        validadores.add(validador);
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
        validadores.add(validador);

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
        validadores.add(validador);

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