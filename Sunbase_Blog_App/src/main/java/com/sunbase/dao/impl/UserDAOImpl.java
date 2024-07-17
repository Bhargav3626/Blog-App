package com.sunbase.dao.impl;

import com.sunbase.dao.UserDAO;
import com.sunbase.models.User;
import com.sunbase.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private Connection connection;
    private DatabaseUtil dbUtil;

    private static final String SQL_REGISTER = "INSERT INTO `Users` (`username`, `email`, `password`, `role`) VALUES (?, ?, ?, ?)";
    private static final String SQL_LOGIN = "SELECT * FROM `Users` WHERE `username` = ? AND `password` = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM `Users` WHERE `id` = ?";
    private static final String SQL_GET_BY_USERNAME = "SELECT * FROM `Users` WHERE `username` = ?";
    private static final String SQL_UPDATE = "UPDATE `Users` SET `username` = ?, `email` = ?, `password` = ?, `role` = ? WHERE `id` = ?";
    private static final String SQL_DELETE = "DELETE FROM `Users` WHERE `id` = ?";
    private static final String SQL_GET_BY_EMAIL = "SELECT * FROM `Users` WHERE `email` = ?";

    
    
    public UserDAOImpl() {
    	dbUtil = new DatabaseUtil();
        this.connection = dbUtil.getConnection();
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("email"), rs.getString("password"),
                        rs.getString("role"));
    }

    @Override
    public boolean registerUser(User user) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_REGISTER)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User loginUser(String username, String password) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_LOGIN)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_ID)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_USERNAME)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public User getUserByEmail(String email) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_EMAIL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
