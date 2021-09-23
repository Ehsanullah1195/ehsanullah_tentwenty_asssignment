package com.bytestechno.tentwenty_movie.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.bytestechno.tentwenty_movie.data.models.tickets.Tickets;


@Database(entities = {Tickets.class},
        version = 1,
        exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract TicketsDao ticketDao();
    public static final String DB_NAME = "tentwenty_movies";
    private static RoomDatabase INSTANCE;

    public static synchronized RoomDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
