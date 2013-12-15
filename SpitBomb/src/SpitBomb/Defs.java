/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SpitBomb;

/**
 *
 * @author Salvador
 */
public interface Defs {
    final char NPC='N';
    final char PC='1';
    final char PC2='2';
    
    final int Attitude_PC=-1;
    final int Attitude_Passive=0;
    final int Attitude_Active=1;
    final int Attitude_Crazy=3;
    final int Attitude_Punisher=4;
    
    final int JFrameWidthError=10;
    final int JFrameHeightError=30;
    
    final int BlockWidth=45;
    final int PlayerWidth=33;
    final int PlayerHeight=45;
    final int BombWidth=34;
    final int BombHeight=34;
    
    final int actionNewGame=1;
    final int actionContinue=2;
    final int actionMultiplayer=3;
    final int actionHelp=4;
    final int actionAbout=5;
    final int actionExit=6;
    final int menuItems=6;
    
    final int actionShowMenu=7;
    
    
    final int screenSizeSmall1=1;
    final int screenSizeSmall2=2;
    final int screenSizeNormal=3;
    final int screenSizeLarge1=4;
    final int screenSizeLarge2=5;
    
    final int menuY=150;
    final int menuX=290;
    
    /*final int screenWidth=Maps.level1[0].length*BlockWidth;
    final int screenHeight=Maps.level1.length*BlockWidth;
    final int frameWidth=screenWidth+15;
    final int frameHeight=screenHeight+30;*/
    
    
    final int hardBlock=1;
    final int softBlock=2;
    final int floor=3;
    
    final int key_Enter=1;
    final int key_Esc=2;
    final int key_Left=3;
    final int key_Right=4;
    final int key_Down=5;
    final int key_Up=6;
    final int key_Fire=7;
    final int key_F1=8;
    final int key_F2=9;
    final int key_F3=10;
    final int key_F4=11;
    final int key_F5=12;
    final int key_F6=13;
    
    final int Right=0;
    final int Left=1;
    final int Up=2;
    final int Down=3;
    final int FireLeft=4;
    final int FireRight=5;
    final int FireDown=6;
    
    final int PCSteps=5;
    final int PCShootDelay=300;
    final int NPCShootDelay=1000;
    final int NPCSteps=2;
    final int BombSteps=7;
    
    final int bombsPerPlayer=50;
    
    final int error=4;
}
