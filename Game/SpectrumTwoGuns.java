import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;
import java.net.*;

public class SpectrumTwoGuns {
     
     ArrayList<String>allPrices = new ArrayList<String>();
     Random r = new Random();
     
     public SpectrumTwoGuns(String caseID){
          //String csvFile = "resources/" + caseID + "/" + caseID + "Guns.csv";
          BufferedReader br = null;
          String line = "";
          try {
               URL urlToDictionary = this.getClass().getResource("resources/" + caseID + "/" + caseID + "Guns.csv");
               InputStream is = urlToDictionary.openStream();
               br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
               //br = new BufferedReader(new FileReader(csvFile));
               while ((line = br.readLine()) != null) {
                    String[] prices = line.split(",");
                    for(int b = 0; b < 3; b++){
                         allPrices.add(prices[b]);
                    }
                    //System.out.println(allPrices);
               }
          } catch (FileNotFoundException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          } finally {
               if (br != null) {
                    try {
                         br.close();
                    } catch (IOException e) {
                         e.printStackTrace();
                    }
               }
          }
     }
     
     public String getInfo(int n){
          String item = allPrices.get(n*3); 
          int max = Integer.parseInt(allPrices.get(n*3+1));
          int min = Integer.parseInt(allPrices.get(n*3+2));
          double floatV = (double)(r.nextInt(max-min)+min)/100;
          String qualities = "";
          if(floatV >= 0.00 && floatV <= 0.07){
               qualities = "Factory New~0";
          }
          if(floatV > 0.07 && floatV <= 0.15){
               qualities = "Minimal Wear~1";
          }
          if(floatV > 0.15 && floatV <= 0.38){
               qualities = "Field Tested~2";
          }
          if(floatV > 0.38 && floatV <= 0.45){
               qualities = "Well Worn~3";
          }
          if(floatV > 0.45 && floatV <= 1.00){
               qualities = "Battle Scared~4";
          } 
          int temp = 0;
          int number = r.nextInt(10000)/100;
          if(number <= 10)
               temp = 1;
          return item+"~"+qualities+"~"+temp;
     }
}
