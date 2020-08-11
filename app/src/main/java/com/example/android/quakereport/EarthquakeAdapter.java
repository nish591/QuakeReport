package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

        public EarthquakeAdapter(Activity context, ArrayList<Earthquake> items) {
            // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
            // the second argument is used when the ArrayAdapter is populating a single TextView.
            // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
            // going to use this second argument, so it can be any value. Here, we used 0.
            super(context, 0, items);
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItemView = convertView;

            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }
            Earthquake currentEarthquake = getItem(position);
            TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

            // Get the appropriate background color based on the current earthquake magnitude
            int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMag());

            // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudeColor);
            DecimalFormat formatter = new DecimalFormat("0.0");
            String magnitude = formatter.format(currentEarthquake.getmMag());
            magnitudeView.setText(magnitude);
            String quake_location[];
            if(currentEarthquake.getmLocation().contains(" of "))
            {
                quake_location=currentEarthquake.getmLocation().split(" of ");
                quake_location[0]+=" of";
            }
            else
            {
                quake_location= new String[]{getContext().getString(R.string.near_the), currentEarthquake.getmLocation()};
            }
            TextView LocPrimeView = (TextView) listItemView.findViewById(R.id.primary_location);
            LocPrimeView.setText(quake_location[1]);
            TextView LocOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
            LocOffsetView.setText(quake_location[0]);
            // Create a new Date object from the time in milliseconds of the earthquake
            Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());

            // Find the TextView with view ID date
            TextView dateView = (TextView) listItemView.findViewById(R.id.date);
            // Format the date string (i.e. "Mar 3, 1984")
            String formattedDate = formatDate(dateObject);
            // Display the date of the current earthquake in that TextView
            dateView.setText(formattedDate);

            // Find the TextView with view ID time
            TextView timeView = (TextView) listItemView.findViewById(R.id.time);
            // Format the time string (i.e. "4:30PM")
            String formattedTime = formatTime(dateObject);
            // Display the time of the current earthquake in that TextView
            timeView.setText(formattedTime);
            return listItemView;
        }
        /**
         * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
         */
        private String formatDate(Date dateObject) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
            return dateFormat.format(dateObject);
        }

        /**
         * Return the formatted date string (i.e. "4:30 PM") from a Date object.
         */
        private String formatTime(Date dateObject) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
            return timeFormat.format(dateObject);
        }
        private int getMagnitudeColor(double magnitude){
            int magnitudeColorResourceId;
            int magnitudeFloor = (int) Math.floor(magnitude);
            switch (magnitudeFloor) {
                case 0:
                case 1:
                    magnitudeColorResourceId = R.color.magnitude1;
                    break;
                case 2:
                    magnitudeColorResourceId = R.color.magnitude2;
                    break;
                case 3:
                    magnitudeColorResourceId = R.color.magnitude3;
                    break;
                case 4:
                    magnitudeColorResourceId = R.color.magnitude4;
                    break;
                case 5:
                    magnitudeColorResourceId = R.color.magnitude5;
                    break;
                case 6:
                    magnitudeColorResourceId = R.color.magnitude6;
                    break;
                case 7:
                    magnitudeColorResourceId = R.color.magnitude7;
                    break;
                case 8:
                    magnitudeColorResourceId = R.color.magnitude8;
                    break;
                case 9:
                    magnitudeColorResourceId = R.color.magnitude9;
                    break;
                default:
                    magnitudeColorResourceId = R.color.magnitude10plus;
                    break;
            }
            return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }
}

