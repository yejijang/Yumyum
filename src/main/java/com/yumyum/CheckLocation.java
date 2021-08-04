package com.yumyum;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.yumyum.dto.ShopDTO;

public class CheckLocation {
	
	//Shoplist, ShopDAO에서 주소를 받아 위도, 경도를 리턴
	public ShopDTO getLocation(String address) {

		String apiURL = "http://api.vworld.kr/req/address";
        
        try {
              int responseCode = 0;
              URL url = new URL(apiURL);
              HttpURLConnection con = (HttpURLConnection)url.openConnection();
              con.setRequestMethod("POST");
 
              String keyword = address;
              
              String text_content =  URLEncoder.encode(keyword.toString(), "utf-8");
              //String text_content =  URLEncoder.encode(keyword.toString());
               
              // post request
              String postParams = "service=address";
                     postParams += "&request=getcoord";                     
                     postParams += "&version=2.0";
                     postParams += "&crs=EPSG:4326";
                     postParams += "&address="+text_content;                                    
                     postParams += "&refine=true";
                     postParams += "&simple=true";                  
                     postParams += "&format=json";
                     postParams += "&type=parcel";    
                     postParams += "&errorFormat=json";
                     postParams += "&key=BC55C468-5843-3138-AD9C-44A0BE3015EB";                    
 
              con.setDoOutput(true);
              DataOutputStream wr = new DataOutputStream(con.getOutputStream());
              wr.writeBytes(postParams);
              wr.flush();
              wr.close();
              responseCode = con.getResponseCode();
              BufferedReader br;
               
              if(responseCode==200) { // 정상 호출
                  br = new BufferedReader(new InputStreamReader(con.getInputStream()));
              }else{  // 에러 발생
                  br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
              }
 
              String inputLine;
              StringBuffer response = new StringBuffer();
 
              while ((inputLine = br.readLine()) != null) {
                  response.append(inputLine);
              }

              JSONParser parser = new JSONParser();
              
              JSONObject jobject1 = (JSONObject)parser.parse(response.toString());
              //System.out.println(jobject1.get("response"));
              
              JSONObject jobject2 = (JSONObject)parser.parse(jobject1.get("response").toString());
              //System.out.println(jobject2.get("result"));
              
              JSONObject jobject3 = (JSONObject)parser.parse(jobject2.get("result").toString());
              //System.out.println(jobject3.get("point"));
              
              JSONObject jobject4 = (JSONObject)parser.parse(jobject3.get("point").toString());
              //System.out.println(jobject4.get("x"));
              //System.out.println(jobject4.get("y"));

              ShopDTO location = new ShopDTO();
				
              location.setLon(jobject4.get("x").toString());
              location.setLat(jobject4.get("y").toString());
              
              br.close();
              con.disconnect();
              
              return location;
              
              
          } catch(NullPointerException e1) {
      	    	System.out.println("주소를 찾을 수 없습니다.");
          } catch(Exception e2) {   
        	  System.out.println("CheckLocation.getLocation()");
              e2.printStackTrace();
          }
		
		return null;
	}

	//ShopDAO에서 출발지와 도착지를 입력 받아 거리(km) 리턴
	public double getDistance(String lon, String lat, String lon2, String lat2) {
		
		double convert_lon = Double.parseDouble(lon);
		double convert_lat = Double.parseDouble(lat);
		double convert_lon2 = Double.parseDouble(lon2);
		double convert_lat2 = Double.parseDouble(lat2);
		
		
		double distanceKiloMeter = distance(convert_lat, convert_lon, convert_lat2, convert_lon2, "kilometer");
		
		return distanceKiloMeter;
	}
	
	
	/**
     * 두 지점간의 거리 계산
     *
     * @param lat1 지점 1 위도
     * @param lon1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lon2 지점 2 경도
     * @param unit 거리 표출단위
     * @return
     */
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
         
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
         
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
         
        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }
 
        return (dist);
    }
     
 
    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
     
    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
	

}
