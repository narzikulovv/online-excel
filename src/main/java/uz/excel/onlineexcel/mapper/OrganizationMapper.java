package uz.excel.onlineexcel.mapper;

import org.mapstruct.Mapper;
import uz.excel.onlineexcel.dto.organization.OrganizationCreateDto;
import uz.excel.onlineexcel.dto.organization.OrganizationDto;
import uz.excel.onlineexcel.dto.organization.OrganizationUpdateDto;
import uz.excel.onlineexcel.entity.Organization;
import uz.excel.onlineexcel.mapper.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper<Organization, OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto> {
}
