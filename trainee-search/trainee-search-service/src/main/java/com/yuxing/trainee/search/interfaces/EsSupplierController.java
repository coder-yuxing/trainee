package com.yuxing.trainee.search.interfaces;

import com.yuxing.trainee.common.core.Pager;
import com.yuxing.trainee.search.api.command.CreateEsSupplierCommand;
import com.yuxing.trainee.search.api.command.DeleteEsSupplierCommand;
import com.yuxing.trainee.search.api.command.UpdateEsSupplierCommand;
import com.yuxing.trainee.search.api.dto.EsSupplierDTO;
import com.yuxing.trainee.search.api.query.EsSupplierQuery;
import com.yuxing.trainee.search.application.facade.EsSupplierFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * supplier restful api
 *
 * @author yuxing
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/suppliers")
public class EsSupplierController {

    private final EsSupplierFacadeService esSupplierFacadeService;

    /**
     * 创建文档
     */
    @PostMapping
    public void create(@RequestBody CreateEsSupplierCommand command) {
        esSupplierFacadeService.create(command);
    }

    /**
     * 更新文档
     */
    @PutMapping
    public void update(@RequestBody UpdateEsSupplierCommand command) {
        esSupplierFacadeService.update(command);
    }

    /**
     * 删除文档
     */
    @DeleteMapping
    public void delete(@RequestBody DeleteEsSupplierCommand command) {
        esSupplierFacadeService.delete(command);
    }

    /**
     * supplier 搜索
     */
    @GetMapping("/search")
    public Pager<EsSupplierDTO> search(EsSupplierQuery query) {
        return esSupplierFacadeService.search(query);
    }
}
