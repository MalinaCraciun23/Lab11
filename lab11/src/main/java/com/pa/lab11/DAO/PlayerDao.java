/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.lab11.DAO;

import com.pa.lab11.DTO.Player;
import com.pa.lab11.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mally
 */
public class PlayerDao {

    private Player extractPlayerFromResultSet(ResultSet rs) throws SQLException {
        Player player = new Player();
        player.setId(rs.getInt("id"));
        player.setName(rs.getString("name"));
        player.setAge(rs.getInt("age"));
        return player;
    }

    public Player getPlayer(int id) {
        Connection connection = Database.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM player WHERE id=" + id);
            if (rs.next()) {
                return extractPlayerFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Set<Player> getAllPlayers() {
        Connection connection = Database.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM player");
            Set<Player> players = new HashSet();
            while (rs.next()) {
                Player player = extractPlayerFromResultSet(rs);
                players.add(player);
            }
            return players;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean insertPlayer(Player player) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO player VALUES (NULL, ?, ?)");
            ps.setString(1, player.getName());
            ps.setInt(2, player.getAge());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updatePlayer(Player player) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE player SET name=?, age=? WHERE id=?");
            ps.setString(1, player.getName());
            ps.setInt(2, player.getAge());
            ps.setInt(3, player.getId());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deletePlayer(int id) {
        Connection connection = Database.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM player WHERE id=" + id);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
