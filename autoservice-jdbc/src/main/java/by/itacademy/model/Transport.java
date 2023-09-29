package by.itacademy.model;

public class Transport {
    private final Integer id;
    private final String type;
    private final String model;
    private final Client client;

    public Transport(final Integer id, final String type, final String model, final Client client) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", client=" + client +
                '}';
    }
}
