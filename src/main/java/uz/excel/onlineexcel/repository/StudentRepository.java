package uz.excel.onlineexcel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.repository.BaseRepository;

public interface StudentRepository extends JpaRepository<Student, Long>, BaseRepository {
}
