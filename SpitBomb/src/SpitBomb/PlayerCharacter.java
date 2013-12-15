/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SpitBomb;
import java.awt.*;
import java.io.BufferedInputStream;
//import javazoom.jl.player.Player;
/**
 *
 * @author Salvador
 */
public class PlayerCharacter {
    
    PlayerCharacter target=null;
    
    int iRoute=0;
    
    int Attitude=Defs.Attitude_PC;
    
    int xBlockPos=-1;
    int yBlockPos=-1;
    
    int lives=15;
    int activeBombs=0;
    int speed=0;
    int shootDelay=0;
    String name="";
    Label lblName=null;
    
    boolean killed=false;
    
    double lastFireTime=0;
    
    Bomb [] bombs;
    
    Image [] imgs;
    int direccion=Defs.Down;
    
    int slidingDir=-1;
    
    double x;
    double y;
    
    PlayerCharacter(String name,int attitude, int speed,int shootDelay, int x, int y){
        this.Attitude=attitude;
        this.name=name;
        this.speed=speed;
        this.shootDelay=shootDelay;
        this.x=x;
        this.y=y;
        
        imgs = new Image[7];
        bombs = new Bomb[Defs.bombsPerPlayer];
         
        load();
    }
    //Player player;
    /*void playSFX(){
        try{
            if(player!=null){
                player.close();
                System.gc();
            }
            
            BufferedInputStream bis = new BufferedInputStream(getClass().getResourceAsStream("sounds/sfx_killed.mp3"));
            player = new Player(bis);
            
        }catch(Exception e){System.out.println("error "+e.getMessage());}
        new Thread() {
            @Override public void run() {
                try {
                    player.play();
                }catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }*/
    void hurt(){
        
        lives--;
        if(lives==0){
            killed=true;
            //playSFX();
        }
                 
    }
    
    void move(int direccion){
        switch(direccion){
            case Defs.Right:{
                moveRight();
                break;
            }
            case Defs.Left:{
                moveLeft();
                break;
            }
            case Defs.Up:{
                moveUp();
                break;
            }
            case Defs.Down:{
                moveDown();
                break;
            }
        }
        if(Attitude>-1){
            this.direccion=direccion;
        }
    }
    void moveLeft(){
        x-=speed;
    }
    
    void moveRight(){
        x+=speed;
    }
    
    void moveUp(){
        y-=speed;
    }
    
    void moveDown(){
        y+=speed;
    }
    
    void fireNewBomb(int direccion){
        int bomb=0;
        while(bombs[bomb]!=null && bomb<bombs.length){
            bomb++;
        }
        if(bomb<bombs.length && bombs[bomb]==null){
            double xB=this.x;
            double yB=this.y;
            
            if(direccion==Defs.Right){
                xB+=Defs.PlayerWidth;
                yB+=Defs.PlayerHeight>>1;
            } 
            else if(direccion==Defs.Left){
                xB-=Defs.BombWidth;
                yB+=Defs.PlayerHeight>>1;
            }
            else if(direccion==Defs.Up){
                xB+=Defs.PlayerWidth>>4;
                yB-=Defs.BombHeight;
            }
            else if(direccion==Defs.Down){
                xB+=Defs.PlayerWidth>>4;
                yB+=Defs.PlayerHeight;
            }
            bombs[bomb]=new Bomb(direccion,(int)xB,(int)yB);
        }
    }
    void Fire(){
        if(System.currentTimeMillis()>lastFireTime+shootDelay){
            fireNewBomb(direccion);
            activeBombs++;

            if(direccion==Defs.Right)
                direccion=Defs.FireRight;
            else if(direccion==Defs.Left)
                direccion=Defs.FireLeft;
            else if (direccion==Defs.Down)
                direccion=Defs.FireDown;
            fireTics=5;
            lastFireTime=System.currentTimeMillis();
        }
    }
    
    void load(){
        imgs[Defs.Right] = Images.PC_Right;
        imgs[Defs.Left] = Images.PC_Left;
        imgs[Defs.Up] = Images.PC_Up;
        imgs[Defs.Down] = Images.PC_Down;
        imgs[Defs.FireLeft] = Images.PC_LeftOpen;
        imgs[Defs.FireRight] = Images.PC_RightOpen;
        imgs[Defs.FireDown] = Images.PC_DownOpen;
    }
    int fireTics=5;
    void paint(Graphics g){
        if(imgs!=null){
            if(killed)
                g.drawImage(Images.killed,(int)x,(int)y,50,50,null);
            else {
                g.drawImage(imgs[direccion],(int)x,(int)y,null);
                if(spitBomb.showLabel){
                    g.setColor(Color.RED);
                    int lblWidth = 8*name.length();
                    g.fillRect((int)x+20,(int)y-10,lblWidth,12);
                    g.setColor(Color.BLACK);
                    g.drawRect((int)x+20,(int)y-10,lblWidth,12);
                    g.setColor(Color.WHITE);
                    g.drawString(name,(int)x+20+3,(int)y+2);
                }
            }
            
            int i=0;
            while(i<bombs.length){
                if(bombs[i]!=null){
                    if(!bombs[i].finished){
                        bombs[i].paint(g);
                        bombs[i].update();    
                    }
                    else{
                        bombs[i]=null;
                        activeBombs--;
                    }
                }
                i++;
            }
            if(fireTics==0){
                if(direccion==Defs.FireRight)
                    direccion=Defs.Right;
                else if(direccion==Defs.FireLeft)
                    direccion=Defs.Left;
                else if(direccion==Defs.FireDown)
                    direccion=Defs.Down;
            } else {
                fireTics--;
            }
        }
    }

}
