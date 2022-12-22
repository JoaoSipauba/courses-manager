package com.api.coursesmanager.services;

import com.api.coursesmanager.Utils.JwtUtils;
import com.api.coursesmanager.repositories.CourseRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EmailService emailService;

    public void exportCourses() throws FileNotFoundException, JRException {
        var coursesReportProjections = courseRepository.findAllCoursesAndStudentCount();

        File file = ResourceUtils.getFile("classpath:reports/Courses.jrxml");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(coursesReportProjections);

        this.generateReport(file, dataSource);
    }

    private void generateReport(File file, JRBeanCollectionDataSource dataSource) throws JRException {

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        JwtUtils jwtUtils = new JwtUtils();

        parameters.put("USER_NAME", jwtUtils.getUsername());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);

        emailService.sendEmailWithFile(output);
    }
}
