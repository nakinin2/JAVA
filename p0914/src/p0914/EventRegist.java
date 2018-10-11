package p0914;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;

public class EventRegist {
	Scanner s = new Scanner(System.in);
	
	String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/yangjung?serverTimezone=UTC&characterEncoding=utf8";
	String user = "testuser";
	String pwd = "1234";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public EventRegist() {
		System.out.println("## event");
		System.out.print("이름 : ");
		String uname = s.next();//개행 문자, 공백 무시하고 출력한다
		//String uname = s.nextLine();//개행 문자, 공백을 인식하여 출력한다
		System.out.print("이메일 : ");
		String email = s.next();//개행 문자, 공백 무시하고 출력한다
		 //String uname = s.nextLine();//개행 문자, 공백을 인식하여 출력한다
		//메소드 설정
		connectDB();
		registUser(uname, email);
		printList();
		closeDB();
		
	}//end EventRegist public
	
	//DB 연결
	private void connectDB() {
		// TODO Auto-generated method stub
		try {
			Class.forName(jdbcDriver);
			
			conn = DriverManager.getConnection(jdbcUrl, user, pwd);
		}catch (Exception e){
			e.printStackTrace();
		}
	}//end connectDB 메소드
	
	//DB 수정
	private void registUser(String uname, String email) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO event VALUES(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uname);
			pstmt.setString(2, email);
			pstmt.executeUpdate();//데이터 정보 수정이므로 executeUpdate를 사용한다.
		}catch (Exception e) {
			e.printStackTrace();
		}
	}//end registUser 메소드
	
	//DB 조회
	private void printList() {
		// TODO Auto-generated method stub
		System.out.println("# 리스트");
		String sql = "SELECT * FROM event";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next() ) {//sql로 조회를 할 때마다 실행
				System.out.println(rs.getString("uname") + ", " + rs.getString(2) );
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}//end printList 메소드
	
	//DB 종료
	private void closeDB() {
		// TODO Auto-generated method stub
		try{
			pstmt.close();
			rs.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}//end closeDB 메소드
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//EventRegist er = new EventRegist();
		new EventRegist();
	}

}
