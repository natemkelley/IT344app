package kelley.nate.it344weather;


import android.icu.text.DateFormat;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadTask extends AsyncTask<String,Void,String> {

    String result;
    @Override
    protected String doInBackground(String... urls) {
        result = "";
        URL link;
        HttpURLConnection myconnection = null;


        //make a remote api connectino to weather service
        try {
            link = new URL(urls[0]);
            myconnection = (HttpURLConnection)link.openConnection();
            InputStream in = myconnection.getInputStream();
            InputStreamReader myStreamReader = new InputStreamReader(in);
            int data = myStreamReader.read();
            while(data!= -1){
                char current = (char)data;
                result+= current;
                data = myStreamReader.read();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            //create json object from remote api call data
            JSONObject myObject = new JSONObject(result);
            JSONObject main = new JSONObject(myObject.getString("main"));
            Double datTemperature = Double.parseDouble(main.getString("temp"));

            //convert to farenhieght
            int temperature = (int)(datTemperature*1.8-459.67);

            String placeName = myObject.getString("name");

            //set standard text
            MainActivity.place.setText(placeName);
            MainActivity.temperature.setText(String.valueOf(temperature));

            //get current time
            int currentDateTimeString = DateFormat.getDateTimeInstance().format(new java.util.());
            String timeofday = "";
            String finaltimeandweather = "";
            String finalactivy = "";


            if (temperature <= 120){ finaltimeandweather =" super hot";}
            else if(temperature <= 85){finaltimeandweather ="very hot";}
            else if(temperature <= 75){finaltimeandweather ="warm";}
            else if(temperature <= 55){finaltimeandweather ="nice temperature";}
            else if(temperature <= 35){finaltimeandweather ="cold";}
            else if(temperature <= 25){finaltimeandweather ="dang cold";}

            //if statement to create string
            if (currentDateTimeString <= 12){
                timeofday = "Morning";
                if (temperature <= 120){ finalactivy ="Go eat ice cream!";}
                else if(temperature <= 85){finalactivy ="Go sun tanning!";}
                else if(temperature <= 75){finalactivy ="Go to the park!";}
                else if(temperature <= 55){finalactivy ="Ride a bike!";}
                else if(temperature <= 35){finalactivy ="Drink hot cocoa!";}
                else if(temperature <= 25){finalactivy ="Just stay inside!";}
            }
            else if (currentDateTimeString <= 16){
                timeofday = "Afternoon";
                if (temperature <= 120){ finalactivy ="Go to the waterpark!";}
                else if(temperature <= 85){finalactivy ="Go canoeing!";}
                else if(temperature <= 75){finalactivy ="Go fishing";}
                else if(temperature <= 55){finalactivy ="Skip stones in the lake";}
                else if(temperature <= 35){finalactivy ="Go shopping";}
                else if(temperature <= 25){finalactivy ="Just stay inside!";}
            }
            else if (currentDateTimeString <= 19){
                timeofday = "Evening";
                if (temperature <= 120){ finalactivy ="Stay inside. Too hot!";}
                else if(temperature <= 85){finalactivy ="Evening picnic!";}
                else if(temperature <= 75){finalactivy ="Go to the drive in movies!";}
                else if(temperature <= 55){finalactivy ="Coat shopping!";}
                else if(temperature <= 35){finalactivy ="Try to see your breath!";}
                else if(temperature <= 25){finalactivy ="Snuggle!";}
            }
            else if (currentDateTimeString <= 24){
                timeofday = "Night time";
                timeofday = "Evening";
                if (temperature <= 120){ finalactivy ="Night time water balloon fight";}
                else if(temperature <= 85){finalactivy ="Night time frisbee";}
                else if(temperature <= 75){finalactivy ="Star gazing";}
                else if(temperature <= 55){finalactivy ="Dinner inside!";}
                else if(temperature <= 35){finalactivy ="Ice Skating!";}
                else if(temperature <= 25){finalactivy ="Movie and popcorn!";}
            }

            //set final string and change text
            finaltimeandweather = timeofday + "" + finaltimeandweather;
            MainActivity.timeandweather.setText(finaltimeandweather);
            MainActivity.activiy.setText(finalactivy);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

