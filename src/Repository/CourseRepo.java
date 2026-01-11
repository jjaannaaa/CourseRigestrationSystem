package Repository;

import DataBase.DataBaseConnection;
import DataBase.OracleConnection;
import Model.Course;
import oracle.jdbc.internal.OracleTypes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepo {
    public static void addCourse(String name){
        String sql = "{ call add_course(?) }";
        DataBaseConnection oracle = null;
        Connection connection = null;

        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            CallableStatement cs = connection.prepareCall(sql);
            cs.setString(1, name);
            cs.execute();
            System.out.println("Course added successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            oracle.closeConnection(connection);
        }
    }
    public static Course getCourse(int id){
        Course course = null;
        String sql = "{ call get_course(?,?) }"; // match your PL/SQL
        DataBaseConnection oracle = null;
        Connection connection = null;
        CallableStatement cs = null;

        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            cs = connection.prepareCall(sql);

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.VARCHAR); // course_name OUT
            cs.execute();

            course = new Course(id, cs.getString(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            oracle.closeConnection(connection, cs);
        }
        return course;
    }
    public static List<Course> getCourses(){
        List<Course> courses = new ArrayList<>();
        String sql = "{ call get_all_courses(?) }";
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
            while (set.next()) {
                courses.add(new Course(
                        set.getInt("course_id"),
                        set.getString("course_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            oracle.closeConnection(connection, set);
        }
        return courses;
    }
    public static void deleteCourse(int id){
        String sql = "{ call delete_course(?) }";
        DataBaseConnection oracle = null;
        Connection connection = null;
        CallableStatement cs = null;

        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            cs = connection.prepareCall(sql);
            cs.setInt(1, id);
            cs.execute();
            System.out.println("Course deleted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            oracle.closeConnection(connection, cs);
        }
    }
    public static void updateCourse(int id, String name){
        String sql = "{ call update_course(?,?) }";
        DataBaseConnection oracle = null;
        Connection connection = null;
        CallableStatement cs = null;

        try {
            oracle = new OracleConnection();
            connection = oracle.getConnection();
            cs = connection.prepareCall(sql);

            cs.setInt(1, id);
            cs.setString(2, name);
            cs.execute();
            System.out.println("Course updated successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            oracle.closeConnection(connection, cs);
        }
    }
}
