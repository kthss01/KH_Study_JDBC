package com.kh.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.member.model.vo.Member;

/* 1.Connection 객체 연결하기 
 * 2.Statement 객체 생성하기 
 * 3.ResultSet 객체 생성하기 
 * 4.Sql작성하기 
 * 5.ResultSet  결과담기 
 * 6.list 에 객체 하나씩 담기 
 * 7.close 하기 (자원반납 - 생성의 역순)
 */

public class MemberDAO {

	public ArrayList<Member> selectAll() {
		ArrayList<Member> list = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER"; // 뒤에 세미콜론 안붙여도 됨

		// 1. jdbc driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			// 2. 등록된 클래스를 이용해서 db 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl: 사용자정의설치 , thin: 자동으로 설치
			// ip주소 -> localhost 로 변경해도됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");

			System.out.println("conn=" + conn); // 성공하면 connection값, 실패하면 null값

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 resultset으로 받기
			rset = stmt.executeQuery(sql);

			// 5. 받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Member>();

			// 값이 있으면 true 없으면 false
			while (rset.next()) {
				Member m = new Member();

				/*
				 * USERID PASSWORD USERNAME GENDER AGE EMAIL PHONE ADDRESS HOBBY ENROLLDATE
				 */

				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // JDBC CHAR타입, 받아오는건 String으로 받아오면 된다고 함
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));

				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 역순으로 close
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public Member selectOne(String memberId) {
		Member m = null;

		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID = ?"; // ?로 바꿈 single quotation도 없음

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			// 2. 등록된 클래스를 이용해서 db 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl: 사용자정의설치 , thin: 자동으로 설치
			// ip주소 -> localhost 로 변경해도됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");

			System.out.println("conn=" + conn); // 성공하면 connection값, 실패하면 null값

			// 3. 쿼리문을 실행할 prepareStatement 객체 생성, 쿼리도 바로 넣어줌
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			// 4. 쿼리문 전송, 실행결과를 resultset으로 받기, pstmt에선 sql 뺌
//			rset = stmt.executeQuery(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member();

				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // JDBC CHAR타입, 받아오는건 String으로 받아오면 된다고 함
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 역순으로 close
				rset.close();
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return m;
	}
	
	public Member selectOneByName(String memberName) {
		Member m = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERNAME = '" + memberName + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			// 2. 등록된 클래스를 이용해서 db 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl: 사용자정의설치 , thin: 자동으로 설치
			// ip주소 -> localhost 로 변경해도됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");

			System.out.println("conn=" + conn); // 성공하면 connection값, 실패하면 null값

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 resultset으로 받기
			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				m = new Member();

				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // JDBC CHAR타입, 받아오는건 String으로 받아오면 된다고 함
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 역순으로 close
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return m;
	}

	public int insertMember(Member m) {
		int result = 0;

		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO MEMBER VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			// 2. 등록된 클래스를 이용해서 db 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl: 사용자정의설치 , thin: 자동으로 설치
			// ip주소 -> localhost 로 변경해도됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");

			System.out.println("conn=" + conn); // 성공하면 connection값, 실패하면 null값

			// 3. 쿼리문을 실행할 statement 객체 생성
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			pstmt.setDate(10, m.getEnrollDate());

			conn.setAutoCommit(false); // auto commit 안되게
//			result = stmt.executeUpdate(sql); // 처리한 행의 갯수 리턴
			result = pstmt.executeUpdate();

			if (result > 0)
				conn.commit(); // 적용
			else
				conn.rollback(); // 되돌리기

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 역순으로 close
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;

		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM MEMBER WHERE USERID = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			// 2. 등록된 클래스를 이용해서 db 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl: 사용자정의설치 , thin: 자동으로 설치
			// ip주소 -> localhost 로 변경해도됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");

			System.out.println("conn=" + conn); // 성공하면 connection값, 실패하면 null값

			// 3. 쿼리문을 실행할 statement 객체 생성
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			conn.setAutoCommit(false); // auto commit 안되게
//			result = stmt.executeUpdate(sql); // 처리한 행의 갯수 리턴
			result = pstmt.executeUpdate();

			if (result > 0)
				conn.commit(); // 적용
			else
				conn.rollback(); // 되돌리기

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 역순으로 close
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updateMember(String memberId, Member m) {
		int result = 0;

		Connection conn = null;
		Statement stmt = null;

		String sql = String.format(
				"UPDATE MEMBER SET "
				+ "USERID = '%s', "
				+ "PASSWORD = '%s', "
				+ "USERNAME = '%s', "
				+ "GENDER = '%s', "
				+ "AGE = '%d', "
				+ "EMAIL = '%s', "
				+ "PHONE = '%s', "
				+ "ADDRESS = '%s', "
				+ "HOBBY = '%s', "
				+ "ENROLLDATE = '%s' "
				+ "WHERE USERID = '%s'", 
				m.getUserId(),
				m.getPassword(),
				m.getUserName(),
				m.getGender(),
				m.getAge(),
				m.getEmail(),
				m.getPhone(),
				m.getAddress(),
				m.getHobby(),
				m.getEnrollDate(),
				memberId);
		
//		System.out.println(sql);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			// 2. 등록된 클래스를 이용해서 db 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl: 사용자정의설치 , thin: 자동으로 설치
			// ip주소 -> localhost 로 변경해도됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");

			System.out.println("conn=" + conn); // 성공하면 connection값, 실패하면 null값

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			conn.setAutoCommit(false); // auto commit 안되게
			result = stmt.executeUpdate(sql); // 처리한 행의 갯수 리턴

			if (result > 0)
				conn.commit(); // 적용
			else
				conn.rollback(); // 되돌리기

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 역순으로 close
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<Member> selectName(String memberName) {
		ArrayList<Member> list = null;

		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";

		// 1. jdbc driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			// 2. 등록된 클래스를 이용해서 db 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl: 사용자정의설치 , thin: 자동으로 설치
			// ip주소 -> localhost 로 변경해도됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");

			System.out.println("conn=" + conn); // 성공하면 connection값, 실패하면 null값

			// 3. 쿼리문을 실행할 statement 객체 생성
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberName);
			
			// 4. 쿼리문 전송, 실행결과를 resultset으로 받기
//			rset = stmt.executeQuery(sql);
			rset = pstmt.executeQuery();
			
			// 5. 받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Member>();

			// 값이 있으면 true 없으면 false
			while (rset.next()) {
				Member m = new Member();

				/*
				 * USERID PASSWORD USERNAME GENDER AGE EMAIL PHONE ADDRESS HOBBY ENROLLDATE
				 */

				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // JDBC CHAR타입, 받아오는건 String으로 받아오면 된다고 함
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));

				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 역순으로 close
				rset.close();
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public int updateMember(Member m) {
		int result = 0;

		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE MEMBER SET " 
				+ "PASSWORD = ?, " 
				+ "EMAIL = ?, " 
				+ "PHONE = ?, " 
				+ "ADDRESS ?, " 
				+ "HOBBY = ? "
				+ "WHERE USERID = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");

			// 2. 등록된 클래스를 이용해서 db 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl: 사용자정의설치 , thin: 자동으로 설치
			// ip주소 -> localhost 로 변경해도됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");

			System.out.println("conn=" + conn); // 성공하면 connection값, 실패하면 null값

			// 3. 쿼리문을 실행할 statement 객체 생성
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getHobby());
			pstmt.setString(6, m.getUserId());
			
			conn.setAutoCommit(false); // auto commit 안되게
//			result = stmt.executeUpdate(sql); // 처리한 행의 갯수 리턴
			result = pstmt.executeUpdate();

			if (result > 0)
				conn.commit(); // 적용
			else
				conn.rollback(); // 되돌리기

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 역순으로 close
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
