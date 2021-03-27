package Helpers;

import DTO.UsersDTO;

public class UsersControl {

    private UsersDTO currentUserData;
    private boolean isAdmUser;

    public UsersControl() {
    }

    public UsersDTO getCurrentUserData() {
        return currentUserData;
    }

    public void setCurrentUserData(UsersDTO currentUserData) {
        this.currentUserData = currentUserData;
    }

    public boolean isAdmUser() {
        return isAdmUser;
    }

    public void setAdmUser(boolean admUser) {
        isAdmUser = admUser;
    }
}
