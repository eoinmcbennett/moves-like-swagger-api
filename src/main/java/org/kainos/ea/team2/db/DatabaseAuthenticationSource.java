package org.kainos.ea.team2.db;

import io.fusionauth.jwt.domain.JWT;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.client.AuthenticationException;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAuthenticationSource
    implements IAuthenticationSource{

    @Override
    public JWT authenticate(BasicCredentials credentials) throws AuthenticationException {
        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("")
        } catch (SQLException e){
            throw new AuthenticationException(e.getMessage());
        }
        return null;
    }
}
