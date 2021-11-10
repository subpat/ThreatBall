package developer.subhadeep.threatball.viewmodel.support;

import java.util.ArrayList;

import developer.subhadeep.threatball.model.LocalDataStore;
import developer.subhadeep.threatball.model.datastore.Data;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class FakeDataStore implements LocalDataStore {

    private ArrayList<Data> dataArrayList;

    public FakeDataStore() {
        dataArrayList = new ArrayList<>();
    }

    @Override
    public Single<Data> store(Data data) {
        dataArrayList.add(data);
        return Single.just(data);
    }

    @Override
    public Flowable<Data> retrieve() {
        return Flowable.fromIterable(dataArrayList);
    }

}
