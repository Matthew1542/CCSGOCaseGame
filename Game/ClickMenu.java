import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class ClickMenu {
     
     public static double caseP = 0.00;
     public static Random r = new Random();
     public static ArrayList<BufferedImage> caseImages = new ArrayList<BufferedImage>();
     
     public static void refreshVariables(){
          
     }
     
     SpriteSheet th;
     public ClickMenu(Game game){
          th = new SpriteSheet(Game.cases);
          for(int i = 1; i < 27; i++){
               caseImages.add(th.grabImage(i, 1, 100, 100));
          }
     }
     
     int caseGot;
     public void tick(){
          if(Game.menuOpened || Game.menuClosing){
               SideMenu.tick();
               refreshVariables();
          }
          else{
               refreshVariables();
          }
          if(caseP >= 100){
               int rNum = r.nextInt(10000000);
               ArrayList<Integer> casePercents = new ArrayList<Integer>();
               casePercents.addAll(Arrays.asList(288651, 399682, 399468, 367639, 378320,
                                                 380776, 314020, 399041, 399575, 399682,
                                                 399682, 399682, 391030, 381204, 399468,
                                                 399682, 399575, 399682, 399468, 397759,
                                                 399041, 327478, 398507, 392739, 399682, 388467));
               for(int i = 0; i < 26; i++)
               {
                    int tempTotal = 0;
                    for(int g = 0; g <= i; g++){
                         tempTotal += casePercents.get(g);
                    }
                    //System.out.println(tempTotal);                 
                    if(rNum < tempTotal){
                         Game.caseInventory.set(i, Game.caseInventory.get(i) + 1);
                         caseGot = i;
                         i = 100;
                         caseOpened = true;
                    }
               }
               caseP = 0;
          }
     }
     
     static ArrayList<FadeClick> moneyClick = new ArrayList<FadeClick>();
     public static void showMoney(int x, int y){
          moneyClick.add(new FadeClick(x, y, "$0.01"));
     }
     public static void showCase(int x, int  y){
          caseP = Math.round(caseP * 100.0) / 100.0; 
          moneyClick.add(new FadeClick(x, y, "1.00%"));
     }
     
     public static boolean caseOpened = false;
     
     public void render(Graphics g){
          if(Game.menuOpened){
               SideMenu.render(g);
          }
          Graphics2D g2d = (Graphics2D) g;
          g.drawImage(Game.background, 0 + Game.moveOver, 0, null);
          Color transBlack = new Color(0, 0, 0, 127);
          Font fnt0 = new Font("arial", Font.BOLD, 40);
          g.setFont(fnt0);
          g.setColor(Color.white);
          g2d.fillRect(605 + Game.moveOver, 15, 30, 5);
          g2d.fillRect(605 + Game.moveOver, 25, 30, 5);
          g2d.fillRect(605 + Game.moveOver, 35, 30, 5);
          Font fnt1 = new Font("ariel", Font.BOLD, 20);
          g.setFont(fnt1);
          g.drawString("" + Game.moneyString, 10 + Game.moveOver, 25);
          g.setFont(fnt0);
          
          g.drawString("Money", 50 + Game.moveOver, 100);
          g2d.drawRect(50 + Game.moveOver, 100, 250, 300);
          for(int i = 0; i < moneyClick.size(); i++)
          {
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
          g.setFont(fnt0);
          g.setColor(Color.white);
          
          g.drawString("Cases", 640 - 300 + Game.moveOver, 100);
          g2d.drawRect(340 + Game.moveOver, 100, 250, 300);
          g2d.drawRect(350 + Game.moveOver, 345, 230, 45);
          g.setColor(Color.green);
          g2d.fillRect(350 + Game.moveOver, 345, (int)(caseP * 2.3), 45);
          g.setColor(Color.white);
          g.drawString(caseP + "%", 425 + Game.moveOver, 385);
          
          if(caseOpened){
               g.setColor(transBlack);
               g2d.fillRect(0,0,1000,1000);
               g.drawImage(Game.caseBackground3, 50, 25, null);
               g.drawImage(caseImages.get(caseGot), 150, 90, 300, 300, null);
               g.setColor(Color.white);
               if(Game.caseNames.get(caseGot).contains(" > ")){
                    int t = Game.caseNames.get(caseGot).indexOf(" > ");
                    g.drawString(Game.caseNames.get(caseGot).substring(0, t) + " " + Game.caseNames.get(caseGot).substring(t+3, Game.caseNames.get(caseGot).length()) + " Case", 85, 85);
               }
               else{
                    g.drawString(Game.caseNames.get(caseGot) + " Case", 85, 85);
               }
          }
          if(Game.menuOpened){
               g.setColor(transBlack);
               g2d.fillRect(0 + Game.moveOver, 0, 650, 500);
          }
     }
}