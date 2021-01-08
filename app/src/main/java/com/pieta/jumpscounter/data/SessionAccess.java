package com.pieta.jumpscounter.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SessionAccess {
    @Query("SELECT * FROM Session ORDER BY timestamp DESC LIMIT 1;")
    Session getLastSession();

//    @Query("SELECT jumps, duration FROM Session WHERE timestamp > DATE('now', 'localtime', 'start of day') - 604800")
//    List<Session> getLastWeekSessions();
//
//    @Query("SELECT jumps, duration FROM Session WHERE timestamp > DATE('now', 'localtime', 'start of day') - 2678400")
//    List<Session> getLastMonthSessions();
//
//    @Query("SELECT jumps, duration FROM Session WHERE timestamp > DATE('now', 'localtime', 'start of day') - 31622400")
//    List<Session> getLastYearSessions();

    @Insert
    void insert(Session session);
}
