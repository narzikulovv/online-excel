package uz.excel.onlineexcel.service;

import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.FilterDto;
import uz.excel.onlineexcel.dto.student.StudentCreateDto;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.dto.student.StudentUpdateDto;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.mapper.StudentMapper;
import uz.excel.onlineexcel.repository.StudentRepository;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;

import java.sql.*;
import java.util.*;
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


    public ResponseEntity<DataDto<List<StudentDto>>> getAllStudents() {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAllByCount())));
    }

    public ResponseEntity<DataDto<List<StudentDto>>> getByFilter(FilterDto filterDto) {

        List<StudentDto> returnStudent = new ArrayList<>(Collections.emptyList());

        if (Objects.isNull(filterDto)) {
            return getAllStudents();
        } else {

            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/online_excel", "postgres", "iPhone0303");

                boolean isJoin = false;
                StringBuilder query = new StringBuilder("select  * from auth.student where ");

                if (Objects.nonNull(filterDto.getFullName())) {
                    query.append("full_name = ").append(filterDto.getFullName());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getUniversityName())) {
                    if (isJoin) {
                        query.append(" and university_name = ").append(filterDto.getUniversityName());
                    }
                    query.append("university_name = ").append(filterDto.getUniversityName());
                    isJoin = true;
                }

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
