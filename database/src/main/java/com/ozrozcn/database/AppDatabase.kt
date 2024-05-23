package com.ozrozcn.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozrozcn.database.dao.ArticleDao
import com.ozrozcn.database.model.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}