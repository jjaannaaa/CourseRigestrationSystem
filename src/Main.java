import DataBase.DataBaseConnection;
import DataBase.OracleConnection;
import Model.Student;
import Repository.StudentRepo;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //connection test
//        DataBaseConnection oracle = new OracleConnection();
//        Connection connection = oracle.getConnection();
//        oracle.closeConnection(connection);
//        StudentRepo.insertStudent("Jana", "Janayahya@123");
//        StudentRepo.insertStudent("Meera", "Meera@123");
//        StudentRepo.insertStudent("Ajwad", "Ajwad@123");
        List<Student> s = StudentRepo.getStudents();
        System.out.println(s);

        Student s2 = StudentRepo.getStudent(11111112);
        System.out.println(s2);
    }
}