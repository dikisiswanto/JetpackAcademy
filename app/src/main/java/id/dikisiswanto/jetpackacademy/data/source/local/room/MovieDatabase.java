package id.dikisiswanto.jetpackacademy.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

@Database(entities = MovieEntity.class, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

	private static final Object sLock = new Object();
	private static MovieDatabase INSTANCE;

	public static MovieDatabase getInstance(Context context) {
		synchronized (sLock) {
			if (INSTANCE == null) {
				INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movies.db").build();
			}
			return INSTANCE;
		}
	}

	public abstract MovieDao movieDao();
}
