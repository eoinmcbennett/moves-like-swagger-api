package org.kainos.ea.team2.cli;

public enum UserRole {
    User(1), Admin(2);

    private int userRole;

    private static final String[] roleNames = new String[]{"User","Admin"};

    private UserRole(int userRole){
        this.userRole = userRole;
    }

    public int getUserRole() {
        return this.userRole;
    }

    public static UserRole getUserRoleFromId(int id){
        switch (id){
            case 1: return UserRole.User;
            case 2: return UserRole.Admin;
        }

        return null;
    }

    @Override
    public String toString() {
        return roleNames[this.userRole -1];
    }
}
