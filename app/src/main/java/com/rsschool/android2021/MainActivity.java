package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.GenerationNumber, SecondFragment.BackToFirst {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min,max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment,"1");
        transaction.commit();
    }


    @Override
    public void generate(int min, int max) {
        openSecondFragment(min,max);
    }

    @Override
    public void backToFirstFragment(int previousNumber) {
        openFirstFragment(previousNumber);
    }

    @Override
    public void onBackPressed() {
        SecondFragment secFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag("1");
        if(secFragment.isVisible()){
            openFirstFragment(secFragment.getPreviousNumber());
        }
        else {
            super.onBackPressed();
        }
    }
}
