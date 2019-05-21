package sg.edu.rp.c346.p05quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FlightDetails extends AppCompatActivity {

    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        tvOut = findViewById(R.id.tvOut);

        Intent getIntent = getIntent();
        int getTripType = getIntent.getIntExtra("TicketType", -1);
        int getNoOfPax = getIntent.getIntExtra("Pax", 0);
        // 1 for One Way Trip, 2 for Round Trip //

        double price = 0;
        String ticketType = "";
        if (getTripType == 1) {
            price = 100 * getNoOfPax;
            ticketType = "One Way Trip";
        } else if (getTripType == 2) {
            price = 100 * getNoOfPax * 2;
            ticketType = "Round Trip";
        }

        String message = String.format("You have selected %s.\nYour air ticket costs $%.1f", ticketType, price);
        tvOut.setText(message);

    }
}
