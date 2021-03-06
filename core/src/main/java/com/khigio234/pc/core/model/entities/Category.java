package com.khigio234.pc.core.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by PC on 8/1/2016.
 */
public class Category extends RealmObject {

    //region Properties

    @SerializedName("id")
    @PrimaryKey
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("image")
    private String mImage;

    @SerializedName("created_at")
    private Date mCreatedAt;

    @SerializedName("updated_at")
    private Date mUpdatedAt;

    @SerializedName("deleted_at")
    private Date mDeletedAt;

    //endregion

    //region Getter and Setter

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }

    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        mDeletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return mDeletedAt != null;
    }

    //endregion

    //region Constructor


    public Category() {
        super();
    }

    public Category(int id, String name, String image, Date createdAt, Date updatedAt, Date deletedAt) {
        mId = id;
        mName = name;
        mImage = image;
        mCreatedAt = createdAt;
        mUpdatedAt = updatedAt;
        mDeletedAt = deletedAt;
    }

    //endregion

    //region toString

    @Override
    public String toString() {
        return "Category{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mCreatedAt=" + mCreatedAt +
                ", mUpdatedAt=" + mUpdatedAt +
                ", mDeletedAt=" + mDeletedAt +
                '}';
    }

    //endregion
}
