package uz.excel.onlineexcel.mapper;

import org.mapstruct.Mapper;
import uz.excel.onlineexcel.dto.student.StudentCreateDto;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.dto.student.StudentUpdateDto;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.mapper.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends BaseMapper<Student, StudentDto, StudentCreateDto, StudentUpdateDto> {

}
