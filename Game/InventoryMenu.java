import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class InventoryMenu {
     
     // 640 x 480
     
     public static int dim = 80;
     public static int gap = dim + 15;
     public static boolean click = false;
     public static int gunPlace = -100;
     public static String priceString;
     public static int page = 0;
     public static ArrayList<BufferedImage> imageOne = new ArrayList<BufferedImage>();
     static boolean loaded = true;
     public InventoryMenu(Game game){
     }
     
     public static void changePage(int temp){
          if(page + temp < 0 || page + temp >= Game.inventory.size()){
          }
          else{
               page = page + temp;
               loaded();
          }
     }
     
     public static void clicked(int x, int y){
          if((x+1 + 5 * y) + page <= Game.inventory.size()){
               gunPlace = (x+1 + 5 * y) + page - 1;
               double gunPrice = Game.inventory.get(gunPlace).getPrice();
               double temp = gunPrice*10;
               temp = (int)temp;
               temp = temp/10;
               if(gunPrice < .1)
                    priceString = "$" + gunPrice;
               else if(gunPrice - (int)gunPrice == .0)
                    priceString = "$" + gunPrice + "0";
               else if(gunPrice - temp == 0)
                    priceString = "$" + gunPrice + "0";
               else
                    priceString = "$" + gunPrice;
               click = true;
          }
          else
               System.out.println("No Gun Located");
     }
     public static ArrayList<FadeClick> moneyClick = new ArrayList<FadeClick>();
     public static ArrayList<FadeClick> buyClick = new ArrayList<FadeClick>();
     
     public static void sellGun(int mx, int my){
          click = false;
          if(gunPlace == -100)
               gunPlace = Game.inventory.size()-1;
          Game.money += Game.inventory.get(gunPlace).getPrice();
          moneyClick.add(new FadeClick(mx, my, "$" + Game.inventory.get(gunPlace).getPrice()));
          if(Game.inventory.size() == 1){
               Game.inventory.clear();
          }
          else{
               for(int i = gunPlace; i < Game.inventory.size(); i++){
                    try{
                         Game.inventory.set(i, Game.inventory.get(i+1));
                    }catch(IndexOutOfBoundsException e){
                         Game.inventory.remove(i);
                    }
               }
          }
          Game.saveInventory();
          loaded();
          gunPlace = -100;
     }
     
     public static void buyItem(int mx, int my, double amount){
          buyClick.add(new FadeClick(mx, my, "$" + amount));
     }
     
     public static void loaded(){
          imageOne.clear();
          for(int i = page;i < page + 20 && i < Game.inventory.size(); i++){
               imageOne.add(Game.inventory.get(i).getImageOne());
          }
     }
     
     public static void closeGun(){
          click = false;
     }
     
     public void tick(){
          if(loaded){
               loaded();
               loaded = false;
          }
          if(Game.menuOpened || Game.menuClosing){
               SideMenu.tick();
          }
     }
     
     BufferedImage imageTwo = null;
     boolean first = true;
     public void render(Graphics g){
          if(Game.menuOpened){
               SideMenu.render(g);
          }
          Graphics2D g2d = (Graphics2D) g;
          g.drawImage(Game.background, 0 + Game.moveOver, 0, null);
          Font fnt0 = new Font("arial", Font.BOLD, 40);
          Color transBlack = new Color(0, 0, 0, 127);
          g.setFont(fnt0);
          g.setColor(Color.white);
          g.drawString("Inventory", 225 + Game.moveOver, 40);
          g2d.fillRect(605 + Game.moveOver, 15, 30, 5);
          g2d.fillRect(605 + Game.moveOver, 25, 30, 5);
          g2d.fillRect(605 + Game.moveOver, 35, 30, 5);
          Font fnt1 = new Font("ariel", Font.BOLD, 20);
          g.setFont(fnt1);
          g.drawString("" + Game.moneyString, 10 + Game.moveOver, 25);
          g.setColor(transBlack);
          g.setColor(Color.white);
          int dim = 80; 
          int gap = dim + 15;  
          for(int i = 0; i < 5; i++){
               for(int h = 0; h < 4; h++){
                    g2d.drawRect(90 + gap * i + Game.moveOver, 55 + gap * h, dim, dim);
                    g.setColor(transBlack);
                    g2d.fillRect(91 + gap * i + Game.moveOver, 56 + gap * h, dim - 1, dim - 1);
                    g.setColor(Color.white);
               }
          }
          
          
          
          int place = 0; //+ page;
          for(int i = 0; i < 4; i++){
               for(int h = 0; h < 5; h++){
                    try{
                         g.drawImage(imageOne.get(place), 90 + h * 95 + Game.moveOver, 55 + i * 95, 81, 81, null);
                    }catch(IndexOutOfBoundsException e){
                         i = 5;
                    }
                    place++;
               }
          }
          for(int i = 0;i < 6;i++){
               g2d.fillRect(75 + Game.moveOver - 5*i, 180 + 10*i, 5, 120 - 20*i);
               g2d.fillRect(560 + Game.moveOver + 5*i, 180 + 10*i, 5, 120 - 20*i);                   
          }   
          Font fnt8 = new Font("ariel", Font.BOLD, 16);
          Rectangle transBack = new Rectangle(0, 0, 650, 478);
          g.setFont(fnt8);
          try{
               if(click){
                    if(first){
                         imageTwo = Game.inventory.get(gunPlace).getImageTwo();
                         first = false;
                    }
                    g.setColor(transBlack);
                    g2d.fill(transBack);
                    g.drawImage(Game.caseOpenedBackground, 50, 25, null);
                    g.drawImage(imageTwo, 75, 50, null);
                    g.setColor(Color.white);
                    if(Game.inventory.get(gunPlace).getStatTrak()){
                         g.drawString("StatTrak", 90, 330);
                         g.drawString(priceString, 90, 310);
                    }
                    else
                         g.drawString(priceString, 90, 330);
                    g.drawString(Game.inventory.get(gunPlace).getQuality() + "", 90, 350);
               }
               else{
                    try{
                         imageTwo = null;
                         first = true;
                    }catch(IndexOutOfBoundsException e){}
               }
          }catch(IndexOutOfBoundsException e){}
          
          for(int i = 0; i < moneyClick.size(); i++){
               int tempTrans = moneyClick.get(i).getTrans();
               if(tempTrans <= 0){
                    if(i == moneyClick.size() - 1)
                         moneyClick.clear();
               }
               else{
                    Color greenT = new Color(0, 255, 0, tempTrans);
                    g.setColor(greenT);
                    g.setFont(fnt1);
                    g.drawString(moneyClick.get(i).getSymbol(), moneyClick.get(i).getX(), moneyClick.get(i).getY()); 
               }
          }
          if(Game.menuOpened){
               g.setColor(transBlack);
               g2d.fillRect(0 + Game.moveOver, 0, 650, 500);
          }
     }     
}