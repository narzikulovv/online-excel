package uz.excel.onlineexcel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.service.ExcelFileService;

@RestController
@RequestMapping("/excel/")
public class ExcelFileController extends AbstractController<ExcelFileService> {

    public ExcelFileController(ExcelFileService service) {
        super(service);
    }
}
