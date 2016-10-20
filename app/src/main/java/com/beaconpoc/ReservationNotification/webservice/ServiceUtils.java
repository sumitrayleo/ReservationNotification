package com.beaconpoc.ReservationNotification.webservice;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ServiceUtils {

    /**
     * Post Login Service
     * @return
     */
    public String postLoginData() {

        String responseData="";
        boolean result = false;
        HttpClient hc = new DefaultHttpClient();
        String message;

        HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/booking-engine/api/booking/login");
        JSONObject object = new JSONObject();

        try {

            object.put("username", "admin");
            object.put("password", "test");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            message = object.toString();
            httpPost.setEntity(new StringEntity(message, "UTF8"));
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Booking-API-Key","1Q7VbsO93CPDMxTyzYp0ADnXG56gaRymAsp3vRiTcfw");
            httpPost.setHeader("appPlatform","android");

            HttpResponse resp = hc.execute(httpPost);

            if (resp != null) {
                if (resp.getStatusLine().getStatusCode() == 204)
                    result = true;
            }

            responseData= readResponse(resp);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return responseData;
    }


    /**
     * Get retrieveDeviceInformation Service
     * @return String
     */
    public static String retrieveDeviceInformation() {

        String responseData="";
        boolean result = false;
        HttpClient hc = new DefaultHttpClient();
        HttpGet httpGet= new HttpGet("http://10.0.2.2:8080/booking-engine/api/booking/device/info?appName=EHI");

        try {

            httpGet.setHeader("Content-type", "application/json");
            httpGet.setHeader("Booking-API-Key","1Q7VbsO93CPDMxTyzYp0ADnXG56gaRymAsp3vRiTcfw");
            httpGet.setHeader("appPlatform","android");

            HttpResponse resp = hc.execute(httpGet);

            if (resp != null) {
                if (resp.getStatusLine().getStatusCode() == 204)
                    result = true;
            }

            System.out.println("Status line retrieveDeviceInformation:  " + resp.getStatusLine().getStatusCode());
            responseData= readResponse(resp);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return responseData;
    }

    /**
     * Post Push Notification Service
     * @return String
     */

    public static String postPushNotificationData() {

        String responseData="";
        boolean result = false;
        HttpClient hc = new DefaultHttpClient();
        String message;

        HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/booking-engine/api/booking/notify/info");
        JSONObject object = new JSONObject();

        try {

            object.put("memberId", "123456");
            object.put("deviceId", "1234");
            object.put("latitude", "65.7");
            object.put("longitude", "87.6");
            object.put("identifier", "Car_Rental");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            message = object.toString();
            httpPost.setEntity(new StringEntity(message, "UTF8"));
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Booking-API-Key","1Q7VbsO93CPDMxTyzYp0ADnXG56gaRymAsp3vRiTcfw");
            httpPost.setHeader("appPlatform","android");

            HttpResponse resp = hc.execute(httpPost);

            if (resp != null) {
                if (resp.getStatusLine().getStatusCode() == 204)
                    result = true;
            }

            System.out.println("Status line postPushNotificationData:  " + resp.getStatusLine().getStatusCode());
            responseData= readResponse(resp);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return responseData;
    }



    /**
     * Read Response Data
     * @param res
     * @return
     */

    public static String readResponse(HttpResponse res) {

        InputStream is=null;
        String returnText="";

        try {

            is=res.getEntity().getContent();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
            String line="";
            StringBuffer sb=new StringBuffer();

            while ((line=bufferedReader.readLine())!=null)
            {
                sb.append(line);
            }

            returnText=sb.toString();

        } catch (Exception e){

            e.printStackTrace();
        }

        return returnText;

    }


}
