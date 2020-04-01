package xyz.zx21.dao;

import org.apache.ibatis.annotations.Param;
import xyz.zx21.bean.Article;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/3/5 10:34
 */
public interface ArticleDao {
    /**
     * 分页
     *
     * @param start
     * @param limit
     * @return
     */
    List<Article> getList(@Param("start") int start, @Param("limit") int limit);

    /**
     * 插入
     *
     * @param article
     * @return
     */
    int insert(Article article);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean deleteById(@Param("id") Integer id);

    /**
     * 修改
     *
     * @param article
     * @return
     */
    boolean updateById(Article article);

    /**
     * id查询文章
     *
     * @param id
     * @return
     */
    Article selectById(@Param("id") Integer id);

    /**
     * 修改文章显示状态 0显示 1不显示
     *
     * @param id
     * @param status
     * @return
     */
    boolean updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 查询用户发表的文章
     *
     * @param start
     * @param limit
     * @param userName
     * @return
     */
    List<Article> getListByUserName(@Param("status") int start,@Param("limit") int limit,@Param("userName") Integer userName);
}
