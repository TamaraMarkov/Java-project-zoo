package graphics;

/** Class that define animal behavior
 *  @author - Leah Brodsky
 *   @author - Ziv Gimani
 */
public interface IAnimalBehavior {
    //get animal name
    public String getAnimalName();
    //get animal size
    public int getSize();
    public void eatInc();
    //number of eaten objects
    public int getEatCount();
    //if the location changed
    public boolean getChanges();
    //set changes
    public void setChanges (boolean state);
    //into waiting state
    public void setSuspended();
    //out of waiting state
    public void setResumed();
}
