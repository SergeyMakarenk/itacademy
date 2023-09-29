package by.itacademy.model;

public class Transport {
    private final String type;
    private final String model;
    private final Client client;

    public Transport(String type, String model, Client client) {
        this.type = type;
        this.model = model;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", client=" + client +
                '}';
    }
}
