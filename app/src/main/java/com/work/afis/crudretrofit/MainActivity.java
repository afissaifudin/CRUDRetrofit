package com.work.afis.crudretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://sulistiyanto.000webhostapp.com/";
    private RadioButton radioButton;
    private ProgressDialog progress;

    @BindView(R.id.radioButtonSesi) RadioGroup radioGroup;
    @BindView(R.id.etNPM) EditText etNPM;
    @BindView(R.id.etNama) EditText etNama;
    @BindView(R.id.etKelas) EditText etKelas;

    @OnClick(R.id.btnDaftar) void daftar(){
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading....");
        progress.show();

        String npm = etNPM.getText().toString();
        String nama = etNama.getText().toString();
        String kelas = etKelas.getText().toString();

        int selectedID = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedID);
        String sesi = radioButton.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.daftar(npm,nama,kelas,sesi);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")){
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(MainActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
