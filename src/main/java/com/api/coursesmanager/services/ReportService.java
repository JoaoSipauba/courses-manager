package com.api.coursesmanager.services;

import com.api.coursesmanager.models.CoursesReportModel;
import com.api.coursesmanager.repositories.CourseRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    CourseRepository courseRepository;

    public void exportCourses() throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile("classpath:reports/Courses.jrxml");
        this.generateReport(file);
    }

    private void generateReport(File file) throws JRException {
        String path = "C:\\Users\\joaos\\Downloads";
        var coursesReportModelList = courseRepository.findAllCoursesAndStudentCount();

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRDataSource jrDataSource = new JRBeanCollectionDataSource(coursesReportModelList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("USER_NAME", "João Sipauba");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\coursesReport.pdf");
    }
}
