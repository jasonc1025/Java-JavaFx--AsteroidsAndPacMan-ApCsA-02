package AsteroidsAndPacMan_Package;

//y- class IconBriefcase_Cl extends SpriteCore_ClAb implements Collidable_If, Movable_If {
class Sprite_CollidableNot_MovableNot_Cl extends SpriteCore_ClAb {

    // * Constructors
    //
    public Sprite_CollidableNot_MovableNot_Cl()
    {
        // [jwc] this("earth.png", 0, 0);  // Default position of (250,250)
        super("/images/earth.png", 0, 0, false, 0.0, 0.0);  // Default position of (250,250)
    }
    public Sprite_CollidableNot_MovableNot_Cl(String imageFilenameIn)
    {
        super(imageFilenameIn, 0, 0, false, 0.0, 0.0);  // Default position of (0,0)
    }
    public Sprite_CollidableNot_MovableNot_Cl(String imageFilenameIn, double positionXIn, double positionYIn)
    {
        super(imageFilenameIn, positionXIn, positionYIn, false, 0.0, 0.0);  // Default position of (0,0)
    }

}
