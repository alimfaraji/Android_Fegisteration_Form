package com.example.hoco.android_fegisteration_form;

/**
 * Created by hoco on 8/10/15.
 */
public class Member {
    String username;
    String password;
    String firstname;
    String lastname;
    String emailAddress;
    String phoneNumber;
    String birthday;

    public Member() {

    }

    public Member(String username, String password, String firstname, String lastname, String emailAddress, String phoneNumber, String birthday) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    static public Member getMemberFromCode(String code) {
        String[] all = code.split(",");
        Member member = new Member(all[0], all[1], all[2], all[3], all[4], all[5], all[6]);
        return member;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String generateCode() {
        return username + "," + password + "," + firstname + "," + lastname + "," + emailAddress + "," + phoneNumber
                + " , " + birthday;
    }
}
