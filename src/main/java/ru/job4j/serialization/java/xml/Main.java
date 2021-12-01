package ru.job4j.serialization.java.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws JAXBException {

        Driver driver = new Driver("Petr", 40, true,
                new CarMazda("CX-5"), "Passport", "License");

        JAXBContext context = JAXBContext.newInstance(Driver.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(driver, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Driver result = (Driver) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
