package com.lei.jvm.utils.base.utils.birthday;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author leihaoyuan
 * @version 2024/6/6 10:30
 */
@Slf4j
@UtilityClass
public class BirthdayUtil {

    public static void main(String[] args) {
        log.info("解密生日={}", decryptBirthday(3788187338L));
        log.info("加密生日={}", encryptBirthday(20240606));
    }

    public static long decryptBirthday(long value) {
        if (value < 99999999) {
            return value;
        }

        value = (value & 0xFFFFFFF) ^ 0x151212;
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putLong(value);
        byte b = bytes[2];
        bytes[2] = bytes[1];
        bytes[1] = bytes[0];
        bytes[0] = b;
        buffer.rewind();
        value = buffer.getInt();
        return value;
    }

    public static long encryptBirthday(long value) {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putLong(value);
        byte b = bytes[0];
        bytes[0] = bytes[1];
        bytes[1] = bytes[2];
        bytes[2] = b;
        buffer.rewind();
        value = buffer.getInt();
        return ((value ^ 0x151212) | 0xE0000000L);
    }


}
