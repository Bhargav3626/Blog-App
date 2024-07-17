package com.sunbase.dao.impl;

import com.sunbase.dao.BlogDAO;
import com.sunbase.models.Blog;
import com.sunbase.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl implements BlogDAO {
    private Connection connection;
    private DatabaseUtil dbUtil;

    private static final String SQL_CREATE = "INSERT INTO `Blogs` (`title`, `content`, `image_video_path`, `author_id`) VALUES (?, ?, ?, ?)";
    private static final String SQL_GET_BY_ID = "SELECT * FROM `Blogs` WHERE `id` = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM `Blogs`";
    private static final String SQL_UPDATE = "UPDATE `Blogs` SET `title` = ?, `content` = ?, `image_video_path` = ? WHERE `id` = ?";
    private static final String SQL_DELETE = "DELETE FROM `Blogs` WHERE `id` = ?";
    private static final String SQL_GET_BY_AUTHOR = "SELECT * FROM `Blogs` WHERE `author_id` = ?";
    private static final String SQL_GET_BY_TITLE = "SELECT * FROM `Blogs` WHERE `title` LIKE ?";
    private static final String SQL_GET_BY_DATE = "SELECT * FROM `Blogs` WHERE `created_at` LIKE ?";

    public BlogDAOImpl() {
        dbUtil = new DatabaseUtil();
        this.connection = dbUtil.getConnection();
    }

    private Blog extractBlogFromResultSet(ResultSet rs) throws SQLException {
        return new Blog(rs.getInt("id"), rs.getString("title"),
                        rs.getString("content"), rs.getString("image_video_path"),
                        rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"),
                        rs.getInt("author_id"));
    }

    @Override
    public boolean createBlog(Blog blog) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_CREATE)) {
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getImageVideoPath());
            pstmt.setInt(4, blog.getAuthorId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Blog getBlogById(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_ID)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractBlogFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                blogs.add(extractBlogFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public boolean updateBlog(Blog blog) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE)) {
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getImageVideoPath());
            pstmt.setInt(4, blog.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBlog(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Blog> getBlogsByAuthor(int authorId) {
        List<Blog> blogs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_AUTHOR)) {
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                blogs.add(extractBlogFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public List<Blog> getBlogsByTitle(String title) {
        List<Blog> blogs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_TITLE)) {
            pstmt.setString(1, "%" + title + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                blogs.add(extractBlogFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public List<Blog> getBlogsByDate(String date) {
        List<Blog> blogs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_DATE)) {
            pstmt.setString(1, "%" + date + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                blogs.add(extractBlogFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
}
