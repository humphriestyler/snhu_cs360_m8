package com.example.project_three;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the SQLite database for user accounts and inventory items.
 */
public class Database extends SQLiteOpenHelper {

    // Sets the database name and version
    private static final String DATABASE_NAME = "project_three_user_accounts.db";
    private static final int DATABASE_VERSION = 1;

    // Table and columns for the login system
    public static final String USERS_TABLE = "users";
    public static final String USERS_ID_COLUMN = "id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";

    // Table and columns for the inventory system
    public static final String INVENTORY_TABLE = "inventory";
    public static final String ITEM_ID_COLUMN = "item_id";
    public static final String ITEM_NAME_COLUMN = "item_name";
    public static final String ITEM_QUANTITY_COLUMN = "item_quantity";

    // SQL statement to create the 'users' table for the login system
    private static final String TABLE_CREATE =
            "CREATE TABLE " + USERS_TABLE + " (" +
                    USERS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USERNAME_COLUMN + " TEXT, " +
                    PASSWORD_COLUMN + " TEXT);";

    // SQL statement to create the 'inventory' table for the inventory system
    private static final String TABLE_INVENTORY_CREATE =
            "CREATE TABLE " + INVENTORY_TABLE + " (" +
                    ITEM_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ITEM_NAME_COLUMN + " TEXT, " +
                    ITEM_QUANTITY_COLUMN + " INTEGER);";

    /**
     * Creates a new Database instance.
     *
     * @param context The application context.
     */
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute SQL statements to create the 'users' and 'inventory' tables
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_INVENTORY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing 'users' table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        // Recreate the database tables
        onCreate(db);
    }

    /**
     * Inserts a new account in the users table.
     *
     * @param username Username
     * @param password Password
     * @return The row ID of the new inserted user. -1 if insertion fails.
     */
    public long insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = -1; // Default value if insertion fails
        ContentValues values = new ContentValues();
        values.put(USERNAME_COLUMN, username);
        values.put(PASSWORD_COLUMN, password);
        result = db.insert(USERS_TABLE, null, values);
        db.close();
        return result;
    }

    /**
     * Checks if a user exists in the table.
     *
     * @param username Username
     * @param password Password
     * @return True if the user exists, false if it doesn't.
     */
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        String[] columns = {USERS_ID_COLUMN};
        String selection = USERNAME_COLUMN + " = ?" + " AND " + PASSWORD_COLUMN + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(USERS_TABLE,
                columns, selection, selectionArgs, null, null, null);
        count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    /**
     * Inserts a new item to the inventory
     *
     * @param itemName Item name
     * @param itemQuantity Item quantity
     * @return The row ID of the newly inserted item. -1 if insertion fails.
     */
    public long insertInventoryItem(String itemName, int itemQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = -1; // Default value if insertion fails
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME_COLUMN, itemName);
        values.put(ITEM_QUANTITY_COLUMN, itemQuantity);
        result = db.insert(INVENTORY_TABLE, null, values);
        db.close();
        return result;
    }

    /**
     * Gets a list of items from the inventory.
     *
     * @return A list of all items in the inventory
     */
    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {ITEM_NAME_COLUMN};
        Cursor cursor = db.query(INVENTORY_TABLE,
                columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String itemName =
                        cursor.getString(cursor.getColumnIndex(ITEM_NAME_COLUMN));
                itemNames.add(itemName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemNames;
    }

    /**
     * Gets the item quantity from the inventory.
     *
     * @param itemName Item name
     * @return The quantity of the item. 0 if not found.
     */
    @SuppressLint("Range")
    public int getItemQuantity(String itemName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int itemQuantity = 0; // Initialize with a default value
        String[] columns = {ITEM_QUANTITY_COLUMN};
        String selection = ITEM_NAME_COLUMN + " = ?";
        String[] selectionArgs = {itemName};
        Cursor cursor = db.query(INVENTORY_TABLE,
                columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            itemQuantity = cursor.getInt(cursor.getColumnIndex(ITEM_QUANTITY_COLUMN));
        }
        cursor.close();
        db.close();
        return itemQuantity;
    }

    /**
     * Removes item from the inventory
     *
     * @param itemName Item name
     * @return The number of rows deleted. 1 if successful, 0 if failure.
     */
    public int deleteInventoryItem(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = 0;
        String whereClause = ITEM_NAME_COLUMN + " = ?";
        String[] whereArgs = {itemName};
        rowsDeleted = db.delete(INVENTORY_TABLE, whereClause, whereArgs);
        db.close();
        return rowsDeleted;
    }
}