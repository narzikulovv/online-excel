package uz.excel.onlineexcel.service.base;

import uz.excel.onlineexcel.dto.base.BaseDto;
import uz.excel.onlineexcel.dto.base.GenericDto;

/**
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 */
public interface GenericCrudService<
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto> extends GenericService<D> {

    Long create(CD createDto);

    void delete(Long id);

    void update(UD updateDto);

}
