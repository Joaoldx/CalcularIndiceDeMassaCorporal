package infnet.edu.br.indicedemassacorporal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtAltura;
    private EditText txtPeso;
    private TextView txtImc;
    private TextView txtMsg;
    private Float imc;
    private String peso;
    private String altura;
    private Float floatAltura;
    private Float floatPeso;
    private Button btnCalcular;
    private Button btnLimpar;
    private Float aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAltura = (EditText) findViewById(R.id.txtAltura);
        txtPeso = (EditText) findViewById(R.id.txtPeso);
        txtImc = (TextView) findViewById(R.id.txtImc);
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // convertendo peso e altura para String
                peso = txtPeso.getText().toString();
                altura = txtAltura.getText().toString();
                if (peso.isEmpty()) {
                    txtMsg.setText("Por favor insira seu peso");
                    txtMsg.requestFocus();
                    txtImc.setText("");
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(txtPeso.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    return;
                }
                if (altura.isEmpty()) {
                    txtMsg.setText("Por favor insira sua altura");
                    txtMsg.requestFocus();
                    txtImc.setText("");
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(txtAltura.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    return;
                }
                if (!peso.isEmpty() || !altura.isEmpty()) {
                    // convertendo altura para float
                    floatAltura = Float.parseFloat(altura) / 100;
                    // convertendo peso para float
                    floatPeso = Float.parseFloat(peso);

                    // fazendo o calculo do imc
                    imc =  floatPeso / (floatAltura * floatAltura) ;
                    if (imc < 16) {
                        txtMsg.setText("Magreza Leve");
                    } else if (imc >= 16 && imc < 17) {
                        txtMsg.setText("Magreza moderada");
                    } else if (imc >= 17 && imc < 18.5) {
                        txtMsg.setText("Magreza leve");
                    } else if (imc >= 18.5 && imc < 25) {
                        txtPeso.setText("Saudável");
                    } else if (imc >= 25 && imc < 30) {
                        txtMsg.setText("Sobrepeso");
                    } else if (imc >= 30 && imc < 35) {
                        txtMsg.setText("Obesidade Grau I");
                    } else if (imc >= 35 && imc < 40) {
                        txtMsg.setText("Obesidade Grau II (Severa)");
                    } else if (imc >= 40) {
                        txtMsg.setText("Obesidade Grau III (Móbida)");
                    }
                    String imcStr = String.format("%.02f", imc);
                    txtImc.setText("Seu IMC é: " + imcStr.toString());
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(txtAltura.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    in.hideSoftInputFromWindow(txtPeso.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    txtMsg.setText("Por favor preencha todos os campos.");
                }
                btnLimpar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtImc.setText("");
                        txtPeso.setText("");
                        txtAltura.setText("");
                        txtMsg.setText("");
                    }
                });
            }
        });
    }
}
