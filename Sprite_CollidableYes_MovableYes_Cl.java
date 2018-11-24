package AsteroidsAndPacMan_Package;

//y- class IconBriefcase_Cl extends SpriteCore_ClAb implements Collidable_If, Movable_If {
class Sprite_CollidableYes_MovableYes_Cl extends SpriteCore_ClAb implements Collidable_If, Movable_If {

    // * Constructors
    //
    public Sprite_CollidableYes_MovableYes_Cl()
    {
        // [jwc] this("earth.png", 0, 0);  // Default position of (250,250)
        // * 'velocityMovableBooIn' override default of 'false'
        super("/images/earth.png", 0, 0, true, 0.0, 0.0);  // Default position of (250,250)
    }
    public Sprite_CollidableYes_MovableYes_Cl(String imageFilenameIn)
    {
        // * 'velocityMovableBooIn' defaults 'true'
        // * 'velocityMovableBooIn' override default of 'false'
        super(imageFilenameIn, 0, 0, true, 0.0, 0.0);  // Default position of (0,0)
    }
    public Sprite_CollidableYes_MovableYes_Cl(String imageFilenameIn, double positionXIn, double positionYIn, double velocityXIn, double velocityYIn)
    {
        // * 'velocityMovableBooIn' override default of 'false'
        super(imageFilenameIn, positionXIn, positionYIn, true, velocityXIn, velocityYIn);  // Default position of (0,0)
    }

// * tried to move down here, yet had problems since such a core utility, keep it higher up in sbs-cl
//
//n-    public Rectangle2D getBoundary()
//    {
//        // System.out.println("this: positionX_Fld,positionY_Fld,width,height: " + this  + ": " + positionX_Fld + "," + positionY_Fld + "," + width + "," + height);
//        // System.out.println("this: positionX_Fld,positionY_Fld,width,height: " + this  + ": " + positionX_Fld + "," + positionY_Fld);
//        // return new Rectangle2D(positionX_Fld,positionY_Fld,width,height);
//        //y- return new Rectangle2D(positionX_Fld, positionY_Fld, iconImage_Fld.getWidth(), iconImage_Fld.getHeight());
//        return new Rectangle2D(getPositionX(), getPositionY(), getImage().getWidth(), getImage().getHeight());
//    }

    public boolean colliding(SpriteCore_ClAb s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public void addVelocity(double x, double y)
    {
//        velocityX_Fld += x;
//        velocityY_Fld += y;
        setVelocity(getVelocityX()+x, getVelocityY()+y);
    }
    public void addVelocityPrev(double x, double y)
    {
//        velocityX_Prev_Fld += x;
//        velocityY_Prev_Fld += y;
        setVelocityPrev(getVelocityXPrev()+x,getVelocityYPrev()+y);
    }
    public void updateMove(double time)
    {
        setPosition(getPositionX() + (getVelocityX() * time), getPositionY() + (getVelocityY() * time));
    }

}
