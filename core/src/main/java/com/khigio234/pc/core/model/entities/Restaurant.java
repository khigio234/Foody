package com.khigio234.pc.core.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmList;
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

    @SerializedName("open_time")
    private String mOpenTime;

    @SerializedName("close_time")
    private String mCloseTime;

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

    @SerializedName("deleted_at")
    private Date mDeletedAt;

    @SerializedName("comments")
    private RealmList<Comment> mComments;

    @SerializedName("category")
    private Category mCategory;

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

    public String getOpenTime() {
        return mOpenTime;
    }

    public void setOpenTime(String openTime) {
        mOpenTime = openTime;
    }

    public String getCloseTime() {
        return mCloseTime;
    }

    public void setCloseTime(String closeTime) {
        mCloseTime = closeTime;
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

    public RealmList<Comment> getComments() {
        return mComments;
    }

    public void setComments(RealmList<Comment> comments) {
        mComments = comments;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public boolean isDeleted() {
        return mDeletedAt != null;
    }

    //endregion

    //region Constructor


    public Restaurant() {
        super();
    }


    //endregion

    //region toString

    @Override
    public String toString() {
        return "Restaurant{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mOpenTime='" + mOpenTime + '\'' +
                ", mCloseTime='" + mCloseTime + '\'' +
                ", mCategoryId=" + mCategoryId +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mCreatedAt=" + mCreatedAt +
                ", mUpdatedAt=" + mUpdatedAt +
                ", mDeletedAt=" + mDeletedAt +
                '}';
    }

    //endregion
}
