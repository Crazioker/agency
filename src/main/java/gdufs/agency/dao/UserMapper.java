package gdufs.agency.dao;

import gdufs.agency.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String studentid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String studentid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}