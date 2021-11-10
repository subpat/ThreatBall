package developer.subhadeep.threatball.model.datastore;

import androidx.annotation.NonNull;
import androidx.datastore.core.Serializer;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import kotlin.Unit;
import kotlin.coroutines.Continuation;



/**
 *
 * <h1> DataSerializer </h1>
 *
 * <p> This class provides all the necessary methods to convert objects (of classes that
 * implement {@link Data} interface) to bytes for their storage. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

public class DataSerializer implements Serializer <Data> {


    /**
     * This method returns the default value to be returned by other methods in case an error occurs.
     *
     * @return A non null object of an Anonymous Class that implements the {@link Data} interface.
     */
    @Override
    public Data getDefaultValue() {
        return Data.getDefaultObject();
    }


    /**
     * This method unmarshal object from stream.
     *
     * @return A non null object of a class that implements the {@link Data} interface.
     */
    @Override
    public Data readFrom(@NonNull InputStream inputStream, @NonNull Continuation<? super Data> continuation) {
        try {
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            return (Data) ois.readObject();
        } catch (Exception e) {
            System.out.println(getClass().getName() + "::ERROR in readFrom()::" + e.toString());
        }
        return getDefaultValue();
    }


    /**
     * This method is used to create stream from objects.
     *
     * @return A non null object of a class that implements the {@link Data} interface.
     */
    @Override
    public Data writeTo(Data data, @NonNull OutputStream outputStream, @NonNull Continuation<? super Unit> continuation) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            objectOutputStream.close();
            return data;
        } catch (Exception e) {
            System.out.println(getClass().getName() + "::ERROR in writeTo()::" + e.toString());
        }
        return getDefaultValue();
    }

}
