package edu.kh.jsp.student.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jsp.student.model.dao.StudentDAO;
import edu.kh.jsp.student.model.dto.Student;
import static edu.kh.jsp.common.JDBCTemplate.*;

public class StudentService {
	// DAO 필드에 생성
	private StudentDAO dao = new StudentDAO();
	

	public List<Student> selectAll() throws Exception{
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 호출
		List<Student> stdList = dao.selectAll(conn);
		
		// 3. 트랜제어 select 필요 없음
		
		// 4. Connection 반환
		close(conn);
		
		// 5. 결과 반환
		return stdList;
	}
	

	public List<Student> selectArch() throws Exception{
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 호출
		List<Student> stdList = dao.selectArch(conn);
		
		// 3. 트랜제어 select 필요 없음
		
		// 4. Connection 반환
		close(conn);
		
		// 5. 결과 반환
		return stdList;
	}


	public List<Student> selectOne(String deptName) throws Exception{
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 호출
		List<Student> stdList = dao.selectOne(conn, deptName);
		
		// 3. 트랜제어 select 필요 없음
		
		// 4. Connection 반환
		close(conn);
		
		// 5. 결과 반환
		return stdList;
	}	


}
