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
public class spitBomb extends Canvas implements Runnable{
    static int screenWidth;
    static int screenHeight;
    static int frameWidth;
    static int frameHeight;
    
    static boolean showLabel=false;
    
    int drawWidth;
    int drawHeight;
    
    Image bBufferImage=null;
    Graphics bBufferGraphics=null;
    static int player1keyPressed=0;
    static int player2keyPressed=0;
    
    char [][] currMap=null;
    
    int currLevel=1;
    
    int currMenuItem=1;
    
    PlayerCharacter [] PC;
    int nPCs=0;
    
    BlockSprite [][] Blocks = null;
    
    int currentState;
    
    int loadingTics=0;
    
    spitBomb(){
        currentState=States.Loading;
        currMap=Maps.level1;
        
        screenWidth=drawWidth=currMap[0].length*Defs.BlockWidth;
        screenHeight=drawHeight=currMap.length*Defs.BlockWidth;
        frameWidth=screenWidth+Defs.JFrameWidthError;
        frameHeight=screenHeight+Defs.JFrameHeightError;
    }
    
    void changeScreenSize(int size){
        switch(size){
            case Defs.screenSizeSmall1:{
                drawWidth=screenWidth>>1;
                drawHeight=screenHeight>>1;
                break;
            }
            case Defs.screenSizeSmall2:{
                drawWidth=screenWidth-(screenWidth/5);
                drawHeight=screenHeight-(screenHeight/5);
                break;
            }
            case Defs.screenSizeNormal:{
                drawWidth=screenWidth;
                drawHeight=screenHeight;
                break;
            }
            case Defs.screenSizeLarge1:{
                drawWidth=screenWidth+(screenWidth/5);
                drawHeight=screenHeight+(screenWidth/5);
                break;
            }
            case Defs.screenSizeLarge2:{
                drawWidth=screenWidth<<1;
                drawHeight=screenHeight<<1;
                break;
            }
        }
        Main.f.setSize(drawWidth+Defs.JFrameWidthError,drawHeight+Defs.JFrameHeightError);
    }

    void createMap()
    {
        Blocks=new BlockSprite[currMap.length][currMap[0].length];
        nPCs=2;
        
        int posX=0;
        int posY=0;
        int PCsCreated=0;
        
        for(int x=0;x<currMap.length;x++)
        {
            for(int y=0;y<currMap[0].length;y++)
            {
                boolean PCCreated=false;
                switch(Maps.level1[x][y])
                {
                    case 'B':
                    {
                        Blocks[x][y]=new BlockSprite(Defs.hardBlock,posX,posY);
                        break;   
                    }
                    case 'X':
                    {
                        Blocks[x][y]=new BlockSprite(Defs.softBlock,posX,posY);
                        break;
                    }
                    case Defs.PC:
                        if(PC==null){
                            PC= new PlayerCharacter[nPCs];
                        }
                        PC[PCsCreated++] = new PlayerCharacter("ChavaMan"+PCsCreated,Defs.Attitude_PC,Defs.PCSteps,Defs.PCShootDelay,posX,posY);
                        
                        PCCreated=true;
                    case Defs.PC2:
                        if(!PCCreated){
                            if(PC==null){
                                PC= new PlayerCharacter[nPCs];
                            }
                            PC[PCsCreated++] = new PlayerCharacter("ChavaMan"+PCsCreated,Defs.Attitude_PC,Defs.PCSteps,Defs.PCShootDelay,posX,posY);
                        }
                    case ' ':
                    {
                        if(Images.floor==null){
                            Images.floor=createImage(Defs.BlockWidth,Defs.BlockWidth);
                            Graphics tmpG=Images.floor.getGraphics();
                            tmpG.setColor(Color.lightGray);
                            tmpG.fillRect(0,0,Defs.BlockWidth,Defs.BlockWidth);
                        }
                        Blocks[x][y]=new BlockSprite(Images.floor,Defs.floor,posX,posY);
                        break;
                    }
                }
                posX+=Defs.BlockWidth;
            }
            posY+=Defs.BlockWidth;
            posX=0;
        }
    }
    void cleanAll(){
        currMap=null;
        PC=null;
        currLevel=1;
        System.gc();
    }
    
    void newGame(){
        cleanAll();
        
        currMap=Maps.level1;
        
        createMap();
        currentState=States.InGame;
        
        //playTitleMusic();
    }
    
    //Player player=null;
    //int lastSongPlayed=0;
    
    /*void playTitleMusic(){
        try{
            if(player!=null){
                player.close();
                System.gc();
            }
            
            BufferedInputStream bis;
            if(lastSongPlayed==1){
                bis = new BufferedInputStream(getClass().getResourceAsStream("sounds/Reptilia.mp3"));
                lastSongPlayed=2;
            } else {
                bis = new BufferedInputStream(getClass().getResourceAsStream("sounds/AreYouGonnaBeMyGirl.mp3"));
                lastSongPlayed=1;
            }
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
    
    void createMenu(){
        if (menuImages==null){
             menuImages = new Image[Defs.menuItems][2];
             menuImages[0][0]=Images.NewGame01;
             menuImages[0][1]=Images.NewGame02;
             menuImages[1][0]=Images.Continue01;
             menuImages[1][1]=Images.Continue02;
             menuImages[2][0]=Images.Multiplayer01;
             menuImages[2][1]=Images.Multiplayer02;
             menuImages[3][0]=Images.Help01;
             menuImages[3][1]=Images.Help02;
             menuImages[4][0]=Images.About01;
             menuImages[4][1]=Images.About02;
             menuImages[5][0]=Images.Exit01;
             menuImages[5][1]=Images.Exit02;
        }
    }
    void updateLoading(){
        switch(loadingTics)
        {
            case 0:
            {
                bBufferImage=createImage(screenWidth,screenHeight);
                bBufferGraphics=bBufferImage.getGraphics();
                break;
            }
            
            case 1:
            {
                new Images();
                new Sounds();
                break;
            }
            case 2:
            {
                createMenu();
                //createMap();
                break;
            }
            case 3:
            {
                break;
            }
            default:
            {
                currentState=States.Menu;
                break;
            }
        }
        loadingTics++;
    }
    int loadingPoint=0;
    String [] loadingPoints={" ",".","..","..."};
    
    void drawLoading(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,screenWidth,screenHeight);
        
        g.setColor(Color.WHITE);
        g.drawString("Loading"+loadingPoints[loadingPoint], (screenWidth>>1)-15, screenHeight>>1);
        loadingPoint=loadingPoint==3?0:loadingPoint+1;        
    }
    void doAction(int action){
        switch(action){
            case Defs.actionNewGame:{
                newGame();
                break;
            }
            case Defs.actionContinue:{
                currentState=States.InGame;
                break;
            }
            case Defs.actionMultiplayer:{
                break;
            }
            case Defs.actionAbout:{
                break;
            }
            case Defs.actionExit:{
                currentState=States.Exit;
                break;
            }
            case Defs.actionShowMenu:{
                currMenuItem=1;
                currentState=States.Menu;
                break;
            }
        }
    }
    
    void updateMenu(){
        switch(player1keyPressed){
            case Defs.key_Fire:{
                doAction(currMenuItem);
                player1keyPressed=-1;
                break;
            }
/*            case Defs.key_Up:{
                currMenuItem=currMenuItem==1?Defs.menuItems:currMenuItem-1;
                player1keyPressed=-1;
                break;
            }
            case Defs.key_Down:{
                currMenuItem=currMenuItem==Defs.menuItems?1:currMenuItem+1;
                player1keyPressed=-1;
                break;
            }*/
            case Defs.key_Esc:{
                currentState=States.Exit;
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F1:{
                changeScreenSize(Defs.screenSizeSmall1);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F2:{
                changeScreenSize(Defs.screenSizeSmall2);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F3:{
                changeScreenSize(Defs.screenSizeNormal);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F4:{
                changeScreenSize(Defs.screenSizeLarge1);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F5:{
                changeScreenSize(Defs.screenSizeLarge2);
                player1keyPressed=-1;
                break;
            }
        }
    }
    
    Image [][] menuImages=null;
    long lastTime=0;
    boolean showMessage=false;
    
    void drawMenu(Graphics g){
        g.drawImage(Images.Menu,0,0,screenWidth,screenHeight,null);
        if(System.currentTimeMillis()>lastTime+500){
            showMessage=!showMessage;
            lastTime=System.currentTimeMillis();
        }
        Color lastColor=g.getColor();
        Font lastFont=g.getFont();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",0,12));
        if(showMessage){
            g.drawString("Presiona ESPACIO para continuar",240,(screenHeight>>1)+100);
            g.drawString("presiona ESC para salir del juego",240,(screenHeight>>1)+120);
        }
        g.setColor(lastColor);
        g.setFont(lastFont);
        /*int y = Defs.menuY;
        for(int i=1;i<=Defs.menuItems;i++){
            if(i==currMenuItem){
                g.drawImage(menuImages[i-1][1],Defs.menuX,y,null);
            } else {
                g.drawImage(menuImages[i-1][0],Defs.menuX,y,null);
            }
            y+=35;
        }
        */      
    }

    boolean canMove(PlayerCharacter player, int direccion, boolean slide){
        boolean move=true;
        boolean blockBlocked=false;
        boolean playerBlocked=false;
        
        int leftXplayer=(int)player.x;
        int rightXplayer=(int)player.x+Defs.PlayerWidth;
        int topYplayer=(int)player.y;
        int downYplayer=(int)player.y+Defs.PlayerHeight;
        
        checkHaveBlockPos(player);
        
        for(int y=0; y<Blocks.length; y++){
            for(int x=0; x<Blocks[0].length; x++){
                if(leftXplayer+Defs.error>=Blocks[y][x].x && rightXplayer-Defs.error<=Blocks[y][x].x+Defs.BlockWidth &&//X's
                        topYplayer+Defs.error>=Blocks[y][x].y && downYplayer-Defs.error<=Blocks[y][x].y+Defs.BlockWidth)//Y's
                {
                    player.xBlockPos=x;
                    player.yBlockPos=y;
                }
            }
        }
        
        int blockX=player.xBlockPos;
        int blockY=player.yBlockPos;
        
        BlockSprite block = Blocks[blockY][blockX];
        BlockSprite rightBlock=Blocks[blockY][blockX+1];
        BlockSprite leftBlock=Blocks[blockY][blockX-1];
        BlockSprite upBlock=Blocks[blockY-1][blockX];
        BlockSprite downBlock=Blocks[blockY+1][blockX];
        
        blockBlocked=true;
        
        switch(direccion){
            case Defs.Up:{
                if(topYplayer>=upBlock.y && topYplayer<block.y+Defs.BlockWidth+Defs.error && /*(*/upBlock.type==Defs.floor/* || ((upBlock.type==Defs.softBlock || upBlock.type==Defs.hardBlock) && topYplayer>upBlock.y+Defs.BlockWidth+Defs.error))*/ &&
                        leftXplayer>=upBlock.x-Defs.error && rightXplayer<=upBlock.x+Defs.BlockWidth+Defs.error)
                {
                    blockBlocked=false;
                }
                break;
            }
            case Defs.Down:{
                if(downYplayer<=downBlock.y+Defs.BlockWidth && downYplayer>block.y-Defs.error && downBlock.type==Defs.floor &&
                        leftXplayer>=downBlock.x-Defs.error && rightXplayer<=downBlock.x+Defs.BlockWidth+Defs.error)
                {
                    blockBlocked=false;
                }
                break;
            }
            case Defs.Left:{
                if(leftXplayer>=leftBlock.x && leftXplayer<block.x+Defs.BlockWidth+Defs.error && (leftBlock.type==Defs.floor || ((leftBlock.type==Defs.softBlock || leftBlock.type==Defs.hardBlock) && leftXplayer>leftBlock.x+Defs.BlockWidth+Defs.error)) &&
                        topYplayer>=leftBlock.y-Defs.error && downYplayer<=leftBlock.y+Defs.BlockWidth+Defs.error)
                {
                    blockBlocked=false;
                }
                break;
            }
            case Defs.Right:{
                if(rightXplayer<=rightBlock.x+Defs.BlockWidth && rightXplayer>block.x-Defs.error && (rightBlock.type==Defs.floor || ((rightBlock.type==Defs.softBlock || rightBlock.type==Defs.hardBlock) && rightXplayer<rightBlock.x-Defs.error)) &&
                        topYplayer>=rightBlock.y-Defs.error && downYplayer<=rightBlock.y+Defs.BlockWidth+Defs.error)
                {
                    blockBlocked=false;
                }
                break;
            }
        }
        
        
        if(blockBlocked){
            if(slide){
                if((direccion==Defs.Up || direccion==Defs.Down) && player.slidingDir!=Defs.Right && player.slidingDir!=Defs.Left){
                    if(((leftXplayer+Defs.error + (Defs.PlayerWidth>>1)) <= (block.x + (Defs.BlockWidth>>1))) &&
                            (leftBlock.type==Defs.floor && 
                            ((direccion==Defs.Up && Blocks[blockY-1][blockX-1].type==Defs.floor) || (direccion==Defs.Down && Blocks[blockY+1][blockX-1].type==Defs.floor)))){
                        player.slidingDir=Defs.Left;
                    }
                    else if(((leftXplayer-Defs.error + (Defs.PlayerWidth>>1)) > (block.x + (Defs.BlockWidth>>1))) && 
                            (rightBlock.type==Defs.floor && 
                            ((direccion==Defs.Up && Blocks[blockY-1][blockX+1].type==Defs.floor) || (direccion==Defs.Down && Blocks[blockY+1][blockX+1].type==Defs.floor)))){
                        player.slidingDir=Defs.Right;
                    }
                }
                else if((direccion==Defs.Left || direccion==Defs.Right) && player.slidingDir!=Defs.Up && player.slidingDir!=Defs.Down){
                    if(((topYplayer+Defs.error + (Defs.PlayerHeight>>1)) <= (block.y + (Defs.BlockWidth>>1))) && 
                            (upBlock.type==Defs.floor && 
                            ((direccion==Defs.Left && Blocks[blockY-1][blockX-1].type==Defs.floor) || (direccion==Defs.Right && Blocks[blockY-1][blockX+1].type==Defs.floor)))){
                        player.slidingDir=Defs.Up;
                    }
                    else if((((topYplayer+Defs.error + (Defs.PlayerHeight>>1)) > (block.y + (Defs.BlockWidth>>1))) && 
                            (downBlock.type==Defs.floor) && 
                            ((direccion==Defs.Left && Blocks[blockY+1][blockX-1].type==Defs.floor) || (direccion==Defs.Right && Blocks[blockY+1][blockX+1].type==Defs.floor)))){
                        player.slidingDir=Defs.Down;
                    }
                }
                move(player,player.slidingDir,false);
            }
        } else {//si no fue bloqueado por los bloques checar si lo bloquean los players
            for(int i=0;i<PC.length;i++){
                PlayerCharacter plyer = PC[i];
                if(!plyer.killed && plyer!=player){
                    switch(direccion){
                        case Defs.Up:{
                            if(player.y<=plyer.y+Defs.PlayerHeight+Defs.error && player.y>=plyer.y+Defs.PlayerHeight-Defs.error &&
                                    ((player.x+Defs.error>=plyer.x && player.x-Defs.error<=plyer.x+Defs.PlayerWidth) || (player.x+Defs.PlayerWidth+Defs.error>=plyer.x && player.x+Defs.PlayerWidth-Defs.error<=plyer.x+Defs.PlayerWidth))){
                                playerBlocked=true;
                            }
                            break;
                        }
                        case Defs.Down:{
                            if(player.y+Defs.PlayerHeight>=plyer.y-Defs.error && player.y+Defs.PlayerHeight<=plyer.y+Defs.error &&
                                    ((player.x+Defs.error>=plyer.x && player.x-Defs.error<=plyer.x+Defs.PlayerWidth) || (player.x+Defs.PlayerWidth+Defs.error>=plyer.x && player.x+Defs.PlayerWidth-Defs.error<=plyer.x+Defs.PlayerWidth))){
                                playerBlocked=true;
                            }
                            break;
                        }
                        case Defs.Left:{
                            if(player.x<=plyer.x+Defs.PlayerWidth+Defs.error && player.x>=plyer.x+Defs.PlayerWidth-Defs.error &&
                                    ((player.y>=plyer.y && player.y<=plyer.y+Defs.PlayerHeight) || (player.y+Defs.PlayerHeight>=plyer.y && player.y+Defs.PlayerHeight<=plyer.y+Defs.PlayerHeight))){
                                playerBlocked=true;
                            }
                            break;
                        }
                        case Defs.Right:{
                            if(player.x+Defs.PlayerWidth>=plyer.x-Defs.error && player.x+Defs.PlayerWidth<=plyer.x+Defs.error &&
                                    ((player.y>=plyer.y && player.y<=plyer.y+Defs.PlayerHeight) || (player.y+Defs.PlayerHeight>=plyer.y && player.y+Defs.PlayerHeight<=plyer.y+Defs.PlayerHeight))){
                                playerBlocked=true;
                            }
                            break;
                        }
                    }
                }
                if(playerBlocked)
                    break;
            }
        }
        
        if(blockBlocked || playerBlocked)
            move=false;
        
        if(move)
            player.slidingDir=direccion;
        
        return move;
    }

    boolean move(PlayerCharacter player, int direccion, boolean slide){
        boolean canMove;
        canMove=canMove(player, direccion, slide);
        if(canMove)
            player.move(direccion);
        return canMove;
    }

    void bombBlockCollition(Bomb bomb){
        for(int y=0; y<Blocks.length; y++){
            for(int x=0; x<Blocks[0].length; x++){
                BlockSprite block = Blocks[y][x];
                if(block.type==Defs.softBlock || block.type==Defs.hardBlock){
                    int bombCenterX=(int)bomb.x+(Defs.BombWidth>>1);
                    int bombCenterY=(int)bomb.y+(Defs.BombHeight>>1);
                    if(bombCenterX>=block.x && bombCenterX<=block.x+Defs.BlockWidth && 
                            bombCenterY>=block.y && bombCenterY<=block.y+Defs.BlockWidth){
                        bomb.internatFinished=true;
                        if(block.type==Defs.softBlock)
                            block.explode();
                    }
                }
                if(bomb.finished)
                    break;
            }
            if(bomb.finished)
                break;
        }
    }
    
    void bombPlayerCollition(PlayerCharacter shooter, Bomb bomb){
        for(int iPlayer=0;iPlayer<PC.length;iPlayer++){
            PlayerCharacter player = PC[iPlayer];
            if(player!= shooter && !player.killed){
                int bombCenterX=(int)bomb.x+(Defs.BombWidth>>1);
                int bombCenterY=(int)bomb.y+(Defs.BombHeight>>1);
                if(bombCenterX>=player.x && bombCenterX<=player.x+Defs.PlayerWidth && 
                        bombCenterY>=player.y && bombCenterY<=player.y+Defs.PlayerHeight){
                    if(!bomb.internatFinished){
                            player.hurt();
                            bomb.internatFinished=true;
                    }
                    if(player==PC[0] || player==PC[1])
                        shakeWindow=true;
                }
            }
        }
    }
    void updateBombs(){
        for(int iPlayer=0;iPlayer<PC.length;iPlayer++){
            PlayerCharacter player = PC[iPlayer];
                
            if(!player.killed && player.activeBombs>0){
                int i=0;
                while(i<Defs.bombsPerPlayer){
                    if(player.bombs[i]!=null && !player.bombs[i].finished){
                        Bomb bomb = player.bombs[i];
                        bombBlockCollition(bomb);
                        bombPlayerCollition(player, bomb);
                    }
                    i++;
                }
            }
        }
    }
    
    void updatePC2(){
        switch(player2keyPressed){
            case Defs.key_Left:{
                PC[1].direccion=Defs.Left;
                move(PC[1], Defs.Left, true);
                break;
            }
            case Defs.key_Right:{
                PC[1].direccion=Defs.Right;
                move(PC[1], Defs.Right, true);
                break;
            }
            case Defs.key_Up:{
                PC[1].direccion=Defs.Up;
                move(PC[1], Defs.Up, true);
                break;
            }
            case Defs.key_Down:{
                PC[1].direccion=Defs.Down;
                move(PC[1], Defs.Down, true);
                break;
            }
            case Defs.key_Fire:{
                PC[1].Fire();
                break;
            }
        }
    }
    void updatePC1(){
        switch(player1keyPressed){
            case Defs.key_Left:{
                if(!PC[0].killed){
                    PC[0].direccion=Defs.Left;
                    move(PC[0], Defs.Left, true);
                }
                break;
            }
            case Defs.key_Right:{
                if(!PC[0].killed){
                    PC[0].direccion=Defs.Right;
                    move(PC[0], Defs.Right, true);
                }
                break;
            }
            case Defs.key_Up:{
                if(!PC[0].killed){
                    PC[0].direccion=Defs.Up;
                    move(PC[0], Defs.Up, true);
                }
                break;
            }
            case Defs.key_Down:{
                if(!PC[0].killed){
                    PC[0].direccion=Defs.Down;
                    move(PC[0], Defs.Down, true);
                }
                break;
            }
            case Defs.key_Fire:{
                if(!PC[0].killed){
                    PC[0].Fire();
                }
                break;
            }
            case Defs.key_Esc:{
                doAction(Defs.actionShowMenu);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F1:{
                changeScreenSize(Defs.screenSizeSmall1);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F2:{
                changeScreenSize(Defs.screenSizeSmall2);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F3:{
                changeScreenSize(Defs.screenSizeNormal);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F4:{
                changeScreenSize(Defs.screenSizeLarge1);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F5:{
                changeScreenSize(Defs.screenSizeLarge2);
                player1keyPressed=-1;
                break;
            }
            case Defs.key_F6:{
                showLabel=!showLabel;
                player1keyPressed=-1;
                break;
            }
        }
    }
    void checkHaveBlockPos(PlayerCharacter player){
        if(player.xBlockPos==-1 && player.yBlockPos==-1){
            double _blockX = ((player.x+(Defs.PlayerWidth>>1))*currMap[0].length)/screenWidth;
            double _blockY = ((player.y+(Defs.PlayerHeight>>1))*currMap.length)/screenHeight;
            player.xBlockPos=(int)Math.floor(_blockX);
            player.yBlockPos=(int)Math.floor(_blockY);
        }
    }
    
    boolean insideBlock(PlayerCharacter player, BlockSprite block){
        if(player.x>=block.x-Defs.error && player.x+Defs.PlayerWidth<=block.x+Defs.BlockWidth+Defs.error &&
                player.y>=block.y-Defs.error && player.y+Defs.PlayerHeight<=block.y+Defs.BlockWidth+Defs.error){
            return true;
        }
        return false;
    }

    void updateInGame(){
        updateBombs();
        updatePC1();
        if(!PC[1].killed)
            updatePC2();
        if(PC[0].killed || PC[1].killed)
            currentState=States.Finish;
        /*if(player.isComplete())
            playTitleMusic();*/
    }
    
    void drawInGame(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,screenWidth, screenHeight);
        for(int x=0;x<Blocks.length;x++)
            for(int y=0;y<Blocks[0].length;y++)
                Blocks[x][y].paint(g);
        PC[0].paint(g);
        PC[1].paint(g);
        
        drawHud(g);
    }
    public void run(){
        while(currentState!=States.Exit){
            repaint();
            try{Thread.sleep(15);}
            catch(Exception e){System.out.println(e.getMessage());}
        }
        System.exit(0);
    }
    void drawHud(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRoundRect(20,10,screenWidth-40,20,20,20);
        g.setColor(Color.WHITE);
        g.drawRoundRect(20,10,screenWidth-40,20,20,20);
        
        int x=45;
        
        g.setFont(new Font("Arial",1,16));
        g.drawString(PC[0].name+" Lives "+PC[0].lives, x,25);
        x+=200;
        g.drawString(PC[1].name+" Lives "+PC[1].lives, x,25);
    }
    
    boolean shakeWindow=false;
    int translateX=0;
    int translateY=0;
    int iterator=5;
    
    void shake(){
        if(iterator!=0){
            translateX=iterator;
            iterator*=-1;
            if(iterator>0)
                iterator--;
        } else {
            iterator=5;
            shakeWindow=false;
        }
    }
    void updateWinner(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,drawWidth,drawHeight);
        g.setColor(Color.BLACK);
        g.drawString("Player "+(PC[0].killed?"2":"1")+" Wins!!!",270,drawHeight>>1);
    }
    
    @Override public void update(Graphics g){
        if(shakeWindow){
            shake();
            g.translate(translateX, translateY);
        }
        
        paint(bBufferGraphics);
        g.drawImage(bBufferImage,0,0,drawWidth,drawHeight,null);
    }
    int itWinner=50;
    @Override public void paint(Graphics g){
        switch(currentState){
            case States.Loading:{
                updateLoading();
                drawLoading(g);
                break;
            }
            case States.Menu:{
                updateMenu();
                drawMenu(g);
                break;
            }
            case States.InGame:{
                updateInGame();
                drawInGame(g);
                break;
            }
            case States.Finish:{
                if(itWinner!=0){
                    updateWinner(g);
                    itWinner--;
                } else {
                    itWinner=50;
                    currentState=States.Menu;
                }
            }
        }
    }
}
