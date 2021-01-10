package com.pieta.jumpscounter.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SessionAccess {
    @Query("SELECT * FROM Session ORDER BY timestamp DESC LIMIT 1;")
    Session getLastSession();

    @Query("SELECT jumps FROM Session ORDER BY jumps DESC LIMIT 1;")
    int getMostJumps();

    @Query("SELECT duration FROM Session ORDER BY duration DESC LIMIT 1;")
    float getMostDuration();

    @Query("SELECT (jumps / duration) FROM Session ORDER BY 1 DESC LIMIT 1;")
    float getMostAvg();

    @Query("SELECT * FROM Session WHERE timestamp > DATE('now', 'localtime', 'start of day') - 604800")
    List<Session> getLastWeekSessions();

    @Insert
    void insert(Session session);
}
