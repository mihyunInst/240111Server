package edu.kh.jsp.student.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.jsp.student.model.dto.Student;
import edu.kh.jsp.student.model.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jstl/student/selectArch")
public class SelectArchController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 서비스 객체 생성
		
		StudentService service = new StudentService();
		
		try {
			
			// 학생 전체 조회 서비스
			List<Student> stdList = service.selectArch();
			
			// request scope에 stdList를 담아서 JSP 로 위임
			req.setAttribute("stdList", stdList);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		// 요청 위임
		req.getRequestDispatcher("/WEB-INF/views/student/selectArch.jsp").forward(req, resp);
		
		
		
	}
}
