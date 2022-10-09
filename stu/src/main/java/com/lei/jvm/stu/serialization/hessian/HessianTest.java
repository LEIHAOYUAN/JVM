package com.lei.jvm.stu.serialization.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.lei.jvm.stu.serialization.SerializationInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

/**
 *  职能描述：hessian序列化
 *  @author leihaoyuan
 *  @version 2022/9/22 11:17
 */
@Slf4j
public class HessianTest {

    public static void main(String[] args) {
        SerializationInfo info = new SerializationInfo(100L, "hessian", new BigDecimal("111999999.56985623333338"));
        byte[] obj = serialize(info);
        log.info("hessian 序列化结果长度：{}", obj.length);
        byte[] obj2 = serialize2(info);
        log.info("hessian2 序列化结果长度：{}", obj2.length);
        byte[] other = jdkSerialize(info);
        log.info("JDK 序列化结果长度：{}", other.length);
        SerializationInfo student = deserialize2(obj2);
        log.info("反序列化结果：{}", student);
        // 精度丢失
        log.info("反序列化bigdecimal结果：{}", student.getAmount().toPlainString());
    }


    public static <T> byte[] serialize(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput output = new HessianOutput(os);
            output.writeObject(t);
            data = os.toByteArray();
        } catch (Exception e) {
            log.error("hessian序列化异常：{}", e.getMessage(), e);
        }
        return data;
    }

    public static <T> byte[] serialize2(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(os);
            output.writeObject(t);
            output.getBytesOutputStream().flush();
            output.completeMessage();
            output.close();
            data = os.toByteArray();
        } catch (Exception e) {
            log.error("hessian2序列化异常：{}", e.getMessage(), e);
        }
        return data;
    }

    public static <T> byte[] jdkSerialize(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(os);
            output.writeObject(t);
            output.flush();
            output.close();
            data = os.toByteArray();
        } catch (Exception e) {
            log.error("JDK序列化异常：{}", e.getMessage(), e);
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] data) {
        if (data == null) {
            return null;
        }
        Object result = null;
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            HessianInput input = new HessianInput(is);
            result = input.readObject();
        } catch (Exception e) {
            log.error("hessian反序列化异常：{}", e.getMessage(), e);
        }
        return (T) result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize2(byte[] data) {
        if (data == null) {
            return null;
        }
        Object result = null;
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            Hessian2Input input = new Hessian2Input(is);
            result = input.readObject();
        } catch (Exception e) {
            log.error("hessian2反序列化异常：{}", e.getMessage(), e);
        }
        return (T) result;
    }


}
