package uz.excel.onlineexcel.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.service.base.BaseService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExcelFileService implements BaseService {

    public String createExcelFile(List<StudentDto> list) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.setWorkbookType(XSSFWorkbookType.XLSX);
        String fileName = System.currentTimeMillis() + UUID.randomUUID().toString();
        File file = new File("src/main/resources/excelFileStorage/" + fileName + ".xlsx");

        try (FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath())) {

            XSSFSheet xssfSheet = workbook.createSheet("Students");
            XSSFRow xssfRow = xssfSheet.createRow(0);

            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName("Times New Roman");
            font.setBold(true);

            CellStyle style = workbook.createCellStyle();
            style.setFont(font);

            List<String> rowNames = new ArrayList<>();
            rowNames.add("№");
            rowNames.add("OTM nomi");
            rowNames.add("Familiyasi, ismi, sharifi");
            rowNames.add("O'qishga kirgan yili");
            rowNames.add("Bitirgan yili");
            rowNames.add("Fakulteti");
            rowNames.add("Yo'nalishi");
            rowNames.add("O'qish turi");
            rowNames.add("Bo'lim");
            rowNames.add("Diplom seriyasi");
            rowNames.add("Diplom ro'yxatga olish raqami");
            rowNames.add("Berilgan vaqti");
            rowNames.add("Magistr/Bakalavr");
            rowNames.add("Ilova");

            for (int i = 0; i <= 13; i++) {
                XSSFCell cell = xssfRow.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(rowNames.get(i));
            }

            Font font1 = workbook.createFont();
            font1.setFontHeightInPoints((short) 11);
            font1.setFontName("Times New Roman");

            CellStyle style1 = workbook.createCellStyle();
            style1.setFont(font1);

            for (int i = 0; i < list.size(); i++) {

                XSSFRow row = xssfSheet.createRow(i + 1);
                row.setRowStyle(style);

                List<String> studentValues = new ArrayList<>();
                studentValues.add(String.valueOf(i + 1));
                studentValues.add(list.get(i).getUniversityName());
                studentValues.add(list.get(i).getFullName());
                studentValues.add(list.get(i).getEntranceYear());
                studentValues.add(list.get(i).getGraduationYear());
                studentValues.add(list.get(i).getFaculty());
                studentValues.add(list.get(i).getSpeciality());
                studentValues.add(list.get(i).getStudyType());
                studentValues.add(list.get(i).getAcademicType());
                studentValues.add(list.get(i).getDiplomaSerial());
                studentValues.add(list.get(i).getDiplomaRegistrationNumber());
                studentValues.add(list.get(i).getGivenDate());
                studentValues.add(list.get(i).getAcademicLevel());
                studentValues.add(list.get(i).getAppendixNumber());

                for (int j = 0; j <= 13; j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(studentValues.get(j));
                    cell.setCellStyle(style1);
                }
            }
            workbook.write(outputStream);
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
