package com.example.f;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.textView);
//        textView.setText("hi");
        Intent intent=getIntent();
        String name=intent.getStringExtra(MainActivity.EXTRA_NAME);
        textView.setText("Name: " + name);

    }
    public static double new_regime_income_rate(double income) {

        double tax_new_regime = 0;

        /*
         * Please note that the tax rates in the New tax regime is the same for all
         * categories of Individuals, i.e Individuals & HUF upto 60 years of age, Senior
         * citizens above 60 years upto 80 years , and Super senior citizens above 80
         * years. Hence no increased basic exemption limit benefit will be available to
         * senior and super senior citizens in the New Tax regime.
         */

        // checking income slab

        // slab 1
        if (income >= 0 && income <= 250000) {
            tax_new_regime = 0.00 * income;
        }

        // slab 2
        else if (income > 250000 && income <= 500000) {
            tax_new_regime = 0.05 * (income - 250000);
        }

        // slab 3
        else if (income > 500000 && income <= 750000) {
            tax_new_regime = (0.05 * 250000) + (0.10 * (income - 500000));
        }

        // slab 4
        else if (income > 750000 && income <= 1000000) {
            tax_new_regime = (0.05 * 250000) + (0.10 * 250000) + (0.15 * (income - 750000));
        }

        // slab 5
        else if (income > 1000000 && income <= 1250000) {
            tax_new_regime = (0.05 * 250000) + (0.10 * 250000) + (0.15 * 250000) + (0.20 * (income - 1000000));
        }

        // slab 6
        else if (income > 1250000 && income <= 1500000) {
            tax_new_regime = (0.05 * 250000) + (0.10 * 250000) + (0.15 * 250000) + (0.20 * 250000)
                    + (0.25 * (income - 1250000));
        }

        // slab 7
        else {
            tax_new_regime = (0.05 * 250000) + (0.10 * 250000) + (0.15 * 250000) + (0.20 * 250000) + (0.25 * 250000)
                    + (0.30 * (income - 1500000));
        }

        return tax_new_regime;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // function to calculate income rate under old regime for age less than 60
    public static double old_regime_income_rate_for_age_less_than_60(double income) {

        double tax_old_regime = 0;

        // checking income slab

        // slab 1
        if (income >= 0 && income <= 250000) {
            tax_old_regime = 0.00 * income;
        }

        // slab 2
        else if (income > 250000 && income <= 500000) {
            tax_old_regime = 0.05 * income;
        }

        // slab 3
        else if (income > 500000 && income <= 1000000) {
            tax_old_regime = (0.05 * 250000) + (0.20 * (income - 500000));
        }

        // slab 4
        else {
            tax_old_regime = (0.05 * 250000) + (0.20 * 500000) + (0.30 * (income - 1000000));
        }

        return tax_old_regime;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // function to calculate income rate under old regime for ages between 60 and 80
    public static double old_regime_income_rate_for_age_60_to_80(double income) {

        double tax_old_regime = 0;

        // checking income slab

        // slab 1
        if (income >= 0 && income <= 300000) {
            tax_old_regime = 0.00 * income;
        }

        // slab 2
        else if (income > 300000 && income <= 500000) {
            tax_old_regime = 0.05 * income;
        }

        // slab 3
        else if (income > 500000 && income <= 1000000) {
            tax_old_regime = (0.05 * 200000) + (0.20 * (income - 500000));
        }

        // slab 4
        else {
            tax_old_regime = (0.05 * 200000) + (0.20 * 500000) + (0.30 * (income - 1000000));
        }

        return tax_old_regime;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // function to calculate income rate under old regime for age more than 80
    public static double old_regime_income_rate_for_age_more_than_80(double income) {

        double tax_old_regime = 0;

        // checking income slab

        // slab 1
        if (income >= 0 && income <= 500000) {
            tax_old_regime = 0.00 * income;
        }

        // slab 2
        else if (income > 500000 && income <= 1000000) {
            tax_old_regime = 0.20 * income;
        }

        // slab 3
        else {
            tax_old_regime = (0.20 * 500000) + (0.30 * (income - 1000000));
        }

        return tax_old_regime;
    }
}
