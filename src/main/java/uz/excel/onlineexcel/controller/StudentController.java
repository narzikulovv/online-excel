package uz.excel.onlineexcel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.service.StudentService;

@RestController
@RequestMapping("/student/")
public class StudentController extends AbstractController<StudentService> {

    public StudentController(StudentService service) {
        super(service);
    }
}
