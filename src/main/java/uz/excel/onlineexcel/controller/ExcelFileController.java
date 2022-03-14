package uz.excel.onlineexcel.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.service.ExcelFileService;
import uz.excel.onlineexcel.service.StudentService;
import uz.excel.onlineexcel.utils.MediaTypeUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/excel/")
public class ExcelFileController extends AbstractController<ExcelFileService> {

    private final ServletContext servletContext;
    private final StudentService studentService;

    public ExcelFileController(ExcelFileService service, ServletContext servletContext, StudentService studentService) {
        super(service);
        this.servletContext = servletContext;
        this.studentService = studentService;
    }

    @RequestMapping("/get")
    public ResponseEntity<InputStreamResource> getExcelFile() throws IOException {

        String excelFileName = service.createExcelFile(studentService.getAll());

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, excelFileName);

        File file = new File(excelFileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length()) //
                .body(resource);
    }

    @RequestMapping(value = "/upload/{id}")
    public void uploadFile(@PathVariable String id){
        if (!id.equals("123123")) return;
        service.upload();

    }


}
