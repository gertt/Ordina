package com.ordinefacile.root.ordinefacile.data.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by user on 4/26/2018.
 */

@DatabaseTable(tableName = PushHistory.TABLE_PUSH_HISTORY) public class PushHistory {

    public static final String TABLE_PUSH_HISTORY = "History";


    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_TITTLE   = "tittle";
    public static final String FIELD_DESCRIPTION   = "description";
    public static final String FIELD_PRICE   = "price";


    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true, unique = true)  private int id;

    @DatabaseField(columnName = FIELD_TITTLE)  private String name;

    @DatabaseField(columnName = FIELD_DESCRIPTION)  private String descriptions;

    @DatabaseField(columnName = FIELD_PRICE)  private String price;


    public PushHistory() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    public void setmId(int id) {
        this.id = id;
    }

    public void setmName(String name) {
        this.name = name;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void set(String price) {
        this.price = price;
    }

    /** Getters **/

    public static String getFieldNameId() {
        return FIELD_NAME_ID;
    }

    public static String getFieldTittle() {
        return FIELD_TITTLE;
    }

    public static String getFieldDescription () {
        return FIELD_DESCRIPTION;
    }

    public static String getFieldPrice() {
        return FIELD_PRICE;
    }


    /** Setters **/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getPrice() {
        return price;
    }

}
