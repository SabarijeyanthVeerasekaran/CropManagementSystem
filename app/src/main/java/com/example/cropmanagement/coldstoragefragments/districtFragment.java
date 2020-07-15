package com.example.cropmanagement.coldstoragefragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cropmanagement.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class districtFragment extends Fragment {


    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    private ArrayList<ColdFire> list;
    private MyIAdapter myIAdapter;
    public districtFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_district, container, false);


        recyclerView=(RecyclerView) view.findViewById(R.id.cold_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> districts=new ArrayList<>();
        list=new ArrayList<ColdFire>();

        Bundle bundle=getArguments();
        String text=bundle.getString("districtname");

        databaseReference= FirebaseDatabase.getInstance().getReference("district").child(text);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    ColdFire coldFire=dataSnapshot1.getValue(ColdFire.class);
                    list.add(coldFire);

                }
                myIAdapter=new MyIAdapter(getActivity(),list);
                recyclerView.setAdapter(myIAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),""+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });






        return view;
    }

}
