package com.example.staff_precyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.staff_precyapp.Adapter.UpComingEventsAdapter;
import com.example.staff_precyapp.Model.UpComingEventsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    ArrayList<UpComingEventsModel> list;
    UpComingEventsAdapter myAdapter;
    FirebaseAuth firebaseAuth;

    TextView name;
    ImageView logout_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name_TextView);
        logout_Btn = findViewById(R.id.logout_Btn);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext() ));

        myAdapter = new UpComingEventsAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(myAdapter);

        setUpFirebase();
        setUpLogOut();
    }

    private void setUpLogOut() {

        logout_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();

                Toast.makeText(getApplicationContext(), "Successfully Logged Out", Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);

            }
        });
    }

    private void setUpFirebase() {


        firebaseFirestore.collection("ApprovedReservationListData").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    list.clear();

                    for (QueryDocumentSnapshot document :value) {
                        if (document.exists()) {
                            list.add(new UpComingEventsModel(document.get("Name").toString(), document.get("ReservationDate").toString(), document.get("Venue").toString()));
                            myAdapter.notifyDataSetChanged();

                        }
                    }
                }

            }
        });

        firebaseFirestore.collection("StaffUsers"). document(firebaseAuth.getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if(documentSnapshot.exists()){
                                name.setText( documentSnapshot.getString("Name"));
                            } else{
                                Log.d("TAG", "no such document");
                            }
                        }
                    }
                });
    }
}