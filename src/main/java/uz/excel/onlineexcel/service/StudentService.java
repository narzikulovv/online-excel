package uz.excel.onlineexcel.service;

import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.mapper.StudentMapper;
import uz.excel.onlineexcel.repository.StudentRepository;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;

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
}
