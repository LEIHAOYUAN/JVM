package com.lei.jvm.utils.base.mapstruct;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.utils.base.mapstruct.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/12/8 13:51
 */
@Slf4j
public class App {

    public static void main(String[] args) {

        PersonDTO personDTO = PersonMapper.INSTANCE.toDTO(buildPersionEntity());
        log.info("entity转换dto={}", JSON.toJSONString(personDTO));

        PersonEntity personEntity = PersonMapper.INSTANCE.toEntiry(buildPersonDTO());
        log.info("dto转换entity={}", JSON.toJSONString(personEntity));
    }

    private static PersonEntity buildPersionEntity() {
        return new PersonEntity("AAA", "person-entity");
    }

    private static PersonDTO buildPersonDTO() {
        return new PersonDTO("AAA", "person-dto");
    }

}
