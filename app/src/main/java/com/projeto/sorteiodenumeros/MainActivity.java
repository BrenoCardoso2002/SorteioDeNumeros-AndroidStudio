package com.projeto.sorteiodenumeros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //EditText:
        EditText PrimeiroNumero = findViewById(R.id.ET_PrimeiroNumero);
        PrimeiroNumero.setText("");
        EditText SegundoNumero = findViewById(R.id.ET_SegundoNumero);
        SegundoNumero.setText("");
        // Button:
        Button Sortear = findViewById(R.id.BT_Sortear);
        // TextView:
        TextView NumeroSorteado = findViewById(R.id.TV_NumeroSorteado);
        NumeroSorteado.setText("X");

        // Evento de clique do botão:
        Sortear.setOnClickListener(view -> {
            // Ao receber click inicia a função a seguir:
            String Numero = SorteiaUmNumero(PrimeiroNumero.getText().toString(), SegundoNumero.getText().toString());

            //Exibe o Número na tela:
            NumeroSorteado.setText(Numero);
        });

        // Evento de mudança de texto, campo 1:
        PrimeiroNumero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NumeroSorteado.setText("X");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NumeroSorteado.setText("X");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                NumeroSorteado.setText("X");
            }
        });

        // Evento de mudança de texto, campo 2:
        SegundoNumero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NumeroSorteado.setText("X");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NumeroSorteado.setText("X");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                NumeroSorteado.setText("X");
            }
        });
    }

    private String SorteiaUmNumero(String Primeiro, String Segundo) {
        if (VerificaBraco(Primeiro, Segundo)){
            AlertaErro(getString(R.string.Erro1));
        }else{
            int PN = Integer.parseInt(Primeiro);
            int SN = Integer.parseInt(Segundo);
            if (!VerificaOrdem(PN, SN)){
                AlertaErro(getString(R.string.Erro2));
            }else{
                Random random = new Random();
                return Integer.toString(random.nextInt((SN - PN) + 1) + PN);
            }
        }
        return "X";
    }

    private boolean VerificaOrdem(int primeiro, int segundo) {
        return primeiro <= segundo;
    }

    private boolean VerificaBraco(@NonNull String primeiro, String segundo) {
        return primeiro.isEmpty() || segundo.isEmpty();
    }

    private void AlertaErro(String msg) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Erro!")
                .setMessage(msg)
                .setPositiveButton("Ok", (dialogInterface, i) -> dialogInterface.cancel()).show();
    }
}