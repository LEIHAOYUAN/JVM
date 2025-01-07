//package com.lei.jvm.stu.jaxb;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlRootElement;
//
//@XmlRootElement(name = "MyRootElement")
//@XmlAccessorType(XmlAccessType.FIELD)
//public class AccessorType {
//
//    public String name;
//    private String address;
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public static void main(String[] args) throws Exception {
//        AccessorType root = new AccessorType();
//        root.setName("ROSS");
//        root.setAddress("GZ");
//
//        JAXBContext context = JAXBContext.newInstance(AccessorType.class);
//        Marshaller m = context.createMarshaller();
//        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        m.marshal(root, System.out);
//    }
//}