package com.yummy.mapper;

import com.github.pagehelper.Page;
import com.yummy.enumeration.OperationType;
import com.yummy.dto.CategoryPageQueryDTO;
import com.yummy.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * insert data
     * @param category
     */
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user)" +
            " VALUES" +
            " (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * page query
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * delete category by id
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /**
     * update category by id
     * @param category
     */
    void update(Category category);

    /**
     * search category by id
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
