package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.HashedPassword;
import org.kainos.ea.team2.cli.UserRole;
import org.kainos.ea.team2.client.AuthenticationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MySQL database authentication source implementation.
 */
public class DBAuthenticationSource implements IAuthenticationSource {
    /**
     * Attempts to get the credentials for a user in the database.
     * @param username the username to lookup
     * @return HashedPassword object
     * @throws AuthenticationException thrown on database access error
     */
    @Override
    public HashedPassword getHashedPasswordForUser(final String username)
            throws AuthenticationException {
        String sql = "SELECT "
                + "password_hash, password_salt, password_hash_iterations "
                + "FROM Users WHERE email = ?";

        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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

    @Override
    public UserRole getRoleForUser(String username) throws AuthenticationException {
        String SQL = "SELECT role_id, role_name"
                + " FROM Users WHERE email = ? "
                + " JOIN Roles USING(role_id)";

        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL);
            st.setString(1,username);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                return UserRole.getUserRoleFromId(rs.getInt(1));
            }

            return null;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new AuthenticationException("Failed to get user role!");
        }
    }
}
