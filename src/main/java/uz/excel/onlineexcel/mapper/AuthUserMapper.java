package uz.excel.onlineexcel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.excel.onlineexcel.dto.auth.AuthUserCreateDto;
import uz.excel.onlineexcel.dto.auth.AuthUserDto;
import uz.excel.onlineexcel.dto.auth.AuthUserUpdateDto;
import uz.excel.onlineexcel.entity.AuthUser;
import uz.excel.onlineexcel.mapper.base.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthUserMapper extends BaseMapper<AuthUser, AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto> {

}
