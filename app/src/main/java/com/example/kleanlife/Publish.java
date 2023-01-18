package com.example.kleanlife;

public class Publish {
    String name;
    String propertyId;
    String roomCount;
    String bathroomCount;
    String flooringType;
    String location;
    byte[] image;
    String cost;
    String date;

    public Publish(String name, String propertyId, String roomCount, String bathroomCount,
                   String flooringType, String location, byte[] image, String cost, String date) {
        this.name = name;
        this.propertyId = propertyId;
        this.roomCount = roomCount;
        this.bathroomCount = bathroomCount;
        this.flooringType = flooringType;
        this.location = location;
        this.image = image;
        this.cost = cost;
        this.date = date;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(String roomCount) {
        this.roomCount = roomCount;
    }

    public String getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(String bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public String getFlooringType() {
        return flooringType;
    }

    public void setFlooringType(String flooringType) {
        this.flooringType = flooringType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
