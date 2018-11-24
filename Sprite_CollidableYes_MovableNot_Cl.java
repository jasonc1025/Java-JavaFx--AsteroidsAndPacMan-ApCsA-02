package AsteroidsAndPacMan_Package;

//y- class IconBriefcase_Cl extends SpriteCore_ClAb implements Collidable_If, Movable_If {
class Sprite_CollidableYes_MovableNot_Cl extends SpriteCore_ClAb implements Collidable_If {

    // * Constructors
    //
    public Sprite_CollidableYes_MovableNot_Cl()
    {
        // [jwc] this("earth.png", 0, 0);  // Default position of (250,250)
        super("/images/earth.png", 0, 0, false, 0.0, 0.0);  // Default position of (250,250)
    }
    public Sprite_CollidableYes_MovableNot_Cl(String imageFilenameIn)
    {
        super(imageFilenameIn, 0, 0, false, 0.0, 0.0);  // Default position of (0,0)
    }
    public Sprite_CollidableYes_MovableNot_Cl(String imageFilenameIn, double positionXIn, double positionYIn)
    {
        super(imageFilenameIn, positionXIn, positionYIn, false, 0.0, 0.0);  // Default position of (0,0)
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
//    public boolean colliding(Sprite_CollidableYes_MovableNot_Cl s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

}
