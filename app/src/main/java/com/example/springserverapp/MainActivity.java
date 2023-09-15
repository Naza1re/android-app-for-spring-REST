package com.example.springserverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.springserverapp.model.Employee;
import com.example.springserverapp.retrofit.EmployeeApi;
import com.example.springserverapp.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditBranch = findViewById(R.id.form_textFieldBranch);
        TextInputEditText inputEditLocation = findViewById(R.id.form_textFieldLocation);

        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);
        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi =  retrofitService.getRetrofit().create(EmployeeApi.class);
        buttonSave.setOnClickListener(view ->{
            String name = inputEditName.getText().toString();
            String branch = inputEditBranch.getText().toString();
            String location = inputEditLocation.getText().toString();
            Employee employee = new Employee(name,branch,location);
            employeeApi.save(employee)
                    .enqueue(new Callback<Employee>() {
                        @Override
                        public void onResponse(Call<Employee> call, Response<Employee> response) {
                            Toast.makeText(MainActivity.this,"Save succsessful",Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<Employee> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"Save failed",Toast.LENGTH_LONG).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Erorr",t);
                        }
                    });

        });

    }


}