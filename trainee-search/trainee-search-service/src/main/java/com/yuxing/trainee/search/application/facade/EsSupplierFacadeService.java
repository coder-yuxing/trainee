package com.yuxing.trainee.search.application.facade;


import com.yuxing.trainee.search.api.command.CreateEsSupplierCommand;
import com.yuxing.trainee.search.api.command.DeleteEsSupplierCommand;
import com.yuxing.trainee.search.api.command.UpdateEsSupplierCommand;
import com.yuxing.trainee.search.api.dto.EsSupplierDTO;
import com.yuxing.trainee.search.api.query.EsSupplierQuery;

/**
 * supplier es 门面服务接口
 *
 * @author yuxing
 */
public interface EsSupplierFacadeService {

    /**
     * 创建supplier 文档
     *
     * @param command 创建文档命令
     */
    void create(CreateEsSupplierCommand command);

    /**
     * 更新supplier 文档
     *
     * @param command 更新文档命令
     */
    void update(UpdateEsSupplierCommand command);

    /**
     * 删除 supplier 文档
     *
     * @param command 删除文档命令
     */
    void delete(DeleteEsSupplierCommand command);

    /**
     * supplier 文档查询
     *
     * @param query 查询条件
     * @return 文档分页
     */
    Pager<EsSupplierDTO> search(EsSupplierQuery query);
}
