package up.l3info.LostKnight.model.core.miscellaneous;

public abstract class GameObject {
    /**
     * Position of the object
     */
    private int posX;
    private int posY;

    //methods

    /**
     * Returns the character's X position
     * @return X position
     */
    public int getPosX(){ return this.posX; }

    /**
     * Returns the character's Y position
     * @return Y position
     */
    public int getPosY(){ return this.posY; }

    /**
     * Set a new X position
     */
    public void setPosX(int newPosX){ this.posX = newPosX; }

    /**
     * Set a new Y position
     */
    public void setPosY(int newPosY){ this.posY = newPosY; }
}
