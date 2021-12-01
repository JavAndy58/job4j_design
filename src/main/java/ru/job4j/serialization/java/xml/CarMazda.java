package ru.job4j.serialization.java.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "carMazda")
public class CarMazda {

    @XmlAttribute
    private String modelAuto;

    public CarMazda() {
    }

    public CarMazda(String modelAuto) {
        this.modelAuto = modelAuto;
    }

    @Override
    public String toString() {
        return "CarMazda{"
                + "modelAuto='" + modelAuto + '\''
                + '}';
    }
}
