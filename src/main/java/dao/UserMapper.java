package dao;

import entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUser(String username); // 不需要 @Select 注解，SQL 查询在 XML 文件中定义
}
