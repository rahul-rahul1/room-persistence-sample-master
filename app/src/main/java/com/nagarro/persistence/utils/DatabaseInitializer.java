package com.nagarro.persistence.utils;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nagarro.persistence.database.AppDatabase;
import com.nagarro.persistence.entity.User;

import java.util.List;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull String firstNAme, @NonNull String LastName, @NonNull int age, @NonNull final AppDatabase db) {


        PopulateDbAsync task = new PopulateDbAsync(firstNAme, LastName, age, db);
        task.execute();

        //  populateWithTestData(firstNAme,LastName,age,db);

    }

    public static void populateAsyncdelte(@NonNull int uID, @NonNull final AppDatabase db) {


        populateItemTestDataDelete(uID, db);

    }
/*

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }
*/

    private static User addUser(final AppDatabase db, User user) {
        db.userDao().insertAll(user);
        return user;
    }

    private static User deleteUser(final AppDatabase db, User deleteUser) {
        db.userDao().delete(deleteUser);
        return deleteUser;
    }

    private static void populateItemTestDataDelete(int uid, AppDatabase db) {
        User user = new User();
        user.setUid(uid);
        deleteUser(db, user);
        List<User> userList = db.userDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count Delete: " + userList.size());
    }

    private static void populateWithTestData(String FirstNAme, String LastNAme, int age, AppDatabase db) {
        User user = new User();
        user.setFirstName(FirstNAme);
        user.setLastName(LastNAme);
        user.setAge(age);
        addUser(db, user);

        List<User> userList = db.userDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + userList.size());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        String mFirstName, mLastName;
        int mAge;

        PopulateDbAsync(String firstNAme, String LastNAme, int age, AppDatabase db) {
            mDb = db;
            mFirstName = firstNAme;
            mLastName = LastNAme;
            mAge = age;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mFirstName, mLastName, mAge, mDb);

            return null;
        }

    }
}
