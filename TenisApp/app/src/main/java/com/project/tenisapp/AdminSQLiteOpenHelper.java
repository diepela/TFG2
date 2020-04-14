package com.project.tenisapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(id integer primary key, username text, nombre text, brazo boolean)");
        db.execSQL("create table golpes(idGolpe integer primary key, idUsuario integer, tipoGolpe integer, fecha text, hora text)");
        db.execSQL("create table datosgolpes(idgolpe integer primary key, timestamp integer, ACC_X double, ACC_Y double, ACC_Z double, GYR_X double, GYR_Y double, GYR_Z double," +
                    "MAG_X double, MAG_Y double, MAG_Z double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
