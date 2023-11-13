package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.HashedPassword;
import org.kainos.ea.team2.client.AuthenticationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAuthenticationSource implements IAuthenticationSource {
    /**
     * Attempts to get the credentials for a user in the database
     * @param username the username to lookup
     * @return HashedPassword object
     * @throws AuthenticationException thrown if there was an error accessing the data
     */
    @Override
    public HashedPassword getHashedPasswordForUser(String username) throws AuthenticationException {
        String SQL = "SELECT password_hash, password_salt, password_hash_iterations FROM Users WHERE email = ?";

        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new HashedPassword(
                        rs.getBytes("password_hash"),
                        rs.getBytes("password_salt"),
                        rs.getInt("password_hash_iterations")
                );
            }

            return null;
        } catch (SQLException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}
