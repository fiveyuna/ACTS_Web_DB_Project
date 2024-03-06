package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Favorite;


public class FavoriteDAO {
private JDBCUtil jdbcUtil = null;
	
	public FavoriteDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil �뤆�룇鍮섊뙼占� �뜝�럡臾멨뜝�럡�뎽
	}
		
	public int create(Favorite favorite) throws SQLException {

		String sql = "INSERT INTO FAVORITE VALUES (favorite_id_seq.nextval, ?, ?)";
		Object[] param = new Object[] { favorite.getUserId(), favorite.getPostId() };	
		System.out.println("sql: " + sql);
		System.out.println("param: " + param);
		for (Object p : param) {
			System.out.println(p);
		}

		jdbcUtil.setSqlAndParameters(sql, param);	
		try {
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {	 
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;			
	}

	public int removeByPostIdAndUserId(int postId, int userId) throws SQLException {
		String sql = "DELETE FROM FAVORITE WHERE postId=? AND userId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId, userId});	

		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
	}

	public Favorite findFavoriteByPostIdAndUserId(int postId, int userId) throws SQLException {
        String sql = "SELECT favorId, userId, postId "
        			+ "FROM favorite "
        			+ "WHERE postId=? AND userId=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId, userId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {						
				Favorite favorite = new Favorite(
					rs.getInt("favorId"),
					rs.getInt("userId"),
					rs.getInt("postId"));
				return favorite;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}

	public List<Favorite> findFavoriteListByUserId(int userId) throws SQLException {
        String sql = "SELECT favorId, userId, postId " 
        		   + "FROM Favorite "
        		   + "WHERE userId=? ";
        
        jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<Favorite> favorList = new ArrayList<Favorite>();	
			while (rs.next()) {
				Favorite favor = new Favorite(			
						rs.getInt("favorId"),
						rs.getInt("userId"),
						rs.getInt("postId"));
				favorList.add(favor);				
			}		
			return favorList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}
}
