package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Post;
import model.dao.PostDAO;
/**
 * �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 API�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕黎귛뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�뙓�벝�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦怨ㅼ삕 �뜝�떎�뙋�삕 �겢�뜝�룞�삕�뜝�룞�삕.
 * UserDAO�뜝�룞�삕 �뜝�떛�슱�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떢釉앹삕�뜝�떛�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�뙗�뼲�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�벝�삕�뜝�룞�삕 �뜝�떦紐뚯삕,
 * �뜝�룞�삕�뜝�룞�삕�뜝�떢釉앹삕�뜝�떛�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떢�벝�삕�뜝�룞�삕 �뜝�떛�슱�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떬�뙋�삕.
 * �뜝�룞�삕�뜝�룞�삕�뜝�떦�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜎�댙�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�떦�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕 �겢�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 
 * �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕 �뜝�뙇�뙋�삕.
 */
public class PostManager {
	private static PostManager post = new PostManager();
	private PostDAO postDAO;

	private PostManager() {
		try {
			postDAO = new PostDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static PostManager getInstance() {
		return post;
	}
	
	public int create(Post post) throws SQLException {
		return postDAO.create(post);
	}

	public int update(Post post) throws SQLException, PostNotFoundException {
		return postDAO.update(post);
	}	

	public int remove(int postId) throws SQLException, PostNotFoundException {
		return postDAO.remove(postId);
	}

	public Post findPost(int postId)
		throws SQLException, PostNotFoundException {
		Post post = postDAO.findPost(postId);
		
		if (post == null) {
			throw new PostNotFoundException(postId + "는 존재하지 않는  포스트입니다.");
		}		
		return post;
	}

	public List<Post> findPostList() throws SQLException {
			return postDAO.findPostList();
	}
	
	public List<Post> findPostListUseCategory(String cName) throws SQLException {
		return postDAO.findPostListUseCategory(cName);
}
	
	public PostDAO getPostDAO() {
		return this.postDAO;
	}
	
	public void increasePostView(Post post) throws SQLException {
		postDAO.increasePostView(post);
	}
	
	public String getPostUserNickName (int userId) throws SQLException {
		
		return postDAO.findUserNickNameByUserId(userId);
	}
	
	public String getImgUrl(int postId) throws SQLException {
		Post post = postDAO.findPost(postId);

		return post.getImgUrl();
	}
	
	public List<Post> findMyPostList(int userId) throws SQLException {
		return postDAO.findMyPostList(userId);
	}

	
}
