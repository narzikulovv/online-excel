package uz.excel.onlineexcel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.service.OrganizationService;

@RestController
@RequestMapping("/organization/")
public class OrganizationController extends AbstractController<OrganizationService> {

    public OrganizationController(OrganizationService service) {
        super(service);
    }
}
