package com.example.pothole.Contractor.account_C;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pothole.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    FirebaseAuth mAuth;
    String email;
    int points;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account_c, container, false);
       // final TextView textView = root.findViewById(R.id.text_account);

        final EditText mail=(EditText)root.findViewById(R.id.emailid1);

        final EditText fname=(EditText)root.findViewById(R.id.fname);


        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("contractor").child(uid);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                email=dataSnapshot.child("email").getValue(String.class);
                String name=dataSnapshot.child("full name").getValue(String.class);
                mail.setText(email);

                fname.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }
}