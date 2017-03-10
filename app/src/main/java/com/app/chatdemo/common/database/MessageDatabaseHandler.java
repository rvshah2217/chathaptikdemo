package com.app.chatdemo.common.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.chatdemo.model.ChatDetailsModel;
import com.app.chatdemo.model.FavTotalModel;
import com.app.chatdemo.model.MessagesDisplayModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapna on 5/3/17.
 */

public class MessageDatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "messageManager.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    public static final String TABLE_NAME = "MessageManager";
    protected static final String ID = "id";
    protected static final String USER_NAME = "user_name";
    protected static final String MESSAGES = "messages";
    protected static final String IMAGE = "image";
    protected static final String NAME = "name";
    protected static final String MYFAV = "fav";
    protected static final String DATE = "date";
    protected static final String ME = "me";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            +ID + " INTEGER PRIMARY KEY autoincrement, "
            +USER_NAME + " TEXT,"
            +MESSAGES + " TEXT,"
            +IMAGE + " TEXT,"
            +MYFAV + " INTEGER,"
            +DATE + " STRING,"
            +ME + " TEXT,"
            +NAME + " TEXT)";
    private String TAG="MessageDatabaseHandler";


    public MessageDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MessageDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public void insertData(MessagesDisplayModel messagesDisplayModel) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, messagesDisplayModel.getUserName());
        contentValues.put(MESSAGES, messagesDisplayModel.getMessages());
        contentValues.put(NAME, messagesDisplayModel.getName());
        contentValues.put(IMAGE, messagesDisplayModel.getImage());
        contentValues.put(MYFAV, messagesDisplayModel.getMyFav());
        contentValues.put(DATE, messagesDisplayModel.getTimeStamp());
        contentValues.put(ME, messagesDisplayModel.getMe());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public ArrayList<MessagesDisplayModel> getAllNotificationData()
    {
        database=this.getReadableDatabase();
        Cursor cursor=database.query(TABLE_NAME,null,null,null,null,null,null);

        ArrayList<MessagesDisplayModel> notificationModelArrayList=new ArrayList<>();

        MessagesDisplayModel messagesDisplayModel;
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();

            messagesDisplayModel = new MessagesDisplayModel();
            messagesDisplayModel.setID(cursor.getString(0));
            messagesDisplayModel.setUserName(cursor.getString(1));
            messagesDisplayModel.setMessages(cursor.getString(2));
            messagesDisplayModel.setImage(cursor.getString(3));
            messagesDisplayModel.setMyFav(cursor.getInt(4));
            messagesDisplayModel.setTimeStamp(cursor.getString(5));
            messagesDisplayModel.setMe(cursor.getInt(6));
            messagesDisplayModel.setName(cursor.getString(7));
            notificationModelArrayList.add(messagesDisplayModel);
        }
        cursor.close();
       // database.close();
        // NotificationStore.setAllNotificationData(notificationModelArrayList);

        return notificationModelArrayList;
    }

    public void dropTableIfExists() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }
    public void updateNotificationData(MessagesDisplayModel messagesDisplayModel) {
        database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, messagesDisplayModel.getUserName());
        contentValues.put(MESSAGES, messagesDisplayModel.getMessages());
        contentValues.put(NAME, messagesDisplayModel.getName());
        contentValues.put(IMAGE, messagesDisplayModel.getImage());
        contentValues.put(MYFAV, messagesDisplayModel.getMyFav());
        contentValues.put(DATE, messagesDisplayModel.getTimeStamp());
        contentValues.put(ME, messagesDisplayModel.getMe());

        database.update(TABLE_NAME, contentValues,ID + " = ?", new String[]{messagesDisplayModel.getID()});
        database.close();
    }

    public List<ChatDetailsModel> getOverviewOfApis() {
        List<ChatDetailsModel> apiOverviewDetailsList = new ArrayList<>();
        database=getReadableDatabase();
        Log.i(TAG, "getOverviewOfApis: ");
        String mSelectComplexQuery = "SELECT " +IMAGE +","+NAME +","

                + " COUNT(" +"distinct " +MESSAGES + ")"
                + " FROM " + TABLE_NAME + " GROUP BY " +NAME;

        Cursor cursor = database.rawQuery(mSelectComplexQuery, null);
        try {
            if(cursor !=null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    ChatDetailsModel overviewDetails = new ChatDetailsModel();
                    overviewDetails.setImages(cursor.getString(0));
                    overviewDetails.setUserName(cursor.getString(1));
                    overviewDetails.setTotalMessages(cursor.getInt(2));
                    apiOverviewDetailsList.add(overviewDetails);
                    cursor.moveToNext();
                }

            }
        } finally {
            cursor.close();
            database.close();
        }
        return apiOverviewDetailsList;
    }


    public List<FavTotalModel> getTotalFavMessages() {

        List<FavTotalModel> favTotalModelArrayList = new ArrayList<>();

        database=getReadableDatabase();
        Log.i(TAG, "getOverviewOfApis: ");


        String mSelectComplexQuery = "SELECT " +NAME+","

                + " COUNT (" +MESSAGES + ")"
                + " FROM " + TABLE_NAME + " WHERE " +MYFAV+" = "+1 +" GROUP BY "+NAME;

        Cursor cursor = database.rawQuery(mSelectComplexQuery, null);
        try {
            if(cursor !=null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    FavTotalModel favTotalModel = new FavTotalModel();
                    favTotalModel.setUserName(cursor.getString(0));
                    favTotalModel.setFavTotal(cursor.getInt(1));
                    favTotalModelArrayList.add(favTotalModel);
                    cursor.moveToNext();
                }

            }
        } finally {
            cursor.close();
            database.close();
        }

        return favTotalModelArrayList;
    }
}