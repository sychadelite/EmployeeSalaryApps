package com.barjlazuardi.employeesalaryapps;

public class UserProfile {
    // Shown on Firebase Realtime Database
    public String userAddress;
    public String userBirthday;
    public String userEmail;
    public String userFullname;
    public String userGender;
    public String userPhoneNumber;
    public String userUsername;

    public UserProfile(String userAddress, String userBirthday, String userEmail, String userFullname, String userGender, String userPhoneNumber, String userUsername) {
        this.userAddress = userAddress;
        this.userBirthday = userBirthday;
        this.userEmail = userEmail;
        this.userFullname = userFullname;
        this.userGender = userGender;
        this.userPhoneNumber = userPhoneNumber;
        this.userUsername = userUsername;
    }
}
