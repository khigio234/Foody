package com.khigio234.pc.core.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by PC on 8/1/2016.
 */
public abstract class Entity {

    //region Properties

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

    //region Constructors

    public Entity() {
    }

    public Entity(Date createdAt, Date updatedAt, Date deletedAt, boolean isDeleted) {
        mCreatedAt = createdAt;
        mUpdatedAt = updatedAt;
        mDeletedAt = deletedAt;
        mIsDeleted = isDeleted;
    }

    //endregion

    //region toString

    @Override
    public String toString() {
        return "Entity{" +
                "mCreatedAt=" + mCreatedAt +
                ", mUpdatedAt=" + mUpdatedAt +
                ", mDeletedAt=" + mDeletedAt +
                ", mIsDeleted=" + mIsDeleted +
                '}';
    }

    //endregion

    //region IBuilder interface

    public interface IBuilder<T> {

        T build();

    }

    //endregion

}
