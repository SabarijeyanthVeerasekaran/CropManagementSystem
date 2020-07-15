package com.example.cropmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private long backPressedTime;
    private TextView header_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        toolbar=findViewById(R.id.toolbar);
        header_name=(TextView)findViewById(R.id.header_name);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerLayout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        navigationView=findViewById(R.id.navigatonView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);


        navigationView.setNavigationItemSelectedListener(this);

/*


        final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                //header_name.setText("Welcome Mr/Mrs."+userProfile.getName());
                header_name.setText("Welcome Mr/Mrs."+userProfile.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

 */
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerFragment,new MainFragment());
        fragmentTransaction.commit();


    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);

        if(menuItem.getItemId()==R.id.home)
        {
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerFragment,new MainFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.gallery)
        {
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerFragment,new GalleryFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.profile)
        {
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerFragment,new ProfileFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        return true;
    }


}

