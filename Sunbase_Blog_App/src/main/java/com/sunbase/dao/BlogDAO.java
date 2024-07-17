package com.sunbase.dao;

import com.sunbase.models.Blog;
import java.sql.Timestamp;
import java.util.List;

public interface BlogDAO {
    boolean createBlog(Blog blog);
    Blog getBlogById(int id);
    List<Blog> getAllBlogs();
    boolean updateBlog(Blog blog);
    boolean deleteBlog(int id);
    List<Blog> getBlogsByAuthor(int authorId);
    List<Blog> getBlogsByTitle(String title);
    List<Blog> getBlogsByDate(String date);
}
