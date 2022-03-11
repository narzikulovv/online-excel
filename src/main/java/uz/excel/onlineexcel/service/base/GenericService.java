package uz.excel.onlineexcel.service.base;


import uz.excel.onlineexcel.dto.base.GenericDto;

import java.util.List;


public interface GenericService<D extends GenericDto> extends BaseService {

    List<D> getAll();

    D get(Long id);

}
