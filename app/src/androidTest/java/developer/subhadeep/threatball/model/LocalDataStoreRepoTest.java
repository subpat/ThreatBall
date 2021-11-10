package developer.subhadeep.threatball.model;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.datastore.rxjava3.RxDataStore;
import androidx.datastore.rxjava3.RxDataStoreBuilder;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import developer.subhadeep.threatball.AppConstants;
import developer.subhadeep.threatball.model.datastore.Data;
import developer.subhadeep.threatball.model.datastore.GameData;
import developer.subhadeep.threatball.model.datastore.DataSerializer;

@RunWith(AndroidJUnit4.class)
public class LocalDataStoreRepoTest {

    private LocalDataStoreRepo localDataStoreRepo;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        DataSerializer dataSerializer = new DataSerializer();
        RxDataStore<Data> rxDataStore = new RxDataStoreBuilder<>(
                context, AppConstants.TEST_FILE_NAME, dataSerializer
        ).build();
        localDataStoreRepo = new LocalDataStoreRepo(rxDataStore);
    }

    @After
    public void tearDown() {
        localDataStoreRepo = null;
    }

    @Test
    public void storeAndRetrieve () {
        localDataStoreRepo.store(new GameData(AppConstants.SCORE_TEST_VAL));
        Data result = localDataStoreRepo.retrieve().blockingFirst();
        assertTrue(AppConstants.SCORE_TEST_VAL == result.getHighScore());
    }



}