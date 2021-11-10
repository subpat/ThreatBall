package developer.subhadeep.threatball.model.datastore;


/**
 *
 * <h1> Data </h1>
 *
 * <p> This interface lists the must have methods in our {@link GameData} class. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

public interface Data {

    /**
     * This method signature allows to access the stored high score.
     *
     * @return A non negative integer value.
     */
    int getHighScore ();


    /**
     * This default method creates most basic behavior expected of a class implementing this interface.
     *
     * @return A non null object of an Anonymous Class that implements this interface.
     */
    static Data getDefaultObject() {
        return new Data() {
            @Override public int getHighScore() { return 0; }
        };
    }


}
