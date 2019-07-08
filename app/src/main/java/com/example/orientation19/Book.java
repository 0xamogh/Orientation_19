
package com.example.orientation19;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("book_id")
    @Expose
    private Integer bookId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("department")
    @Expose
    private String department;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
