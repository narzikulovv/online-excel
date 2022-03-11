package uz.excel.onlineexcel.service;

import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.mapper.StudentMapper;
import uz.excel.onlineexcel.repository.StudentRepository;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;

@Service
public class StudentService extends AbstractService<StudentMapper, StudentRepository> implements BaseService {

    public StudentService(StudentMapper mapper, StudentRepository repository) {
        super(mapper, repository);
    }
}
