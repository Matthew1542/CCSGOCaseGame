import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class MouseInput implements MouseListener{
     
     @Override
     public void mouseClicked(MouseEvent e){
          
     }
     
     @Override
     public void mouseEntered(MouseEvent e){
          
     }
     
     @Override
     public void mouseExited(MouseEvent e){
          
     }
     
     @Override
     public void mousePressed(MouseEvent e){
          int mx = e.getX();
          int my = e.getY();
          
          if(Game.menuOpened){
               if(mx >= 0 && mx <= 640 - 195){
                    Game.menuOpened = false;
                    if(Game.moveOver < 0){
                         Game.menuClosing = true;
                    }
               }
               
               if(mx >= 460 && mx <= 640 && my >= 50 && my <= 100){
                    Game.State = Game.State.CLICK;
                    Game.menuOpened = false;
                    if(Game.moveOver < 0){
                         Game.menuClosing = true;
                    }
               }
               
               if(mx >= 460 && mx <= 640 && my >= 110 && my <= 160){
                    Game.State = Game.State.UPGRADE;
                    Game.menuOpened = false;
                    if(Game.moveOver < 0){
                         Game.menuClosing = true;
                    }
               }
               
               if(mx >= 460 && mx <= 640 && my >= 170 && my <= 220){
                    Game.State = Game.State.MENU;
                    Game.menuOpened = false;
                    if(Game.moveOver < 0){
                         Game.menuClosing = true;
                    }
               }
               
               if(mx >= 460 && mx <= 640 && my >= 230 && my <= 280){
                    Game.State = Game.State.INVENTORY;
                    Game.menuOpened = false;
                    if(Game.moveOver < 0){
                         Game.menuClosing = true;
                    }
               }
               if(mx >= 460 && mx <= 640 && my >= 290 && my <= 340){
                    Game.State = Game.State.STATS;
                    Game.menuOpened = false;
                    if(Game.moveOver < 0){
                         Game.menuClosing = true;
                    }
               }
               
               if(mx >= 460 && mx <= 640 && my >= 350 && my <= 400){
                    Game.State = Game.State.OPTION;
                    Game.menuOpened = false;
                    if(Game.moveOver < 0){
                         Game.menuClosing = true;
                    }
                    //Game.clearSave();
               }
               
               if(mx >= 460 && mx <= 640 && my >= 410 && my <= 460){
                    Game.saveInventory();
                    System.exit(1);
               }   
          }
          
          else{
               if(Game.State == Game.STATE.MENU){
                    
                    if(mx >= 605 && mx <= 635 && my >= 15 && my <= 40){
                         Game.menuOpened = true; 
                    }
                    
                    if((mx >= 75 && mx <= 80 && my >= 190 && my <= 310) 
                            || (mx >= 70 && mx <= 75 && my >= 200 && my <= 300) 
                            || (mx >= 65 && mx <= 70 && my >= 210 && my <= 290) 
                            || (mx >= 60 && mx <= 65 && my >= 220 && my <= 280) 
                            || (mx >= 55 && mx <= 60 && my >= 230 && my <= 270) 
                            || (mx >= 50 && mx <= 55 && my >= 240 && my <= 260)){
                         // System.out.println("Back");
                         Menu.changePage(-4);
                    }
                    if((mx >= 560 && mx <= 565 && my >= 190 && my <= 310) 
                            || (mx >= 565 && mx <= 570 && my >= 200 && my <= 300) 
                            || (mx >= 570 && mx <= 575 && my >= 210 && my <= 290) 
                            || (mx >= 575 && mx <= 580 && my >= 220 && my <= 280) 
                            || (mx >= 580 && mx <= 585 && my >= 230 && my <= 270) 
                            || (mx >= 585 && mx <= 590 && my >= 240 && my <= 260)){
                         //  System.out.println("Next");
                         Menu.changePage(4);
                    }
                    
                    try{
                         if(mx >= 276 && mx <= 290 && my >= 126 && my <= 144){
                              if(Game.caseInventory.get(Menu.page) > 0){
                                   double temp = Game.caseMap.get(Game.ids.get(Menu.page)).getcasePrice();
                                   //System.out.println(temp);
                                   Game.money += temp;
                                   Game.caseInventory.set(Menu.page, Game.caseInventory.get(Menu.page) - 1);
                              }
                         }
                         else if(mx >= Game.WIDTH - 30 - 120 && mx <= Game.WIDTH - 30 && my >= 125 && my <= 225){
                              if(!(Game.ids.get(Menu.page).equals(null))){
                                   Game.State = Game.State.SPECTRUM2;
                                   Game.setCase(Game.ids.get(Menu.page));
                              }
                              else
                                   System.out.println("/////////////////////");                            
                         }
                         if(mx >= 456 && mx <= 470 && my >= 126 && my <= 144){
                              if(Game.caseInventory.get(Menu.page+1) > 0){
                                   double temp = Game.caseMap.get(Game.ids.get(Menu.page+1)).getcasePrice();
                                   //System.out.println(temp);
                                   Game.money += temp;
                                   Game.caseInventory.set(Menu.page+1, Game.caseInventory.get(Menu.page+1) - 1);
                              }
                         }
                         else if(mx >= Game.WIDTH + 30 && mx <= Game.WIDTH + 30 + 120){
                              if(my >= 125 && my <= 225){
                                   if(!(Game.ids.get(Menu.page+1).equals("bravo"))){
                                        Game.State = Game.State.SPECTRUM2;
                                        Game.setCase(Game.ids.get(Menu.page+1));
                                   }
                                   else
                                        System.out.println("/////////////////////");
                              }
                         }
                         if(mx >= 276 && mx <= 290 && my >= 276 && my <= 294){
                              if(Game.caseInventory.get(Menu.page+2) > 0){
                                   double temp = Game.caseMap.get(Game.ids.get(Menu.page+2)).getcasePrice();
                                   //System.out.println(temp);
                                   Game.money += temp;
                                   Game.caseInventory.set(Menu.page+2, Game.caseInventory.get(Menu.page+2) - 1);
                              }
                         }
                         else if(mx >= Game.WIDTH - 30 - 120 && mx <= Game.WIDTH - 30){
                              if(my >= 275 && my <= 375){
                                   if(!(Game.ids.get(Menu.page+2).equals("bravo"))){
                                        //System.out.println(Game.ids.get(Menu.page+2));
                                        Game.State = Game.State.SPECTRUM2;
                                        Game.setCase(Game.ids.get(Menu.page+2));
                                   }
                                   else
                                        System.out.println("/////////////////////");
                              }
                         }
                         if(mx >= 456 && mx <= 470 && my >= 276 && my <= 294){
                              if(Game.caseInventory.get(Menu.page+3) > 0){
                                   double temp = Game.caseMap.get(Game.ids.get(Menu.page+3)).getcasePrice();
                                   System.out.println(temp);
                                   Game.money += temp;
                                   Game.caseInventory.set(Menu.page+3, Game.caseInventory.get(Menu.page+3) - 1);
                              }
                         }
                         else if(mx >= Game.WIDTH + 30 && mx <= Game.WIDTH + 30 + 120){
                              if(my >= 275 && my <= 375){
                                   if(!(Game.ids.get(Menu.page+3).equals("bravo"))){
                                        //System.out.println(Game.ids.get(Menu.page+3));
                                        Game.State = Game.State.SPECTRUM2;
                                        Game.setCase(Game.ids.get(Menu.page+3));
                                   }
                                   else
                                        System.out.println("/////////////////////");
                              }
                         }
                    }catch(IndexOutOfBoundsException f){}
                    
               }
               
               else if(Game.State == Game.STATE.INVENTORY){
                    int x = 0;
                    int y = 0;
                    if(!InventoryMenu.click){
                         
                         if(mx >= 605 && mx <= 635 && my >= 15 && my <= 40){
                              //System.out.println("MENU");
                              Game.menuOpened = true; 
                         }
                         
                         if((mx >= 75 && mx <= 80 && my >= 190 && my <= 310) 
                                 || (mx >= 70 && mx <= 75 && my >= 200 && my <= 300) 
                                 || (mx >= 65 && mx <= 70 && my >= 210 && my <= 290) 
                                 || (mx >= 60 && mx <= 65 && my >= 220 && my <= 280) 
                                 || (mx >= 55 && mx <= 60 && my >= 230 && my <= 270) 
                                 || (mx >= 50 && mx <= 55 && my >= 240 && my <= 260)){
                              // System.out.println("Back");
                              InventoryMenu.changePage(-20);
                              //InventoryMenu.loaded();
                         }
                         if((mx >= 560 && mx <= 565 && my >= 190 && my <= 310) 
                                 || (mx >= 565 && mx <= 570 && my >= 200 && my <= 300) 
                                 || (mx >= 570 && mx <= 575 && my >= 210 && my <= 290) 
                                 || (mx >= 575 && mx <= 580 && my >= 220 && my <= 280) 
                                 || (mx >= 580 && mx <= 585 && my >= 230 && my <= 270) 
                                 || (mx >= 585 && mx <= 590 && my >= 240 && my <= 260)){
                              //  System.out.println("Next");
                              InventoryMenu.changePage(20);
                              //InventoryMenu.loaded();
                         }
                         for(int i = 0; i < 5; i++){
                              for(int h = 0; h < 4; h++){
                                   if((mx >= 90 + InventoryMenu.gap * i && mx <= 90 + InventoryMenu.gap * i + InventoryMenu.dim && my >= 55 + InventoryMenu.gap * h && my <= 55 + InventoryMenu.gap * h + InventoryMenu.dim)){
                                        //System.out.println(i + "   " + h);
                                        x = mx;
                                        y = my;
                                        InventoryMenu.clicked(i, h);
                                   }
                              }
                         }
                    }
                    
                    if(InventoryMenu.click){
                         if(mx == x && my == y){}
                         else if(mx >= 95 && mx <= 95 + 325){
                              if(my >=380 && my <= 380 + 60){
                                   InventoryMenu.closeGun();
                              }
                         }
                         //System.out.println(mx);
                         //System.out.println(my);
                         else if(mx >= 475 && mx <= 475 + 85){
                              if(my >=380 && my <= 380 + 60){
                                   //System.out.println(mx);
                                   InventoryMenu.sellGun(mx, my);
                              }
                         }  
                    }
               }
               
               else if(Game.State == Game.STATE.CLICK){
                    if(ClickMenu.caseOpened){
                         if(mx >= 162 && mx <= 488 && my >= 380 && my <= 442){
                              ClickMenu.caseOpened = false;
                         }
                         //System.out.println("mx: " + mx + "\nmy: " + my);
                    }
                    else{
                         if(mx >= 605 && mx <= 635 && my >= 15 && my <= 40){
                              Game.menuOpened = true; 
                         }
                         //CLICK ON MONEY
                         if(mx >= 50 && mx <= 300 && my >= 100 && my <= 400){
                              Game.money += .01;
                              ClickMenu.showMoney(mx, my);
                         }
                         
                         if(mx >= 340 && mx <= 590 && my >= 100 && my <= 400){
                              ClickMenu.caseP += 1.00;
                              ClickMenu.showCase(mx, my);
                         }
                    }
               }
               
               else if(Game.State == Game.STATE.UPGRADE){
                    if(mx >= 605 && mx <= 635 && my >= 15 && my <= 40){
                         Game.menuOpened = true; 
                    }
                    
                    if(mx >= 535 && mx <= 550 && my >= 55 + UpgradeMenu.moveValue && my <= 55 + UpgradeMenu.moveValue + UpgradeMenu.sliderHeight){
                         MouseMotion.sliderClicked = true;
                         System.out.println("true");
                    }
                    
               }
               
               else if(Game.State == Game.STATE.STATS){
                    if(mx >= 605 && mx <= 635 && my >= 15 && my <= 40){
                         Game.menuOpened = true; 
                    }
               }
               
               else if(Game.State == Game.STATE.OPTION){
                    if(mx >= 605 && mx <= 635 && my >= 15 && my <= 40){
                         Game.menuOpened = true; 
                    }
               }
               
               
               else if(Game.State == Game.STATE.SPECTRUM2){
                    if(mx >= Game.WIDTH / 2 + 5 && mx <= Game.WIDTH / 2 + 325){
                         if(my >= Game.HEIGHT*2 - 60 && my <= Game.HEIGHT*2 - 10){
                              if(!SpectrumTwo.getOpening() && !SpectrumTwo.getOpened()){
                                   Game.openCase(mx, my);
                              }
                         }
                    }
                    if(mx >= Game.WIDTH*2 - 110 && mx <= Game.WIDTH*2 - 10){
                         if(my >= Game.HEIGHT*2 - 60 && my <= Game.HEIGHT*2 + 40){
                              if(!SpectrumTwo.getOpened() && !SpectrumTwo.getOpening())
                                   Game.State = Game.State.MENU;
                         }
                    }
                    
                    /*if(InventoryMenu.click){
                     if(mx >= 95 && mx <= 95 + 325){
                     if(my >=380 && my <= 380 + 60){
                     InventoryMenu.closeGun();
                     }
                     }
                     //System.out.println(mx);
                     //System.out.println(my);
                     if(mx >= 475 && mx <= 475 + 85){
                     if(my >=380 && my <= 380 + 60){
                     //System.out.println(mx);
                     InventoryMenu.sellGun(mx, my);
                     }
                     }    
                     }*/
                    
                    
                    if(mx >= 95 && mx <= 95 + 325){
                         if(my >=380 && my <= 380 + 60){
                              if(SpectrumTwo.getOpened() && !SpectrumTwo.getOpening()){
                                   //SpectrumTwo.setAddMoney(true);
                                   SpectrumTwo.setOpened(false);
                              }
                         }
                    }
                    
                    if(mx >= 475 && mx <= 475 + 85){
                         if(my >=380 && my <= 380 + 60){
                              if(SpectrumTwo.getOpened() && !SpectrumTwo.getOpening()){
                                   //SpectrumTwo.setAddMoney(true);
                                   InventoryMenu.sellGun(mx, my);
                                   SpectrumTwo.setOpened(false);
                              }
                         }
                    }
                    
               }   
          }
     }
     
     @Override
     public void mouseReleased(MouseEvent e){
          int mx = e.getX();
          int my = e.getY();
          if(Game.menuOpened){
               
          }
          else{
               if(Game.State == Game.STATE.UPGRADE){
                    if(MouseMotion.sliderClicked == true){
                         MouseMotion.sliderClicked = false;
                         //MouseMotion.dragStart = true;
                         System.out.println("false");
                    }
                    
               }
          }
     }
}

