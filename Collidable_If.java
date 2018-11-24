package AsteroidsAndPacMan_Package;

public interface Collidable_If {

// a core utility, move to 'SpriteCore_ClAb'
//    public Rectangle2D getBoundary();
//    {
//        // System.out.println("this: positionXFld,positionYFld,width,height: " + this  + ": " + positionXFld + "," + positionYFld + "," + width + "," + height);
//        // System.out.println("this: positionXFld,positionYFld,width,height: " + this  + ": " + positionXFld + "," + positionYFld);
//        // return new Rectangle2D(positionXFld,positionYFld,width,height);
//        return new Rectangle2D(positionXFld, positionYFld, iconImageFld.getWidth(), iconImageFld.getHeight());
//    }

    public boolean colliding(SpriteCore_ClAb s);
//    {
//        return s.getBoundary().colliding( this.getBoundary() );
//    }

}
