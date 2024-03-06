package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.post.SearchCategoryController;
import model.Post;
import model.User;

/**
 * 野껊슣�뻻疫뀐옙 �꽴占썹뵳�됵옙占� 占쎌맄占쎈퉸 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞 占쎌삂占쎈씜占쎌뱽 占쎌읈占쎈뼖占쎈릭占쎈뮉 DAO 占쎄깻占쎌삋占쎈뮞
 * POST 占쎈�믭옙�뵠�뇡遺용퓠 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 �빊遺쏙옙, 占쎈땾占쎌젟, 占쎄텣占쎌젫, 野껓옙占쎄퉳 占쎈땾占쎈뻬 
 */
public class PostDAO {
private static final Logger log = LoggerFactory.getLogger(PostDAO.class);
private JDBCUtil jdbcUtil = null;
	
	public PostDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 揶쏆빘猿� 占쎄문占쎄쉐
	}
		
	/**
	 * 野껊슣�뻻疫뀐옙 �꽴占썹뵳占� 占쎈�믭옙�뵠�뇡遺용퓠 占쎄퉱嚥≪뮇�뒲 野껊슣�뻻疫뀐옙 占쎄문占쎄쉐.
	 */
	public int create(Post post) throws SQLException {

		String sql = "INSERT INTO POST VALUES (post_id_seq.nextval, ?, ?, ?, DEFAULT, DEFAULT, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { post.getTitle(), post.getDesc(), post.getImgUrl(),
				post.getStatus(), post.getPrice(), post.getpType(), post.getWriterId(), post.getCategoryId()};	
		System.out.println("sql: " + sql);
		System.out.println("param: " + param);
		for (Object p : param) {
			System.out.println(p);
		}

		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쎈퓠 insert�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟	
		try {
			int result = jdbcUtil.executeUpdate();	// insert �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {	 
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}
		return 0;			
	}

	/**
	 * 疫꿸퀣�덌옙�벥 野껊슣�뻻疫뀐옙 占쎌젟癰귣�占쏙옙 占쎈땾占쎌젟.
	 */
	public int update(Post post) throws SQLException {
		String sql = "UPDATE POST "
					+ "SET title=?, description=?, imageUrl=?, categoryId=?, status=?, price=? "
					+ "WHERE postId=?";
		Object[] param = new Object[]  { post.getTitle(), post.getDesc(), post.getImgUrl(), post.getCategoryId(), 
				post.getStatus(), post.getPrice(), post.getPostId() };			
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil占쎈퓠 update�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;
	}

	/**
	 * 野껊슣�뻻疫뀐옙 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 野껊슣�뻻疫뀐옙占쎌뱽 占쎄텣占쎌젫.
	 */
	public int remove(int postId) throws SQLException {
		String sql = "DELETE FROM POST WHERE postId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil占쎈퓠 delete�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;
	}

	/**
	 * 雅뚯눘堉깍쭪占� 野껊슣�뻻疫뀐옙 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 野껊슣�뻻疫뀐옙 占쎌젟癰귣�占쏙옙 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞占쎈퓠占쎄퐣 筌≪뼚釉� Post 占쎈즲筌롫뗄�뵥 占쎄깻占쎌삋占쎈뮞占쎈퓠 
	 * 占쏙옙占쎌삢占쎈릭占쎈연 獄쏆꼹�넎.
	 */
	public Post findPost(int postId) throws SQLException {
        String sql = "SELECT title, description, imageUrl, createdTime, categoryId, "
        			+ "views, status, price, postType, writerId "
        			+ "FROM POST "
        			+ "WHERE postId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {						// 占쎈린占쎄문 占쎌젟癰귨옙 獄쏆뮄猿�
				Post post = new Post(		// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎈린占쎄문 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
					postId,
					rs.getString("title"),
					rs.getString("description"),
					rs.getString("imageUrl"),
					rs.getDate("createdTime"),
					rs.getInt("categoryId"),
					rs.getInt("views"),
					rs.getString("status"),
					rs.getInt("price"),
					rs.getString("postType"),
					rs.getInt("writerId"));
				return post;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}

	/**
	 * 占쎌읈筌ｏ옙 野껊슣�뻻疫뀐옙 占쎌젟癰귣�占쏙옙 野껓옙占쎄퉳占쎈릭占쎈연 List占쎈퓠 占쏙옙占쎌삢 獄쏉옙 獄쏆꼹�넎
	 */
	public List<Post> findPostList() throws SQLException {
        String sql = "SELECT postId, title, imageUrl, views, status, price, postType, writerId " 
        		   + "FROM POST "
        		   + "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쎈퓠 query�눧占� 占쎄퐬占쎌젟
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈뼄占쎈뻬			
			List<Post> postList = new ArrayList<Post>();	// User占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
			while (rs.next()) {
				Post post = new Post(			// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getString("imageUrl"),
						rs.getInt("views"),
						rs.getString("status"),
						rs.getInt("price"),
						rs.getString("postType"),
						rs.getInt("writerId"));
				postList.add(post);				// List占쎈퓠 User 揶쏆빘猿� 占쏙옙占쎌삢
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}
	
	/**
	 * 占쎌읈筌ｏ옙 野껊슣�뻻疫뀐옙 占쎌젟癰귣�占쏙옙 野껓옙占쎄퉳占쎈릭占쎈연 List占쎈퓠 占쏙옙占쎌삢 獄쏉옙 獄쏆꼹�넎
	 */
	public List<Post> findPostListUseCategory(String cName) throws SQLException {
        String sql = "SELECT postId, title, imageUrl, views, status, price, postType, writerId " 
        		   + "FROM POST p JOIN CATEGORY c ON p.categoryId=c.categoryId "
        		   + "WHERE categoryName=? "
        		   + "ORDER BY postId";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {cName});		// JDBCUtil占쎈퓠 query�눧占� 占쎄퐬占쎌젟
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈뼄占쎈뻬			
			List<Post> postList = new ArrayList<Post>();	// User占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
			while (rs.next()) {
				Post post = new Post(			// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getString("imageUrl"),
						rs.getInt("views"),
						rs.getString("status"),
						rs.getInt("price"),
						rs.getString("postType"),
						rs.getInt("writerId"));
				postList.add(post);				// List占쎈퓠 User 揶쏆빘猿� 占쏙옙占쎌삢
				log.debug(rs.getString("postType"));
			}
			
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}

	public List<Post> SearchPostList(String title) throws SQLException {
		String sql = "SELECT * "
				+ "FROM POST "
				+ "WHERE title LIKE '%"
				+ title
				+ "%'";
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();
			
			while(rs.next()) {
				Post post = new Post(
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getString("imageUrl"), //지수가 추가
						rs.getInt("views"),
						rs.getString("status"),
						rs.getInt("price"),
						rs.getString("postType"),
						rs.getInt("writerId"));
				postList.add(post);	
			}
			
			return postList;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
	
	public void increasePostView(Post post) throws SQLException {
		String sql = "UPDATE POST "
				+ "SET views=? "
				+ "WHERE postId=?";
	
		Object[] param = new Object[]  { post.getViews() + 1, post.getPostId() };			
	
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil占쎈퓠 update�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
		
	
		try {				
			jdbcUtil.executeUpdate();	// update �눧占� 占쎈뼄占쎈뻬
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		
	}
	
	public String findUserNickNameByUserId(int userId) throws SQLException {
        String sql = "SELECT nickName "
        			+ "FROM ACCOUNT "
        			+ "WHERE userId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {						// 占쎈린占쎄문 占쎌젟癰귨옙 獄쏆뮄猿�
				return rs.getString("nickName");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}
	
	public List<Post> findBuyerPostList() throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM POST "
        		   + "WHERE POSTTYPE='b' "
        		   + "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쎈퓠 query�눧占� 占쎄퐬占쎌젟
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈뼄占쎈뻬			
			List<Post> postList = new ArrayList<Post>();	// User占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
			while (rs.next()) {
				Post post = new Post(			// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getString("imageUrl"),
						rs.getInt("views"),
						rs.getString("status"),
						rs.getInt("price"),
						rs.getString("postType"),
						rs.getInt("writerId"));
				postList.add(post);				// List占쎈퓠 User 揶쏆빘猿� 占쏙옙占쎌삢
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}
	
	public List<Post> findMyPostList(int userId) throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM POST "
        		   + "WHERE writerId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();	
			List<Post> postList = new ArrayList<Post>();
			while (rs.next()) {
				Post post = new Post(		// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎈린占쎄문 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getString("description"),
						rs.getString("imageUrl"),
						rs.getDate("createdTime"),
						rs.getInt("categoryId"),
						rs.getInt("views"),
						rs.getString("status"),
						rs.getInt("price"),
						rs.getString("postType"),
						rs.getInt("writerId"));
				postList.add(post);				// List占쎈퓠 User 揶쏆빘猿� 占쏙옙占쎌삢
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}
	

}
