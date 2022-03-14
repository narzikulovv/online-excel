package uz.excel.onlineexcel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.excel.onlineexcel.dto.auth.AuthUserCreateDto;
import uz.excel.onlineexcel.dto.auth.AuthUserDto;
import uz.excel.onlineexcel.dto.auth.AuthUserUpdateDto;
import uz.excel.onlineexcel.entity.AuthUser;
import uz.excel.onlineexcel.mapper.base.BaseMapper;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthUserMapper extends BaseMapper<AuthUser,
        AuthUserDto,
        AuthUserCreateDto,
        AuthUserUpdateDto> {

    @Override
    AuthUserDto toDto(AuthUser authUser);

    @Override
    List<AuthUserDto> toDto(List<AuthUser> e);

    @Override
    @Mapping(target="picture",ignore = true)
    @Mapping(target="password",ignore = true)
    AuthUser fromCreateDto(AuthUserCreateDto authUserCreateDto);

    @Override
    AuthUser fromUpdateDto(AuthUserUpdateDto authUserUpdateDto);
}
