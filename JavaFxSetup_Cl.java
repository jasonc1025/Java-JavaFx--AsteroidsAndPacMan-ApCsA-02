package AsteroidsAndPacMan_Package;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JavaFxSetup_Cl {

    static void setup_StageAndRoot(Stage theStage, Group root, Scene theScene, Canvas canvas)
    {
        theStage.setTitle( "Asteroids and PacMan Mix" );
        theStage.setScene( theScene );

        root.getChildren().add( canvas );

    }

    static void setup_SetOnKey(Scene theScene, ArrayList<String> playerInputList)
    {
        theScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                String playerInputCode = e.getCode().toString();
                if ( !playerInputList.contains(playerInputCode) ) {
                    playerInputList.add( playerInputCode );
                    // * Debug Print
                    System.out.println(playerInputList.toString());
                }
            }
        });

        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String playerInputCode = e.getCode().toString();
                        playerInputList.remove( playerInputCode );
                    }
                });

    }

    static void setup_GraphicContext(GraphicsContext graphicContextObj)
    {
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        graphicContextObj.setFont( theFont );
        graphicContextObj.setFill( Color.GREEN );
        graphicContextObj.setStroke( Color.BLACK );
        graphicContextObj.setLineWidth(1);
    }

}
