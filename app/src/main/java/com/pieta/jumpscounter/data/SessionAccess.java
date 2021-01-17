package com.pieta.jumpscounter.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SessionAccess {
    @Query("SELECT * FROM Session ORDER BY timestamp DESC LIMIT 1;")
    Session getLastSession();

    @Query("SELECT SUM(jumps) FROM Session")
    int getTotalJumps();

    @Query("SELECT COUNT(*) FROM Session")
    int getTotalSessions();

    @Query("SELECT SUM(duration) FROM Session")
    float getTotalDuration();

    @Query("SELECT (jumps / duration) FROM Session ORDER BY 1 DESC LIMIT 1;")
    float getHighestSpeed();

    @Query("SELECT * FROM Session ORDER BY jumps DESC LIMIT 1;")
    Session getBestSession();

    @Insert
    void insert(Session session);
}
