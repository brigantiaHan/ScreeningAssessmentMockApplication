package com.example.mingxiu.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private TaboneFragment searchFragment;
    private TabtwoFragment cartFragment;
    private TabthreeFragment dealsFragment;
    public static SearchFragment2 searchFragment2;
    public static SearchFragment3 searchFragment3;
    private Bundle temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        searchFragment = new TaboneFragment();
        cartFragment = new TabtwoFragment();
        dealsFragment = new TabthreeFragment();
        bottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        searchFragment2 = new SearchFragment2();
        searchFragment3 = new SearchFragment3();
        bottomNavigation.inflateMenu(R.menu.bottom_menu);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, searchFragment).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_search:
                        fragment = searchFragment;
                        break;
                    case R.id.action_cart:
                        fragment = cartFragment;
                        break;
                    case R.id.action_hot_deals:
                        fragment = dealsFragment;
                        break;

                }

               // Toast.makeText(MainActivity.this,id + "",Toast.LENGTH_SHORT).show();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }


        });
    }

    @Override
    public void onBackPressed() {


                if(TaboneFragment.getCode() == 2){

                    int code = TaboneFragment.getCode();
                    code--;
                    TaboneFragment.setCode(code);
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_container, searchFragment).commit();

                } else if(TaboneFragment.getCode() == 3){
                    int code = TaboneFragment.getCode();
                    code--;
                    TaboneFragment.setCode(code);
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_container, searchFragment2).commit();

                }



            else {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }



        }

}
