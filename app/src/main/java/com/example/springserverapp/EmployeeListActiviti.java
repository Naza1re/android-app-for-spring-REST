package com.example.springserverapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.springserverapp.adapter.EmployeeAdapter;
import com.example.springserverapp.model.Employee;
import com.example.springserverapp.retrofit.EmployeeApi;
import com.example.springserverapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActiviti extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list_activiti);

        recyclerView = findViewById(R.id.employeeList_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadEmployees();
    }

    private void loadEmployees() {


        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi =  retrofitService.getRetrofit().create(EmployeeApi.class);

        employeeApi.getAllEmployees()
                .enqueue(new Callback<List<Employee>>() {
                    @Override
                    public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                        populateListView(response.body());

                    }


                    @Override
                    public void onFailure(Call<List<Employee>> call, Throwable t) {
                        Toast.makeText(EmployeeListActiviti.this,"Failed to load employees",Toast.LENGTH_SHORT).show();

                    }
                });


    }
    private void populateListView(List<Employee> employees) {
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(employees);
        recyclerView.setAdapter(employeeAdapter);




    }
}