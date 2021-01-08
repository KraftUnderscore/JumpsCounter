package com.pieta.jumpscounter.data;

import androidx.room.RoomDatabase;
import androidx.room.Database;

@Database(entities = {Session.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SessionAccess sessionDao();
}
