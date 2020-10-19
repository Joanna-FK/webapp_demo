package org.sda.javagdy4.webappka.controller;

import org.sda.javagdy4.webappka.database.EntityDao;
import org.sda.javagdy4.webappka.model.Grade;
import org.sda.javagdy4.webappka.model.GradeSubject;
import org.sda.javagdy4.webappka.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/grade/edit")
public class GradeEditServlet extends HttpServlet {
    private final EntityDao<Grade> gradeEntityDao = new EntityDao<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gradeIdString = req.getParameter("gradeId");
        if (gradeIdString == null) {
            resp.sendRedirect(req.getContextPath() + "/students");
            return;
        }
        Long gradeId = Long.parseLong(gradeIdString);
        Optional<Grade> gradeOptional = gradeEntityDao.findById(gradeId, Grade.class);
        if(gradeOptional.isPresent()){
            Grade grade = gradeOptional.get();
            GradeSubject[] subjects = GradeSubject.values();
            req.setAttribute("availableSubjects", subjects);
            req.setAttribute("studentIdAttribute", grade.getStudent().getId());
            req.setAttribute("modifiedGrade", grade);
            req.getRequestDispatcher("/grade_form.jsp").forward(req, resp);
        } else{
            resp.sendRedirect(req.getContextPath() + "/students");
        }

    }
}
