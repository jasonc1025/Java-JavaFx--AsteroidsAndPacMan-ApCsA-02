/*

* TODO
*
* Abstract
    * SpriteCore_ClAb

* Interface: Feature
*
    * Abstract Method: Gerund: Return State
        * Collidable: Colliding
        *
    *  Method: Action: Verb
        * Rendarable Class: Render
*/

package AsteroidsAndPacMan_Package;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// * 'AsteroidsAndPacMan_ClApp' the entry-point for this app
public class AsteroidsAndPacMan_ClApp extends Application
{
    public static final int SCREEN_LENGTH_X = 500;
    public static final int SCREEN_LENGTH_Y = 500;


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        Group root = new Group();
        Scene theScene = new Scene( root );
        Canvas canvas = new Canvas( SCREEN_LENGTH_X, SCREEN_LENGTH_Y );
        JavaFxSetup_Cl.setup_StageAndRoot(theStage, root, theScene, canvas );

        ArrayList<String> playerMe_Input_ArrLst = new ArrayList<String>();
        JavaFxSetup_Cl.setup_SetOnKey( theScene, playerMe_Input_ArrLst);

        GraphicsContext graphicContext_Ob = canvas.getGraphicsContext2D();
        JavaFxSetup_Cl.setup_GraphicContext( graphicContext_Ob );

        //        SpriteCore_ClAb backgroundEarth_Ob = new Sprite_CollidableNot_MovableNot_Cl();
        Sprite_CollidableNot_MovableNot_Cl backgroundEarth_Ob = new Sprite_CollidableNot_MovableNot_Cl();

        //y- Sprite_CollidableYes_MovableYes_Cl playerMe_Ob = new Sprite_CollidableYes_MovableYes_Cl("/images/briefcase.png");
        Sprite_CollidableYes_MovableYes_Cl playerMe_Ob = new Sprite_CollidableYes_MovableYes_Cl("/images/CalvinHobbes-Saucer.png");

        //n- ArrayList<SpriteCore_ClAb> projectile_ArrLst = new ArrayList<SpriteCore_ClAb>();
        ArrayList<Sprite_CollidableYes_MovableYes_Cl> projectile_ArrLst = new ArrayList<>();

        // * Use SuperClass 'SpriteCore_ClAb' as polymorphic-type so each arraylist slot can be randomly-assigned any subclass
        // ** through following for-loop
        ArrayList<SpriteCore_ClAb> targetBot_ArrLst = new ArrayList<>();
        targetBot_ArrLst_Setup_Mth(targetBot_ArrLst);

        // * Instantiate the following objects since need access inner-class by following 'AnimationTimer'
        LongValue lastCycle_NanoTime = new LongValue( System.nanoTime() );
        LongValue lastProjectile_NanoTime = new LongValue( System.nanoTime() );
        LongValue elapsedFpsBetweenCycles = new LongValue(0 );
        //n- Long elapsedFpsBetweenCycles = new Long(0 );

        IntValue score = new IntValue(0);

        //
        // * GameEngine Cycle
        //
        new AnimationTimer()
        {
            public void handle(long currentCycle_NanoTime)
            {
                //
                // * Timer Update
                //

                // * Calculate timer since last GameEngine Cycle
                Double elapsedTimeBetweenCycles_InSec = (currentCycle_NanoTime - lastCycle_NanoTime.value) / 1000000000.0;
                elapsedFpsBetweenCycles.value = Math.round(1/elapsedTimeBetweenCycles_InSec);
                lastCycle_NanoTime.value = currentCycle_NanoTime;
                // y- debug- System.out.println("> "+ elapsedFpsBetweenCycles.value);

                //
                // * PlayerMe Input Update
                //
                playerMe_Input_Move_Mth(playerMe_Ob, playerMe_Input_ArrLst);

                playerMe_Input_Projectile_Mth(currentCycle_NanoTime, playerMe_Input_ArrLst, lastProjectile_NanoTime, playerMe_Ob, projectile_ArrLst);

                //
                // * Move Update
                //

                playerMe_Ob.updateMove( elapsedTimeBetweenCycles_InSec );

                for (Sprite_CollidableYes_MovableYes_Cl projectileOb : projectile_ArrLst ) {
                    projectileOb.updateMove(elapsedTimeBetweenCycles_InSec);
                }
                for (SpriteCore_ClAb targetBotOb : targetBot_ArrLst ) {
//                for (Sprite_CollidableYes_MovableYes_Cl targetBotOb : targetBot_ArrLst ) {
                    if( targetBotOb.getVelocityMovable() ) {
                        // * IMPORTANT: Since arrayList is of type 'SpriteCore_ClAb', need following type-casting to access following methods
                        Sprite_CollidableYes_MovableYes_Cl targetBotObTmp = (Sprite_CollidableYes_MovableYes_Cl) targetBotOb;
//                        targetBotOb.updateMove(elapsedTimeBetweenCycles_InSec);
                        targetBotObTmp.updateMove(elapsedTimeBetweenCycles_InSec);
                    }
                }
                // * May not be needed since stationary, but still 'updateMove' in case may move later
                // TODO: leave out for now                backgroundEarth_Ob.updateMove(elapsedTimeBetweenCycles_InSec);

                //
                // * Collision Detection Update: TargetBot vs PlayerMe
                //

                // * 'Iterator<>' needed since will write into it with 'targetBotIterator.remove()'
                // * Use SuperClass 'SpriteCore_ClAb' as polymorphic-type so object can flexibly handle any of the subclasses
                Iterator<SpriteCore_ClAb> targetBotIterator = targetBot_ArrLst.iterator();
                while ( targetBotIterator.hasNext() )
                {
                    SpriteCore_ClAb targetBotOb = targetBotIterator.next();
                    if ( playerMe_Ob.colliding(targetBotOb) )
                    {
                        targetBotIterator.remove();
                        score.value++;
                    }
                }

                //
                // * Collision Detection Update: TargetBot vs Projectiles
                //

                // Reset 'targetBotIterator'
                targetBotIterator = targetBot_ArrLst.iterator();
                while ( targetBotIterator.hasNext() )
                {
                    // * Use SuperClass 'SpriteCore_ClAb' as polymorphic-type so object can flexibly handle any of the subclasses
                    SpriteCore_ClAb targetBotOb = targetBotIterator.next();

                    //n- Iterator<SpriteCore_ClAb> projectileIterator = projectile_ArrLst.iterator();
                    Iterator<Sprite_CollidableYes_MovableYes_Cl> projectileIterator = projectile_ArrLst.iterator();
                    while ( projectileIterator.hasNext() )
                    {
                        Sprite_CollidableYes_MovableYes_Cl projectile_Ob = projectileIterator.next();
                        if (projectile_Ob.colliding(targetBotOb))
                        {
// TODO at java.util.ArrayList$Itr.remove(ArrayList.java:872)
                            targetBotIterator.remove();
                            score.value++;
                        }
                    }
                }


                //
                // * Screen Render Update
                //

                graphicContext_Ob.clearRect(0, 0, 512,512);

                backgroundEarth_Ob.render( graphicContext_Ob );

                // * Use 'for' loop sufficient since read-only and not need to write ('Iterator<>')
                // * Use SuperClass 'SpriteCore_ClAb' as polymorphic-type so object can flexibly handle any of the subclasses
                for (SpriteCore_ClAb targetBotOb : targetBot_ArrLst )
                    targetBotOb.render( graphicContext_Ob );

                // * Render last to be on top of other icons
                playerMe_Ob.render( graphicContext_Ob );


                // * Render last to be on top of other icons
                // * Use 'for' loop sufficient since read-only and not need to write ('Iterator<>')
                // * Use SuperClass 'SpriteCore_ClAb' as polymorphic-type so object can flexibly handle any of the subclasses
                for (SpriteCore_ClAb projectileOb : projectile_ArrLst )
                    projectileOb.render( graphicContext_Ob );

                //[jwc] String pointsText = "Cash: $" + (100 * score.value);
                String pointsText = "TargetBots: " + (1 * score.value);
                graphicContext_Ob.fillText( pointsText,300,40 );
                graphicContext_Ob.strokeText( pointsText,300,40 );

                String pointsText_02 = "FPS: " + (1 * elapsedFpsBetweenCycles.value);
                graphicContext_Ob.fillText( pointsText_02,300, 70 );
                graphicContext_Ob.strokeText( pointsText_02,300, 70 );
            }
        }.start();

        theStage.show();
    }

    private void targetBot_ArrLst_Setup_Mth(ArrayList<SpriteCore_ClAb> targetBot_ArrLst) {
        //y- for (int i = 0; i < 15; i++)
        for (int i = 0; i < 10; i++)
        {
            // * Not use 'Math.random()' since less flexible and less convenient
            Random rand = new Random();

            // * Do not embed this calculation within Constructor since will complicate it and rather teach simplest Constructor
            //y- double px = (SCREEN_LENGTH_X -100) * Math.random() + 50;
            //y- double py = (SCREEN_LENGTH_Y -100) * Math.random() + 50;
            double px = (SCREEN_LENGTH_X -100) * rand.nextDouble() + 50;
            double py = (SCREEN_LENGTH_Y -100) * rand.nextDouble() + 50;

            if( rand.nextBoolean() ){
                System.out.println(" * True: Sprite_CollidableYes_MovableYes_Cl");
                // * Use SuperClass 'SpriteCore_ClAb' as polymorphic-type so object can flexibly handle any of the subclasses
                // targetBotOb.setPosition(px,py);
                // SpriteCore_ClAb targetBotOb = new SpriteCore_ClAb("ufo.png", px, py);
                //[jwc] SpriteCore_ClAb targetBotOb = new SpriteCore_ClAb("/images/ufo.png", px, py);
                //y- SpriteCore_ClAb targetBotOb = new IconBriefcase_Cl("/images/ufo.png", px, py);

                double vx = 50 - ( rand.nextInt(100) + 1 ); // * Base-1: {-50 to +50}
                double vy = 50 - ( rand.nextInt(100) + 1 ); // * Base-1: {-50 to +50}

                SpriteCore_ClAb targetBotOb = new Sprite_CollidableYes_MovableYes_Cl("/images/ufo.png", px, py, vx, vy);
                targetBot_ArrLst.add( targetBotOb );
            }
            else{
                System.out.println(" * False: Sprite_CollidableYes_MovableNot_Cl");
                SpriteCore_ClAb targetBotOb = new Sprite_CollidableYes_MovableNot_Cl("/images/ufo.png", px, py);
                targetBot_ArrLst.add( targetBotOb );
            }
        }
    }

    private void playerMe_Input_Projectile_Mth(long currentCycle_NanoTime, ArrayList<String> playerMe_Input_ArrLst, LongValue lastProjectile_NanoTime, Sprite_CollidableYes_MovableYes_Cl playerMe_Ob, ArrayList<Sprite_CollidableYes_MovableYes_Cl> projectile_ArrLst) {
        // * IMPORTANT BUG WORKAROUND: Though UP && DOWN && SPACE unexpectedly does not work, A && S && SPACE does work :)+
        // * IMPORTANT: 1 sec = 1 x 10^9 nano-sec
        // * IMPORTANT: To avoid 'java: integer number too large' error, require 'l' for 64bit otherwise 32bit default
        // * Projectile at 1/10 sec frequency
        if ( (playerMe_Input_ArrLst.contains("SPACE")) && ( currentCycle_NanoTime - lastProjectile_NanoTime.value > 100000000l ) ){
            // * Projectile requires non-zero[ playerMe_Ob.getVelocityXPrev || playerMe_Ob.getVelocityYPrev ]
            if( Math.abs( playerMe_Ob.getVelocityXPrev() ) >= 1 || Math.abs( playerMe_Ob.getVelocityYPrev() ) >= 1 ) {
                //y- SpriteCore_ClAb projectileObj = new Sprite_CollidableYes_MovableYes_Cl("/images/earth.png", playerMe_Ob.getPositionX(), playerMe_Ob.getPositionY());
                //y- Sprite_CollidableYes_MovableYes_Cl projectileObj = new Sprite_CollidableYes_MovableYes_Cl("/images/earth.png",
                Sprite_CollidableYes_MovableYes_Cl projectileObj = new Sprite_CollidableYes_MovableYes_Cl("/images/Circle-Green-20x20.png",
                                                                                    playerMe_Ob.getPositionX() + (playerMe_Ob.getImage().getWidth()/2), playerMe_Ob.getPositionY() + (playerMe_Ob.getImage().getHeight()/2),
                                                                                     playerMe_Ob.getVelocityXPrev() * 2,playerMe_Ob.getVelocityYPrev() * 2 );
                projectile_ArrLst.add(projectileObj);
                lastProjectile_NanoTime.value = currentCycle_NanoTime;
            }
        }
    }

    private void playerMe_Input_Move_Mth(Sprite_CollidableYes_MovableYes_Cl playerMe_Ob, ArrayList<String> playerMe_Input_ArrLst) {
        playerMe_Ob.setVelocity(0,0);
        if (playerMe_Input_ArrLst.contains("LEFT") || playerMe_Input_ArrLst.contains("A")) {
            playerMe_Ob.addVelocity(-50, 0);
//                    playerMe_Ob.addVelocityPrev( -50, 0);
            playerMe_Ob.setVelocityPrev( playerMe_Ob.getVelocityX(), playerMe_Ob.getVelocityY());
        }
        if (playerMe_Input_ArrLst.contains("RIGHT") || playerMe_Input_ArrLst.contains("D")){
            playerMe_Ob.addVelocity(50,0);
//                    playerMe_Ob.addVelocityPrev( 50, 0);
            playerMe_Ob.setVelocityPrev( playerMe_Ob.getVelocityX(), playerMe_Ob.getVelocityY());

        }
        if (playerMe_Input_ArrLst.contains("UP") || playerMe_Input_ArrLst.contains("W")) {
            playerMe_Ob.addVelocity(0, -50);
//                    playerMe_Ob.addVelocityPrev( 0, -50);
            playerMe_Ob.setVelocityPrev( playerMe_Ob.getVelocityX(), playerMe_Ob.getVelocityY());

        }
        if (playerMe_Input_ArrLst.contains("DOWN") || playerMe_Input_ArrLst.contains("S")) {
            playerMe_Ob.addVelocity(0, 50);
//                    playerMe_Ob.addVelocityPrev( 0, 50);
            playerMe_Ob.setVelocityPrev( playerMe_Ob.getVelocityX(), playerMe_Ob.getVelocityY());

        }
    }
}