package com.example.f;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private Button button;
    public EditText name;
    public static final String EXTRA_NAME = "";

    public EditText AGE;
    Double age;
    public static final String KEY_AGE = "";

    public EditText SALARY;
    Double salary;
//
    public EditText DEDUCTION_FROM_SALARY;
    Double deduction_from_salary;

    public EditText INCOME_FROM_INTREST;
    Double income_from_intrest;

    public EditText INCOME_FROM_OTHER_SOURCE;
    Double income_from_other_sources;

    public EditText INTREST_PAID_ON_LOANS;
    Double intrest_paid_on_loans;

    Double FINAL_TAX_NEW;
    Double FINAL_TAX_OLD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        name = findViewById(R.id.editTextTextPersonName);
        AGE = findViewById(R.id.editTextNumberDecimal);
        SALARY = findViewById(R.id.editTextNumberDecimal2);
        DEDUCTION_FROM_SALARY = findViewById(R.id.editTextNumberDecimal3);
        INCOME_FROM_INTREST = findViewById(R.id.editTextNumberDecimal4);
        INCOME_FROM_OTHER_SOURCE = findViewById(R.id.editTextNumberDecimal5);
        INTREST_PAID_ON_LOANS = findViewById(R.id.editTextNumberDecimal6);

        String name_string = name.toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().length() == 0 || SALARY.getText().length() == 0 ||
                        DEDUCTION_FROM_SALARY.getText().length() == 0 ||
                        name.getText().length() == 0 ||INCOME_FROM_INTREST.getText().length() == 0 ||INCOME_FROM_OTHER_SOURCE.getText().length() == 0 || INTREST_PAID_ON_LOANS.getText().length() == 0 ) {
                    name.setError("This field is required");
                } else {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    String name1 = name.getText().toString();

                    age = Double.parseDouble(AGE.getText().toString());
                salary = Double.parseDouble(SALARY.getText().toString());
                deduction_from_salary = Double.parseDouble(DEDUCTION_FROM_SALARY.getText().toString());
                income_from_intrest = Double.parseDouble(INCOME_FROM_INTREST.getText().toString());
                income_from_other_sources = Double.parseDouble(INCOME_FROM_INTREST.getText().toString());
                intrest_paid_on_loans = Double.parseDouble(INTREST_PAID_ON_LOANS.getText().toString());

                double salary_for_old =calculateTotalIncome(salary,deduction_from_salary,income_from_intrest,income_from_other_sources,intrest_paid_on_loans);
                    if (age <= 60) {
                        FINAL_TAX_NEW = new_regime_income_rate(salary);
                        FINAL_TAX_OLD = old_regime_income_rate_for_age_less_than_60(salary_for_old);

                    } else if (age > 60 && age < 80) {
                        FINAL_TAX_NEW= new_regime_income_rate(salary);
                        FINAL_TAX_OLD = old_regime_income_rate_for_age_60_to_80(salary_for_old);
                       ;
                    } else {
                        FINAL_TAX_NEW = new_regime_income_rate(salary);
                        FINAL_TAX_OLD = old_regime_income_rate_for_age_more_than_80(salary_for_old);

                    }
                         if(FINAL_TAX_OLD>=FINAL_TAX_NEW){
                             double dif=FINAL_TAX_OLD-FINAL_TAX_NEW;
                             intent.putExtra(EXTRA_NAME, name1 + "\nAge: " + age + "\nTax with New Regime:Rs. "+FINAL_TAX_NEW+"\nTax with Old Regime:Rs. "+FINAL_TAX_OLD+"\n\nYou can save upto "+"Rs. "+dif+" by applying to New Tax Regime");
                             startActivity(intent);
                         }else {
                             double dif=FINAL_TAX_NEW-FINAL_TAX_OLD;
                             intent.putExtra(EXTRA_NAME, name1 + "\nAge: " + age + "\nTax with New Regime:Rs.  "+FINAL_TAX_NEW+"\nTax with Old Regime:Rs. "+FINAL_TAX_OLD+"\n \nYou can save upto "+"Rs. "+dif+" by applying to Old Tax Regime");
                             startActivity(intent);
                         }


//                    intent.putExtra(EXTRA_NAME, name1 + "\nAGE:" + age + "\ntax with new regime"+FINAL_TAX_NEW+"\ntax with old regime"+FINAL_TAX_OLD+"\n Tou can save upto \n"+);
//                    startActivity(intent);
                }

            }


        });


    }


    //    //        private void show1() {
//////        final_textbox.setText("your taxable income is " + String.valueOf(a));
////            textView.setText("your taxable income by new system is : " +
////                    String.valueOf(FINAL_TAX_NEW) + "Rs\n and with old system its " +
////                    String.valueOf(FINAL_TAX_OLD));
////        }
////
////    }



    public static double calculateTotalIncome(double salary_income, double exemptions_deductions_from_salary,
                                              double interest_income, double other_income,
                                              double interest_loan) {

        double income;

        income = salary_income - exemptions_deductions_from_salary + (interest_income*0.9) + other_income- interest_loan-50000;

        return income;
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


