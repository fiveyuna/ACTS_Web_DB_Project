package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;
import model.Transaction;
import model.User;
import java.sql.Date;

/**
 * 占쎄텢占쎌뒠占쎌쁽 �꽴占썹뵳�됵옙占� 占쎌맄占쎈퉸 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞 占쎌삂占쎈씜占쎌뱽 占쎌읈占쎈뼖占쎈릭占쎈뮉 DAO 占쎄깻占쎌삋占쎈뮞
 * ACCOUNT 占쎈�믭옙�뵠�뇡遺용퓠 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 �빊遺쏙옙, 占쎈땾占쎌젟, 占쎄텣占쎌젫, 野껓옙占쎄퉳 占쎈땾占쎈뻬 
 */
public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 揶쏆빘猿� 占쎄문占쎄쉐
	}
		
	/**
	 * 占쎄텢占쎌뒠占쎌쁽 �꽴占썹뵳占� 占쎈�믭옙�뵠�뇡遺용퓠 占쎄퉱嚥≪뮇�뒲 占쎄텢占쎌뒠占쎌쁽 占쎄문占쎄쉐.
	 */
	public int create(User user) throws SQLException {
		String sql = "INSERT INTO ACCOUNT VALUES (user_id_seq.nextval, ?, ?, ?, ?, ?, DEFAULT, ?, ?)";		
		Object[] param = new Object[] { user.getPhone(), user.getEmail(), user.getName(), 
						user.getRegNum(), user.getPassword(), user.getAccountId(), user.getNickName()};		
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
	 * 疫꿸퀣�덌옙�벥 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 占쎈땾占쎌젟.
	 */
	public int update(User user) throws SQLException {
		String sql = "UPDATE ACCOUNT "

					+ "SET phoneNumber=?, emailAddress=?, userName=?, "
					+ "password=?, nickName=? "
					+ "WHERE accountId=?";
		Object[] param = new Object[]  { user.getPhone(), user.getEmail(), user.getName(), 
				user.getPassword(), user.getNickName(), user.getAccountId()};			
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil占쎈퓠 update�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
		System.out.println("update query: " + sql);
		System.out.println("param: " + param);
		for (Object p : param) {
			System.out.println("p: " + p);
		}
			
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
	 * 占쎄텢占쎌뒠占쎌쁽 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽�몴占� 占쎄텣占쎌젫.
	 */
	public int remove(String accountId) throws SQLException {
		String sql = "DELETE FROM ACCOUNT WHERE accountId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {accountId});	// JDBCUtil占쎈퓠 delete�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

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
	 * 雅뚯눘堉깍쭪占� 占쎄텢占쎌뒠占쎌쁽 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞占쎈퓠占쎄퐣 筌≪뼚釉� User 占쎈즲筌롫뗄�뵥 占쎄깻占쎌삋占쎈뮞占쎈퓠 
	 * 占쏙옙占쎌삢占쎈릭占쎈연 獄쏆꼹�넎.
	 */
	public User findUser(String accountId) throws SQLException {
        String sql = "SELECT userId, phoneNumber, emailAddress, userName, registrationNumber, password, accountId, joinDate, nickName "
        			+ "FROM ACCOUNT "
        			+ "WHERE accountId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {accountId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {						// 占쎈린占쎄문 占쎌젟癰귨옙 獄쏆뮄猿�
				User user = new User(		// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎈린占쎄문 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
					rs.getInt("userId"),
						accountId,
					rs.getString("password"),
					rs.getString("userName"),
					rs.getString("emailAddress"),
					rs.getString("phoneNumber"),
					rs.getString("registrationNumber"),
					rs.getDate("joinDate"),
					rs.getString("nickName"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}
	
	public User findUserByPrimaryKey(int userId) throws SQLException {
        String sql = "SELECT phoneNumber, emailAddress, userName, registrationNumber, password, joinDate, accountId, nickName "
        			+ "FROM ACCOUNT "
        			+ "WHERE userId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {						// 占쎈린占쎄문 占쎌젟癰귨옙 獄쏆뮄猿�
				User user = new User(		// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎈린占쎄문 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
					userId,
					rs.getString("accountId"),
					rs.getString("password"),
					rs.getString("userName"),
					rs.getString("emailAddress"),
					rs.getString("phoneNumber"),
					rs.getString("registrationNumber"),
					rs.getDate("joinDate"),
					rs.getString("nickName"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}

	/**
	 * 占쎌읈筌ｏ옙 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 野껓옙占쎄퉳占쎈릭占쎈연 List占쎈퓠 占쏙옙占쎌삢 獄쏉옙 獄쏆꼹�넎
	 */
	public List<User> findUserList() throws SQLException {
        String sql = "SELECT phoneNumber, emailAddress, userName, registrationNumber, joinDate, accountId, nickName " 
        		   + "FROM ACCOUNT "
        		   + "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쎈퓠 query�눧占� 占쎄퐬占쎌젟
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈뼄占쎈뻬			
			List<User> userList = new ArrayList<User>();	// User占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
			while (rs.next()) {
				User user = new User(			// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						rs.getString("accountId"),
						null,
						rs.getString("userName"),
						rs.getString("emailAddress"),
						rs.getString("phoneNumber"),
						rs.getString("registrationNumber"),
						valueOf(rs.getDate("joinDate")),
						rs.getString("nickName"));
				userList.add(user);				// List占쎈퓠 User 揶쏆빘猿� 占쏙옙占쎌삢
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}
	
	/**
	 * 占쎌읈筌ｏ옙 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 野껓옙占쎄퉳占쎈립 占쎌뜎 占쎌겱占쎌삺 占쎈읂占쎌뵠筌욑옙占쏙옙 占쎈읂占쎌뵠筌욑옙占쎈뼣 �빊�뮆�젾占쎈막 占쎄텢占쎌뒠占쎌쁽 占쎈땾�몴占� 占쎌뵠占쎌뒠占쎈릭占쎈연
	 * 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�彛뷂옙�뱽 List占쎈퓠 占쏙옙占쎌삢占쎈릭占쎈연 獄쏆꼹�넎.
	 */

	
	private java.util.Date valueOf(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 雅뚯눘堉깍쭪占� 占쎄텢占쎌뒠占쎌쁽 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽揶쏉옙 鈺곕똻�삺占쎈릭占쎈뮉筌욑옙 野껓옙占쎄텢 
	 */
	public boolean existingUser(String accountId) throws SQLException {
		String sql = "SELECT count(*) FROM ACCOUNT WHERE accountId=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {accountId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return false;
	}
	
	public String findAccountIdByUserId (String userId) throws SQLException {
		 String sql = "SELECT accountId "
     			+ "FROM ACCOUNT "
     			+ "WHERE userId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {						// 占쎈린占쎄문 占쎌젟癰귨옙 獄쏆뮄猿�
				return rs.getString("accountId");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}


}
