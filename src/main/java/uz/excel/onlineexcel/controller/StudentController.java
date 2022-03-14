package uz.excel.onlineexcel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.dto.FilterDto;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student/")
public class StudentController extends AbstractController<StudentService> {

    public StudentController(StudentService service) {
        super(service);
    }

    @GetMapping("list")
    public ResponseEntity<DataDto<List<StudentDto>>> getAll() {
        return service.getAllStudents();
    }

    @GetMapping("filter")
    public ResponseEntity<DataDto<List<StudentDto>>> getAllByFilter(@RequestBody FilterDto filterDto) {
        return service.getByFilter(filterDto);
    }



}
