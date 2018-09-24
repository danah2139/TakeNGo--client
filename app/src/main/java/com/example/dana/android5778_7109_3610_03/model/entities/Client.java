package com.example.dana.android5778_7109_3610_03.model.entities;

/**
 * Created by DELL on 11 נובמבר 2017.
 */

public class Client {
    private int id;
    private String lastName;
    private String firstName;
    private int phoneNum;
    private String eMail;
    private int creditCard;

    public Client() {
        this.lastName = "";
        this.firstName = "";
        this.id = 0;
        this.phoneNum = 0;
        this.eMail = "";
        this.creditCard = 0;
    }

    public Client(String lastName, String firstName, int id, int phoneNum, String eMail, int creditCard) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        this.phoneNum = phoneNum;
        this.eMail = eMail;
        this.creditCard = creditCard;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Client{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                ", phoneNum=" + phoneNum +
                ", eMail='" + eMail + '\'' +
                ", creditCard=" + creditCard +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (getId() != client.getId()) return false;
        if (getPhoneNum() != client.getPhoneNum()) return false;
        if (getCreditCard() != client.getCreditCard()) return false;
        if (getLastName() != null ? !getLastName().equals(client.getLastName()) : client.getLastName() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(client.getFirstName()) : client.getFirstName() != null)
            return false;
        return geteMail() != null ? geteMail().equals(client.geteMail()) : client.geteMail() == null;

    }

    @Override
    public int hashCode() {
        int result = getLastName() != null ? getLastName().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + getId();
        result = 31 * result + getPhoneNum();
        result = 31 * result + (geteMail() != null ? geteMail().hashCode() : 0);
        result = 31 * result + getCreditCard();
        return result;
    }
}
