package uz.excel.onlineexcel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.excel.onlineexcel.entity.AuthUser;
import uz.excel.onlineexcel.repository.BaseRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {
}
