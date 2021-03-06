package com.khigio234.pc.core.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by PC on 8/30/2016.
 */
public class User extends RealmObject {

    //region Properties

    @PrimaryKey
    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mUserName;

    @SerializedName("password")
    private String mPassword;

    @Expose
    private String mRetypePassword;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("created_at")
    private Date mCreatedAt;

    @SerializedName("updated_at")
    private Date mUpdatedAt;

    @SerializedName("deleted_at")
    private Date mDeletedAt;

    @Expose
    private RealmList<Restaurant> mFavoriteRestaurant;

    //endregion

    //region Getter and Setter

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getRetypePassword() {
        return mRetypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        mRetypePassword = retypePassword;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
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

    public RealmList<Restaurant> getFavoriteRestaurant() {
        return mFavoriteRestaurant;
    }

    public void setFavoriteRestaurant(RealmList<Restaurant> favoriteRestaurant) {
        mFavoriteRestaurant = favoriteRestaurant;
    }

    //endregion

    //region Constructor

    public User() {
        super();
    }

    //endregion

    //region Override methods

    @Override
    public String toString() {
        return "User{" +
                "mId='" + mId + '\'' +
                ", mUserName='" + mUserName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mCreatedAt=" + mCreatedAt +
                ", mUpdatedAt=" + mUpdatedAt +
                ", mDeletedAt=" + mDeletedAt +
                '}';
    }

    //endregion
}
