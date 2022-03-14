package uz.excel.onlineexcel.service;

import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.student.StudentCreateDto;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.dto.student.StudentUpdateDto;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.mapper.StudentMapper;
import uz.excel.onlineexcel.repository.StudentRepository;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class StudentService extends AbstractService<StudentMapper, StudentRepository> implements BaseService {

    public StudentService(StudentMapper mapper, StudentRepository repository) {
        super(mapper, repository);
    }

    public List<StudentDto> getAll() {
        List<StudentDto> list = new ArrayList<>();
        StudentDto dto = StudentDto
                .builder()
                .fullName("Doston Bokhodirov")
                .universityName("TKTI")
                .entranceYear("2019")
                .graduationYear("2023")
                .faculty("Menejment")
                .speciality("Menejment")
                .studyType("kontrakt")
                .academicType("kunduzgi")
                .diplomaSerial("12345")
                .diplomaRegistrationNumber("111")
                .givenDate("01.01.2023")
                .academicLevel("bakalavr")
                .appendixNumber("0")
                .organizationId(100L)
                .build();
        list.add(dto);
        return list;

    }

    public StudentDto get(Long id) {

        Optional<Student> student = repository.findById(id);

        return mapper.toDto(student.get());
    }

    public Long create(StudentCreateDto dto) {

        Student student = mapper.fromCreateDto(dto);

        return repository.save(student).getId();

    }

    public void update(StudentUpdateDto dto) {

        Optional<Student> studentOptional = repository.findById(dto.getId());
        Student student = studentOptional.get();

        student = mapper.fromUpdateDto(dto, student);

        repository.save(student);
    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

}
