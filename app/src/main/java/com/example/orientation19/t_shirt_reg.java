package com.example.orientation19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orientation19.Database.MyDatabase;
import com.example.orientation19.MyPojos.TshirtRegDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class t_shirt_reg extends Fragment {
    private MyDatabase myDatabase;
    private TextView tshirtSizeTV,tv_roll,msg;
    private RelativeLayout tShirtRegisteredRL,progressRL;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_shirt_reg, container, false);
        tShirtRegisteredRL=view.findViewById(R.id.tShirt_registered_rl);
        tshirtSizeTV=view.findViewById(R.id.tshirt_size_tv);
        myDatabase=new MyDatabase(getActivity());
        tv_roll=view.findViewById(R.id.tv_roll);
        tv_roll.setText(myDatabase.getRollNumber());
        progressRL=view.findViewById(R.id.progress_rl);
        msg=view.findViewById(R.id.t1);

        checkTshirtRegistered();

        spinner=(Spinner)view.findViewById(R.id.spn_size);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.activity_list_item_1, getResources().getStringArray(R.array.spinner_names));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        Button submitButton=view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progressRL.getVisibility()==View.GONE){
                    progressRL.setVisibility(View.VISIBLE);
                }
                TshirtRegDetails tshirtRegDetails=new TshirtRegDetails();
                tshirtRegDetails.setSize(spinner.getSelectedItem().toString());
                ApiInterface apiInterface1=SecuredApiClient.getApiClient(myDatabase.getJwtToken()).create(ApiInterface.class);
                Call<String> call=apiInterface1.registerTshirt(myDatabase.getJwtToken(),tshirtRegDetails);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body()!=null){
                            if (response.isSuccessful()){
                                Toast.makeText(getContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                                if (progressRL.getVisibility()==View.VISIBLE){
                                    progressRL.setVisibility(View.GONE);
                                }
                                if (tShirtRegisteredRL.getVisibility()==View.GONE){
                                    tShirtRegisteredRL.setVisibility(View.VISIBLE);
                                    msg.setText("T-shirt registration successful");
                                    tshirtSizeTV.setText(spinner.getSelectedItem().toString());
                                }

                            }
                        }
                        Log.d("tshirt_reg",response.toString());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("tshirt_reg",t.getMessage());
                    }
                });
            }
        });

        return view;


    }

    public void checkTshirtRegistered(){
        ApiInterface apiInterface = SecuredApiClient.getApiClient(myDatabase.getJwtToken()).create(ApiInterface.class);
        Log.d("tshirt_reg",myDatabase.getJwtToken());
        Call<TshirtRegDetails> detailsCall=apiInterface.checkTshirtRegistered(myDatabase.getJwtToken());
        detailsCall.enqueue(new Callback<TshirtRegDetails>() {
            @Override
            public void onResponse(Call<TshirtRegDetails> call, Response<TshirtRegDetails> response) {
                if (progressRL.getVisibility()==View.VISIBLE){
                    progressRL.setVisibility(View.GONE);
                }
                if (response.body()!=null){
                    if (tShirtRegisteredRL.getVisibility()==View.GONE){
                        tShirtRegisteredRL.setVisibility(View.VISIBLE);
                    }
                    tshirtSizeTV.setText(response.body().getSize());
                    Log.d("tshirt_reg","size: "+response.body().getSize());
                }
                Log.d("tshirt_reg",response.toString());
            }

            @Override
            public void onFailure(Call<TshirtRegDetails> call, Throwable t) {
                Toast.makeText(getContext(),"Error! Please check your internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
