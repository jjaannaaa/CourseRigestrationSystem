package Repository;

import DataBase.OracleConnection;
import Model.Course;
import Model.Student;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationRepo {
    public static void registerStudentToCourse(int studentId, int courseId) {
        String sql = "{ call register_course(?, ?) }";
        try (Connection conn = new OracleConnection().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, studentId);
            cs.setInt(2, courseId);
            cs.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteRegistration(int studentId, int courseId) {
        String sql = "{ call delete_registration(?, ?) }";
        try (Connection conn = new OracleConnection().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, studentId);
            cs.setInt(2, courseId);
            cs.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Course> getCoursesForStudent(int studentId) {
        List<Course> courses = new ArrayList<>();
        String sql = "{ call get_courses_by_student(?, ?) }";
        try (Connection conn = new OracleConnection().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, studentId);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                courses.add(new Course(rs.getInt("course_id"), rs.getString("course_name")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }

    public static List<Student> getStudentsByCourse(int courseId) {
        List<Student> students = new ArrayList<>();
        String sql = "{ call get_students_by_course(?, ?) }"; // your PL/SQL procedure

        try (Connection conn = new OracleConnection().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, courseId);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return students;
    }
}
