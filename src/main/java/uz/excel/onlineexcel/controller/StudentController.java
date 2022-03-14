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


    @GetMapping(value = "get/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable Long id){
        return new ResponseEntity<>(service.get(id));
    }

    @PostMapping(value = "create")
    public ResponseEntity<Long> create(@RequestBody StudentCreateDto dto){
        Long id = service.create(dto);
        return new ResponseEntity<>(id);
    }


    @PatchMapping(value = "update")
    public ResponseEntity<String> update(@RequestBody StudentUpdateDto dto){
        service.update(dto);
        return new ResponseEntity<>("");
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("");
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
