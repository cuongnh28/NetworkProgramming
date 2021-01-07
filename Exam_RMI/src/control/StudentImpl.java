/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author The Godfather
 */
public class StudentImpl extends UnicastRemoteObject implements StudentInterface{
    
    public Connection conn;
    public Statement stmt;
    
    public StudentImpl() throws RemoteException{
        String dbUrl = "jdbc:mysql://localhost:3306/exam";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl, "root", "123456789");
            stmt = conn.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(StudentImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Student> search(String keyword){
        List<Student> listStudent = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE name LIKE '%" + keyword + "%'";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                listStudent.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listStudent;
    }
    
    @Override
    public boolean editStudent(int studentId, Student student){
        String sql = "UPDATE student SET name='" + student.getName() + "', birth='" + student.getBirth() +
                "', course='" + student.getCourse() + "', address='" + student.getAddress() + "' WHERE studentId=" + studentId;
        System.out.println(sql);
        try {
            if(stmt.executeUpdate(sql) != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
