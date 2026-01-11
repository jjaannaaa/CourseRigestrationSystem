package Repository;

import DataBase.DataBaseConnection;
import DataBase.OracleConnection;
import Model.Student;
import oracle.jdbc.OracleTypes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
    public static void insertStudent(String name, String email){
        String sql = "{ call add_student(?,?) }";
        DataBaseConnection oracle = null;
        Connection connection = null;
        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            CallableStatement cs = connection.prepareCall(sql);
            cs.setString(1, name);
            cs.setString(2,email);
            cs.execute();
            System.out.println("Student added successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            oracle.closeConnection(connection);
        }
    }
    public static List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        String sql = " {call get_all_students(?)} ";
        DataBaseConnection oracle = null;
        Connection connection = null;
        CallableStatement cs;
        ResultSet set = null;
        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            cs = connection.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            set = (ResultSet) cs.getObject(1);
            while (set.next()){
                students.add(new Student(
                        set.getInt("student_id"),
                        set.getString("student_name"),
                        set.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            oracle.closeConnection(connection, set);
        }
        return students;
    }

    public static Student getStudent(int id){
        Student student = null;
        String sql = "{ call get_student(?,?,?) }";
        DataBaseConnection oracle = null;
        Connection connection = null;
        CallableStatement cs = null;
        ResultSet set = null;
        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            cs = connection.prepareCall(sql);

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.execute();

            student = new Student(id, cs.getString(2), cs.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            oracle.closeConnection(connection, set, cs);
        }
        return student;
    }

    public static void updateStudent(int id, String name, String email){
        String sql = "{ call update_student(?,?,?) }";
        DataBaseConnection oracle = null;
        Connection connection = null;
        CallableStatement cs = null;
        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            cs = connection.prepareCall(sql);

            cs.setInt(1, id);
            cs.setString(2, name);
            cs.setString(3, email);
            cs.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            oracle.closeConnection(connection, cs);
        }
    }
    public static void deleteStudent(int id){
        String sql = "{ call delete_student(?) }";
        DataBaseConnection oracle = null;
        Connection connection = null;
        CallableStatement cs = null;
        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            cs = connection.prepareCall(sql);
            cs.setInt(1, id);
            cs.execute();
            System.out.println("Student Updated Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            oracle.closeConnection(connection,cs);
        }
    }
}
