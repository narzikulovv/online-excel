package uz.excel.onlineexcel.service;

import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.mapper.OrganizationMapper;
import uz.excel.onlineexcel.repository.OrganizationRepository;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;

@Service
public class OrganizationService extends AbstractService<OrganizationMapper, OrganizationRepository> implements BaseService {

    public OrganizationService(OrganizationMapper mapper, OrganizationRepository repository) {
        super(mapper, repository);
    }
}
