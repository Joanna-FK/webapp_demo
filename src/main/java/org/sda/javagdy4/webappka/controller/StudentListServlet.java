package org.sda.javagdy4.webappka.controller;

import org.sda.javagdy4.webappka.database.EntityDao;
import org.sda.javagdy4.webappka.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.List;

@WebServlet("/students") // <-nie zapomnij o slash'u na początku, bo ni zadziała
                        // <- nie zapomnij o adnotacji, bo nie zadziała
public class StudentListServlet extends HttpServlet {
    private final EntityDao<Student> studentEntityDao = new EntityDao<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //przetwarzamy zapytanie

        //pobieramy z bazy listę studentów, ładujemy ją do atrybutów (żeby wyświetlić html)
        List<Student> listOfStudents = studentEntityDao.findAll(Student.class);
        req.setAttribute("students", listOfStudents);

        //przekazujemy do request dispatcher'a, żeby dla nas przetworzył stronę 'student_list'
        //i zwrócił ja do użytkownika

        req.getRequestDispatcher("/student_list.jsp").forward(req, resp);
    }
}
