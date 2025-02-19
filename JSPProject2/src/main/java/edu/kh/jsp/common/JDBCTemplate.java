package edu.kh.jsp.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/* DB 연결 (Connection 생성) , 자동 커밋 off
	 * 트랜잭션 제어, JDBC 객제 자원 반환(close)
	 * 
	 * 이러한 JDBC에서 반복 사용되는 코드를 모아둔 클래스
	 * 
	 * * 모든 필드, 메서드가 static * 
	 * -> 별도 객체 생성 X
	 * -> 어디서든지 클래스명.필드명 / 클래스명.메서드명 호출 가능
	 * 
	 * */
	
	private static Connection conn = null;
	
	/** DB 연결 정보를 담고있는 Connection 객체 생성 및 반환 메서드
	 * @return conn
	 */
	public static Connection getConnection() {
		
		try {
			
			// 현재 커넥션 객체가 없을 경우 -> 새 커넥션 객체 생성
			if(conn == null || conn.isClosed() ) {
				// conn.isClosed() : 커넥션이 close 상태면 true 반환
				
				Properties prop = new Properties();
				// Map<String, String> 형태의 객체, XML 입출력 특화
				
				String filePath 
					= JDBCTemplate.class.getResource("/edu/kh/jsp/sql/driver.xml").getPath();
				
				System.out.println(filePath);
				
				// /C:/workspace/05_Server/...
				
				// driver.xml 파일 읽어오기
				prop.loadFromXML( new FileInputStream( filePath ) );
				// -> XML 파일에 작성된 내용이 Properties 객체에 모두 저장됨.
				
				// XML에서 읽어온 값을 모두 변수에 저장
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				
				// 커넥션 생성
				Class.forName(driver); // Oracle JDBC Driver 객체 메모리 로드
				
				// DriverManager를 이용해 Connection 객체 생성
				conn = DriverManager.getConnection(url, user, password);
				
				// 자동 커밋 비활성화
				conn.setAutoCommit(false);
			}
			
			
		}catch(Exception e) {
			System.out.println("[Connection 생성 중 예외 발생]");
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	/** Connection 객체 자원 반환 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		
		try {
			// 전달 받은 conn이
			// 참조하는 Connection 객체가 있고
			// 그 Connection 객체가 close 상태가 아니라면
			if(conn != null && !conn.isClosed()) conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** Statement(부모), PreparedStatment(자식) 객체 자원 반환 메서드
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** ResultSet 객제 자원 반환 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) rs.close(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 트랜잭션 Commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed() ) conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 트랜잭션 Rollback 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed() ) conn.rollback();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
