package ru.job4j.serialization.java.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "driver")
@XmlAccessorType(XmlAccessType.FIELD)
public class Driver {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private boolean sex;

    private CarMazda carMazda;

    @XmlElementWrapper(name = "documentses")
    @XmlElement(name = "documents")
    private String[] documents;

    public Driver() {
    }

    public Driver(String name, int age, boolean sex, CarMazda carMazda, String... documents) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.carMazda = carMazda;
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex
                + ", carMazda=" + carMazda
                + ", documents=" + Arrays.toString(documents)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Driver driver = new Driver("Petr", 40, true,
                new CarMazda("CX-5"), "Passport", "License");

        JAXBContext context = JAXBContext.newInstance(Driver.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(driver, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
