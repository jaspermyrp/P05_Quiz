package sg.edu.rp.c346.p05quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnPlus, btnMinus, btnSubmit;
    CheckBox cbOneWayType, cbRoundTripType;
    TextView tvNoOfPax;
    int noOfPax = 0;
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnSubmit = findViewById(R.id.btnSubmit);
        cbOneWayType = findViewById(R.id.cbOneWay);
        cbRoundTripType = findViewById(R.id.cbRoundTrip);
        tvNoOfPax = findViewById(R.id.tvNoPaxSelected);


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noOfPax++;
                tvNoOfPax.setText(noOfPax + "");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noOfPax--;
                tvNoOfPax.setText(noOfPax + "");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitClicked();
            }
        });
    }

    public void onSubmitClicked() {
        String errorMessage = "";

        if (cbOneWayType.isChecked() && cbRoundTripType.isChecked()) {
            errorMessage += "Only 1 Ticket Type can be selected.\n";
        }

        if (!cbOneWayType.isChecked() && !cbRoundTripType.isChecked()) {
            errorMessage += "Please select at least 1 Ticket Type.\n";
        } else {
            // 1 for One Way Trip, 2 for Round Trip //
            if (cbOneWayType.isChecked()) {
                type = 1;
            } else {
                type = 2;
            }
        }

        if (noOfPax < 0) {
            errorMessage += "Number of pax cannot be negative.\n";
        } else if (noOfPax == 0) {
            errorMessage += "Number of pax cannot be zero.\n";
        }



        if (!errorMessage.isEmpty()) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(MainActivity.this, FlightDetails.class);
            int noOfPax = Integer.parseInt(tvNoOfPax.getText().toString());

            intent.putExtra("TicketType", type);
            intent.putExtra("Pax", noOfPax);
            startActivity(intent);
        }

    }
}
