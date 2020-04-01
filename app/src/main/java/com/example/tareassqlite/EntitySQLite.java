package com.example.tareassqlite;

public class EntitySQLite {
    private long _id;
    private String category;
    private String summary;
    private String description;

    public EntitySQLite(long _id, String category, String summary, String description) {
        this._id = _id;
        this.category = category;
        this.summary = summary;
        this.description = description;
    }
    public EntitySQLite() {
        this._id = 0;
        this.category = "";
        this.summary = "";
        this.description = "";
    }
    public EntitySQLite(EntitySQLite object) {
        this._id = object._id;
        this.category = object.category;
        this.summary = object.summary;
        this.description = object.description;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EntitySQLite{" +
                "_id=" + _id +
                ", category=" + category +
                ", summary="+summary+
                ", description="+description+
                '}';
    }
}
