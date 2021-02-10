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

public class SpectrumTwoPrices {
     
     ArrayList<Double>allPrices = new ArrayList<Double>();
     
     public SpectrumTwoPrices(String caseID){
          //String csvFile = "resources/" + caseID + "/" + caseID + "Prices.csv";
          BufferedReader br = null;
          String line = "";
          try{
               URL urlToDictionary = this.getClass().getResource("resources/" + caseID + "/" + caseID + "Prices.csv");
               InputStream is = urlToDictionary.openStream();
               br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
               //br = new BufferedReader(new FileReader(csvFile));
               while ((line = br.readLine()) != null) {
                    String[] prices = line.split(",");
                    for(int b = 0; b < 10; b++){
                         allPrices.add(Double.parseDouble(prices[b]));
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
     
     public double getPrice(int q, int i, boolean s){
          double test;
          i++;
          if(s)
               test = allPrices.get((i*10-10)+q+5);
          else
               test = allPrices.get((i*10-10)+q);
          return test;
     }  
}
