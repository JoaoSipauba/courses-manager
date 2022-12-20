package com.api.coursesmanager.services;

import com.api.coursesmanager.repositories.CourseRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    CourseRepository courseRepository;

    public void exportCourses() throws FileNotFoundException, JRException {
        var courseModelList = courseRepository.findAll();

        File file = ResourceUtils.getFile("classpath:reports/Courses.jrxml");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(courseModelList);

        this.generateReport(file, dataSource);
    }

    private void generateReport(File file, JRBeanCollectionDataSource dataSource) throws JRException {
        String path = "C:\\Users\\joaos\\Downloads";

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("USER_NAME", "João Sipauba");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\coursesReport.pdf");
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
    }
}
