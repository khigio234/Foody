package com.khigio234.pc.core.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by PC on 8/1/2016.
 */
public class Restaurant extends RealmObject {

    //region Properties

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("opened_time")
    private String mOpenedTime;

    @SerializedName("closed_time")
    private String mClosedTime;

    @SerializedName("category_id")
    private int mCategoryId;

    @SerializedName("phone_number")
    private String mPhoneNumber;

    @SerializedName("image")
    private String mImage;

    @SerializedName("content")
    private String mContent;

    @SerializedName("created_at")
    private Date mCreatedAt;

    @SerializedName("updated_at")
    private Date mUpdatedAt;

    @SerializedName("delete_at")
    private Date mDeletedAt;

    @SerializedName("is_deleted")
    private boolean mIsDeleted;

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

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getOpenedTime() {
        return mOpenedTime;
    }

    public void setOpenedTime(String openedTime) {
        mOpenedTime = openedTime;
    }

    public String getClosedTime() {
        return mClosedTime;
    }

    public void setClosedTime(String closedTime) {
        mClosedTime = closedTime;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
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
        return mIsDeleted;
    }

    public void setDeleted(boolean deleted) {
        mIsDeleted = deleted;
    }

    //endregion

    //region Constructor


    public Restaurant() {
        super();
    }

    public Restaurant(int id, String name, String address, String openedTime, String closedTime, String phoneNumber, String image, String content, Date createdAt, Date updatedAt, Date deletedAt, boolean isDeleted) {
        mId = id;
        mName = name;
        mAddress = address;
        mOpenedTime = openedTime;
        mClosedTime = closedTime;
        mPhoneNumber = phoneNumber;
        mImage = image;
        mContent = content;
        mCreatedAt = createdAt;
        mUpdatedAt = updatedAt;
        mDeletedAt = deletedAt;
        mIsDeleted = isDeleted;
    }

    //endregion

    //region toString

    @Override
    public String toString() {
        return "Restaurant{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mOpenedTime='" + mOpenedTime + '\'' +
                ", mClosedTime='" + mClosedTime + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }

    //endregion
}
