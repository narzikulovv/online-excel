package uz.excel.onlineexcel.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.service.base.BaseService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

            xssfRow.createCell(0).setCellValue("№");
            xssfRow.createCell(1).setCellValue("OTM nomi");
            xssfRow.createCell(2).setCellValue("Familiyasi, ismi, sharifi");
            xssfRow.createCell(3).setCellValue("O'qishga kirgan yili");
            xssfRow.createCell(4).setCellValue("Bitirgan yili");
            xssfRow.createCell(5).setCellValue("Fakulteti");
            xssfRow.createCell(6).setCellValue("Yo'nalishi");
            xssfRow.createCell(7).setCellValue("O'qish turi");
            xssfRow.createCell(8).setCellValue("Bo'lim");
            xssfRow.createCell(9).setCellValue("Diplom seriyasi");
            xssfRow.createCell(10).setCellValue("Diplom ro'yxatga olish raqami");
            xssfRow.createCell(11).setCellValue("Berilgan vaqti");
            xssfRow.createCell(12).setCellValue("Magistr/Bakalavr");
            xssfRow.createCell(13).setCellValue("Ilova");

            for (int i = 0; i < list.size(); i++) {

                XSSFRow row = xssfSheet.createRow(i + 1);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(list.get(i).getUniversityName());
                row.createCell(2).setCellValue(list.get(i).getFullName());
                row.createCell(3).setCellValue(list.get(i).getEntranceYear());
                row.createCell(4).setCellValue(list.get(i).getGraduationYear());
                row.createCell(5).setCellValue(list.get(i).getFaculty());
                row.createCell(6).setCellValue(list.get(i).getSpeciality());
                row.createCell(7).setCellValue(list.get(i).getStudyType());
                row.createCell(8).setCellValue(list.get(i).getAcademicType());
                row.createCell(9).setCellValue(list.get(i).getDiplomaSerial());
                row.createCell(10).setCellValue(list.get(i).getDiplomaRegistrationNumber());
                row.createCell(11).setCellValue(list.get(i).getGivenDate());
                row.createCell(12).setCellValue(list.get(i).getAcademicLevel());
                row.createCell(13).setCellValue(list.get(i).getAppendixNumber());

            }
            workbook.write(outputStream);
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
