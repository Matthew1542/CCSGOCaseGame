import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import javax.imageio.*;
import java.io.*;
import java.util.jar.*;
import sun.misc.*;
import java.net.*;

public class Game extends Canvas implements Runnable {
     
     private static final long serialVersionID = 1L;
     public static final int WIDTH = 320;
     public static final int HEIGHT = WIDTH / 12 * 9;
     public static final int SCALE = 2;
     public final String TITLE = "Case Opening Simulator";
     
     private boolean running = false;
     private Thread thread;
     
     static Random r = new Random();
     private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
     public static BufferedImage cases = null;
     public static BufferedImage background = null;
     public static BufferedImage caseBackground = null;
     public static BufferedImage caseBackground2 = null;
     public static BufferedImage caseBackground3 = null;
     public static BufferedImage caseOpenedBackground = null;
     public static String moneyString;
     public static int change = 0;
     private boolean neg = false;
     public static int caseNumber;
     private Menu menu;
     private InventoryMenu inventoryMenu;
     private ClickMenu clickMenu;
     private UpgradeMenu upgradeMenu;
     private StatsMenu statsMenu;
     private OptionMenu optionMenu;
     private static boolean openCase;
     private static String caseID = "bravo";
     public static boolean changeCase = true;
     public static Map<String, SpectrumTwo> caseMap = new HashMap<String, SpectrumTwo>();
     public static ArrayList<String> ids = new ArrayList<String>();
     private SpectrumTwo tempCase;
     public static int pages;
     public static ArrayList<String> caseNames = new ArrayList<String>();
     
     public static double money = 0.00;
     public static ArrayList<InventoryItem> inventory = new ArrayList<InventoryItem>();
     public static ArrayList<Integer> caseInventory = new ArrayList<Integer>();
     public static File saveLocation = new File("resources/other/");
     public static boolean menuOpened = false;
     public static boolean menuClosing = false;
     public static int moveOver = 0;
     
     public static Properties prop = new Properties();
     
     public static enum STATE{
          MENU,
               SPECTRUM2,
               INVENTORY,
               CLICK,
               UPGRADE,
               OPTION,
               STATS
     };
     
     public static STATE State = STATE.UPGRADE;
     
     private static final long MEGABYTE = 1024L * 1024L;
     
     public static long bytesToMegabytes(long bytes) {
          return bytes / MEGABYTE;
     }
     
     public int getMemory(){
          Runtime runtime = Runtime.getRuntime();
          runtime.gc();
          long memory = runtime.totalMemory() - runtime.freeMemory();
          return (int)(bytesToMegabytes(memory));
     }
     
     
     public void init() {
          System.out.println(System.getProperty("user.name"));
          System.out.println("Game Width: " + Game.WIDTH);
          System.out.println("Game Height: " + Game.HEIGHT);
          System.out.println(getMemory());
          BufferedImageLoader loader = new BufferedImageLoader();
          try{
               cases = loader.loadImage("resources/cases.png");
               background = loader.loadImage("resources/backgroundBlank.png");
               caseBackground = loader.loadImage("resources/background.png");
               caseBackground2 = loader.loadImage("resources/background2.png");
               caseBackground3 = loader.loadImage("resources/background3.png");
               caseOpenedBackground = loader.loadImage("resources/BlankCaseOpened.png");
          }catch(IOException e){}   
          System.out.println(getMemory());
          clickMenu = new ClickMenu(this);
          upgradeMenu = new UpgradeMenu(this);
          menu = new Menu(this);
          inventoryMenu = new InventoryMenu(this);
          statsMenu = new StatsMenu(this);
          optionMenu = new OptionMenu(this);
          ArrayList<String> names = new ArrayList<String>();
          ArrayList<File> directories = new ArrayList<File>();
          ArrayList<File> temp = new ArrayList<File>();
          
          try{
               final String path = "resources";
               final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
               if(jarFile.isFile()) {
                    final JarFile jar = new JarFile(jarFile);
                    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
                    while(entries.hasMoreElements()) {
                         final String name = entries.nextElement().getName();
                         if(name.startsWith(path + "/")) { //filter according to the path
                              System.out.println(name);
                              if(name.indexOf("/", 10) != -1){
                                   if(names.contains(name.substring(0, name.indexOf("/", 11)))){}
                                   else
                                        names.add(name.substring(0, name.indexOf("/", 11)));
                              }
                         }
                    }
                    jar.close();
                    for(int i = 0; i < names.size(); i++){
                         temp.add(new File(names.get(i)));
                    }
               }
          }catch(IOException e){
          }catch(NullPointerException e){
               temp.addAll(Arrays.asList(new File("resources/").listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                         return file.isDirectory();
                    }
               })));
          }
          
          directories = temp;
          for(int d = 0; d < directories.size();d++){
               ids.add(directories.get(d).toString().replace("resources\\", ""));
               caseNames.add((directories.get(d).toString().replace("resources\\", "")).substring(0,1).toUpperCase() + (directories.get(d).toString().replace("resources\\", "")).substring(1).replace("Case_", "").replace("Case", "")); 
          }
          //System.out.println(caseNames); 
          for(int i = 0; i < ids.size(); i++){
               char ch;
               int cap = 0;
               for(int c = 0; c < caseNames.get(i).length(); c++){
                    ch = caseNames.get(i).charAt(c);
                    if(Character.isUpperCase(ch) && cap == 0){
                         cap = c;
                    }
                    if(Character.isDigit(ch) && cap == 0){
                         cap = c;
                    }
               }
               if(cap != 0){
                    caseNames.set(i, (caseNames.get(i).substring(0, cap) + " > " + caseNames.get(i).substring(cap,caseNames.get(i).length())));
               }
               caseNames.set(i, caseNames.get(i).replace("_", " "));
          }
          //System.out.println(caseNames);
          System.out.println(getMemory());
          try{
               try{
                    for(int things = 0; things < ids.size(); things++){
                         caseMap.put(ids.get(things), new SpectrumTwo(this, ids.get(things), caseNames.get(things)));
                    }
               }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null, "ERROR");
               }
          } catch (Throwable t) {
               Runtime runtime = Runtime.getRuntime();
               runtime.gc();
               long memory = runtime.totalMemory() - runtime.freeMemory();
               if(tempBy == 0)
                    tempBy = bytesToMegabytes(memory);
               if(tempBy != bytesToMegabytes(memory)){
                    tempBy = bytesToMegabytes(memory);
                    System.out.println("Used memory is bytes: " + memory);
                    System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
               }
               JOptionPane.showMessageDialog(null, bytesToMegabytes(memory));
               JOptionPane.showMessageDialog(null, t.getClass().getSimpleName() + ": " + t.getMessage());
          }
          ////////
          System.out.println(getMemory());
          InputStream input = null;
          try {
               input = new FileInputStream("C:/Users/" + System.getProperty("user.name") + "/saveOne.properties");
               prop.load(input);
               money = Double.parseDouble(prop.getProperty("money"));
               
               String[] allCases = (prop.getProperty("cases").replace("[", "").replace("]", "")).split(", ");
               Integer[] integers = new Integer[allCases.length]; 
               for (int i = 0; i < integers.length; i++){
                    integers[i] = Integer.parseInt(allCases[i]);
               }
               caseInventory.addAll(Arrays.asList(integers));
               
               ClickMenu.caseP = Double.parseDouble(prop.getProperty("caseP"));
               
                for(int i = 0; i < prop.size()-3; i++){
                     if(prop.getProperty("gun"+i).contains(",")){
                          String[] test = prop.getProperty("gun"+i).split(",");
                          String tempItem = test[0];
                          double tempgunPrice = Double.parseDouble(test[1]);
                          String tempquality = test[2];
                          boolean tempstatTrak = Boolean.parseBoolean(test[3]);
                          String tempcaseID = test[4];
                          int tempitemN = Integer.parseInt(test[5]);
                          Game.inventory.add(new InventoryItem(tempItem, tempgunPrice, tempquality, tempstatTrak, tempcaseID, tempitemN));
                     }
                }
          } catch (IOException ex) {
          } finally {
               if (input != null) {
                    try {
                         input.close();
                    } catch (IOException e) {
                         e.printStackTrace();
                    }
               }
          }
          Game.saveInventory();
          this.addMouseListener(new MouseInput()); 
          this.addMouseMotionListener(new MouseMotion());
     }
     private synchronized void start(){
          if(running)
               return;
          running = true;
          thread = new Thread(this);
          thread.start();
     }
     
     private synchronized void stop(){
          if(!running)
               return;
          running = false;
          try{
               thread.join();
          } catch (InterruptedException e) {}
          System.exit(1);
     }
     
     public void run(){
          init();
          long lastTime = System.nanoTime();
          final double amountOfTicks = 60.0;
          double ns = 1000000000 / amountOfTicks;
          double delta = 0;
          int updates = 0;
          int frames = 0;
          long timer = System.currentTimeMillis();
          while(running){
               long now = System.nanoTime();
               delta += (now - lastTime) / ns;
               lastTime = now;
               if(delta >= 1){
                    tick();
                    updates++;
                    delta--;
               }
               render();
               frames++;
               if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    updates = 0;
                    frames = 0;
               }
          } stop();
     }
     
     public static void saveInventory(){
          OutputStream output = null;
          try {
               if(!(new File("C:/Users/" + System.getProperty("user.name") + "/saveOne.properties").isFile()))
               {
                    caseInventory.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
               }
               output = new FileOutputStream("C:/Users/" + System.getProperty("user.name") + "/saveOne.properties");
               prop.setProperty("money", money + "");
               //System.out.println(caseInventory);
               prop.setProperty("caseP", ClickMenu.caseP + "");
               prop.setProperty("cases",caseInventory + "");
               //System.out.println(Game.inventory);
               for(int i = 0; i < inventory.size()+1;i++){
                    //System.out.println(prop.getProperty("gun"+i));
                    prop.remove("gun"+i);
               }
               for(int i = 0; i < inventory.size(); i++){
                    //System.out.println("Setting gun"+i+" to " + inventory.get(i).toString());
                    prop.setProperty("gun"+i, (inventory.get(i).toString() + ""));
               }
               prop.store(output, null);
          } catch (IOException io) {
               io.printStackTrace();
          } finally {
               if (output != null) {
                    try {
                         output.close();
                    } catch (IOException e) {
                         e.printStackTrace();
                    }
               }
          }
     }
     public static int mx;
     public static int my;
     
     public static void clearSave(){
          money = 0;
          prop.setProperty("money", money + "");
          caseInventory.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
          prop.setProperty("cases", caseInventory + "");
          ClickMenu.caseP = 0;
          prop.setProperty("caseP", ClickMenu.caseP + "");
          caseInventory.clear();
          for(int i = 0; i < inventory.size();i++){
               //System.out.println(prop.getProperty("gun"+i));
               prop.remove("gun"+i);
          }
          inventory.clear();
          saveInventory();
          InventoryMenu.loaded();
     }
     
     long tempBy = 0;
     int mem = 0;
     private void tick(){
          if(getMemory() != mem){
               mem = getMemory();
               System.out.println(mem);
          }
          if(changeCase){
               tempCase = caseMap.get(caseID);
               changeCase = false;
          }
          if(State == STATE.MENU){
               menu.tick();
          } 
          else if(State == STATE.INVENTORY){
               inventoryMenu.tick();
          }
          else if(State == STATE.CLICK){
               clickMenu.tick();
          }
          else if(State == STATE.UPGRADE){
               upgradeMenu.tick();
          }
          else if(State == STATE.OPTION){
               optionMenu.tick();
          }
          else if(State == STATE.STATS){
               statsMenu.tick();
          }
          else if(State == STATE.SPECTRUM2){
               tempCase.tick();
               if(openCase){
                    tempCase.open(mx, my);
                    openCase = false;
               }
          }
          if(money < 0){
               neg = true;
          }
          else
               neg = false;
          money = Math.round(money * 100.0) / 100.0; 
          double temp = Math.abs(money)*10;
          temp = (int)temp;
          temp = temp/10;
          if(neg){
               moneyString = "-";
          }
          else
               moneyString = "+";
          if(Math.abs(money) == 0.0)
               moneyString += "$" + Math.abs(money) + "0";
          else if(Math.abs(money) < .1)
               moneyString += "$" + Math.abs(money);
          else if(Math.abs(money) - (int)Math.abs(money) == .0)
               moneyString += "$" + Math.abs(money) + "0";
          else if(Math.abs(money) - temp == 0)
               moneyString += "$" + Math.abs(money) + "0";
          else
               moneyString += "$" + Math.abs(money);
     }
     
     
     
     private void render(){
          BufferStrategy bs = this.getBufferStrategy();
          if(bs == null){
               createBufferStrategy(3);
               return;
          }
          Graphics g = bs.getDrawGraphics();
          g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
          
          if(State == STATE.MENU){
               menu.render(g);
          }
          else if(State == STATE.INVENTORY){
               inventoryMenu.render(g);
          }
          else if(State == STATE.CLICK){
               clickMenu.render(g);
          }
          else if(State == STATE.UPGRADE){
               upgradeMenu.render(g);
          }
          else if(State == STATE.STATS){
               statsMenu.render(g);
          }
          else if(State == STATE.OPTION){
               optionMenu.render(g);
          }
          else if(State == STATE.SPECTRUM2){
               tempCase.render(g);
          }   
          g.dispose();
          bs.show();
     }
     public static void main(String args[]){
          Game game = new Game();
          game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
          game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
          game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
          JFrame frame = new JFrame(game.TITLE);
          WindowListener exitListener = new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e){
                    saveInventory();
                    System.exit(0);
               }
          };
          frame.add(game);
          frame.pack();
          frame.addWindowListener(exitListener);
          frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
          frame.setResizable(false);
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
          game.start();
     }     
     public BufferedImage getCases(){
          return cases;
     }
     public static void openCase(int x, int y){
          mx = x;
          my = y;
          openCase = true;
     }  
     public static void setCase(String id){
          caseID = id;
          changeCase = true;
     }
}