package ru.job4j.serialization.java.json;

public class CarMazda {

    private final String modelAuto;

    public CarMazda(String modelAuto) {
        this.modelAuto = modelAuto;
    }

    public String getModelAuto() {
        return modelAuto;
    }

    @Override
    public String toString() {
        return "CarMazda{"
                + "modelAuto='" + modelAuto + '\''
                + '}';
    }
}
