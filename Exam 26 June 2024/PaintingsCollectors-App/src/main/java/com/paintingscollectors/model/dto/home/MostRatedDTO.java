package com.paintingscollectors.model.dto.home;

public class MostRatedDTO {
    private String name;

    private String author;

    private long votes;

    public MostRatedDTO() {
    }

    public String getName() {
        return name;
    }

    public MostRatedDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MostRatedDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public long getVotes() {
        return votes;
    }

    public MostRatedDTO setVotes(long votes) {
        this.votes = votes;
        return this;
    }
}
