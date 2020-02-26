package gdufs.agency.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gdufs.agency.entity.Indent;

public interface IndentMapper {
    int deleteByPrimaryKey(Integer indentid);

    int insert(Indent record);

    int insertSelective(Indent record);

    Indent selectByPrimaryKey(Integer indentid);

    int updateByPrimaryKeySelective(Indent record);

    int updateByPrimaryKey(Indent record);
    
    List<Indent> selectByState(String publishId);
    List<Indent> selectIndentsByType(@Param("publishId") String studentId,@Param("type") Integer type);
    
    List<Indent> selectByPublishId(String publishId);
}