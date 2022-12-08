package com.lei.jvm.utils.base.mapstruct.mapper;

import com.lei.jvm.utils.base.mapstruct.PersonDTO;
import com.lei.jvm.utils.base.mapstruct.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/12/8 13:38
 */
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDTO(PersonEntity entity);

    PersonEntity toEntiry(PersonDTO dto);


}
