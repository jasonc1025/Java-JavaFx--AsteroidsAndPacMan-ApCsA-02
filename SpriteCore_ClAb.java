// * Remove fields 'width' & 'height' to reduce fields and dynamically create value via 'getWidth()' & 'getHeight()'

package AsteroidsAndPacMan_Package;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public abstract class SpriteCore_ClAb
{
    // * Private Fields
    //
    private Image iconImage_Fld;
    private double positionX_Fld;
    private double positionY_Fld;
    private double velocityX_Fld;
    private double velocityY_Fld;
    private boolean velocityMovable_Boo_Fld;
    private double  velocityX_Prev_Fld;
    private double  velocityY_Prev_Fld;

    // * Constructors
    //
    public SpriteCore_ClAb()
    {
        // [jwc] this("earth.png", 0, 0);  // Default position of (250,250)
        this("/images/earth.png", 0, 0, false, 0.0, 0.0);  // Default position of (250,250)
    }
    public SpriteCore_ClAb(String imageFilenameIn)
    {
        this(imageFilenameIn, 0, 0, false, 0.0, 0.0);  // Default position of (0,0)
    }
    public SpriteCore_ClAb(String imageFilenameIn, double positionXIn, double positionYIn, boolean velocityMovableBooIn, double velocityXIn, double velocityYin)
    {
        this.setImage(imageFilenameIn);
        positionX_Fld = positionXIn;
        positionY_Fld = positionYIn;

        velocityX_Fld = velocityXIn;
        velocityY_Fld = velocityYin;

        velocityMovable_Boo_Fld = velocityMovableBooIn;

        velocityX_Prev_Fld = 0;
        velocityY_Prev_Fld = 0;
    }

    //
    // * Public Methods
    //

    public Image getImage()
    {
        return iconImage_Fld;
    }
    public void setImage(Image i)
    {
        iconImage_Fld = i;
        // width = i.getWidth();
        // height = i.getHeight();
    }
    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public double getPositionX()
    {
        return positionX_Fld;
    }
    public double getPositionY()
    {
        return positionY_Fld;
    }
    public void setPosition(double x, double y)
    {
        positionX_Fld = x;
        positionY_Fld = y;
    }

    public boolean getVelocityMovable()
    {
        return velocityMovable_Boo_Fld;
    }
    public void setVelocityMovable( boolean velocityMovableBooIn )
    {
        velocityMovable_Boo_Fld = velocityMovableBooIn;
    }

    public double getVelocityX()
    {
        return velocityX_Fld;
    }
    public double getVelocityY()
    {
        return velocityY_Fld;
    }
    public void setVelocity(double x, double y)
    {
        velocityX_Fld = x;
        velocityY_Fld = y;
    }
//
//y-    public void addVelocity(double x, double y)
//    {
//        velocityX_Fld += x;
//        velocityY_Fld += y;
//    }
//y-    public void updateMove(double time)
//    {
//        positionX_Fld += velocityX_Fld * time;
//        positionY_Fld += velocityY_Fld * time;
//    }

    public double getVelocityXPrev()
    {
        return velocityX_Prev_Fld;
    }
    public double getVelocityYPrev()
    {
        return velocityY_Prev_Fld;
    }
    public void setVelocityPrev(double x, double y)
    {
        velocityX_Prev_Fld = x;
        velocityY_Prev_Fld = y;
    }

    // * such a core utility, keep here. Do not move it down the hierarchy tree
    //
    public void render(GraphicsContext gc)
    {
        gc.drawImage(iconImage_Fld, positionX_Fld, positionY_Fld);
    }

    // * such a core utility, keep here. Do not move it down the hierarchy tree
    //
    public Rectangle2D getBoundary()
    {
        // System.out.println("this: positionX_Fld,positionY_Fld,width,height: " + this  + ": " + positionX_Fld + "," + positionY_Fld + "," + width + "," + height);
        // System.out.println("this: positionX_Fld,positionY_Fld,width,height: " + this  + ": " + positionX_Fld + "," + positionY_Fld);
        // return new Rectangle2D(positionX_Fld,positionY_Fld,width,height);
        //y- return new Rectangle2D(positionX_Fld, positionY_Fld, iconImage_Fld.getWidth(), iconImage_Fld.getHeight());
        //y- return new Rectangle2D(positionX_Fld, positionY_Fld, getImage().getWidth(), getImage().getHeight());
        return new Rectangle2D(getPositionX(), getPositionY(), getImage().getWidth(), getImage().getHeight());
    }

    public String toString()
    {
        //        return " Position: [" + positionX_Fld + "," + positionY_Fld + "]"
        //                + " Velocity: [" + velocityX_Fld + "," + velocityY_Fld + "]";
        return " Position: [" + getPositionX() + "," + getPositionY() + "]"
                + " Velocity: [" + getVelocityX() + "," + getVelocityY() + "]";

    }
}