package gdufs.agency.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gdufs.agency.entity.Acceptance;
import gdufs.agency.entity.AcceptanceKey;

public interface AcceptanceMapper {
    int deleteByPrimaryKey(AcceptanceKey key);

    int insert(Acceptance record);

    int insertSelective(Acceptance record);

    Acceptance selectByPrimaryKey(AcceptanceKey key);

    int updateByPrimaryKeySelective(Acceptance record);

    int updateByPrimaryKey(Acceptance record);
    
    Acceptance selectByIndetId(@Param("indentId") Integer indentId);
    
    List<Acceptance> selectByAcceptId(@Param("acceptId") String acceptId);
}