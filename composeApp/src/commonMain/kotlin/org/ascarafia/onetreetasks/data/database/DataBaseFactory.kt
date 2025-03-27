package org.ascarafia.onetreetasks.data.database

import androidx.room.RoomDatabase

expect class DataBaseFactory {
    fun create(): RoomDatabase.Builder<TaskDatabase>
}