package org.tp4.delete.model;

public class DeleteRequest {
    private String path;

    public DeleteRequest(){}

    public DeleteRequest(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
