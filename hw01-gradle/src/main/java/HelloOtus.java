import com.google.common.base.MoreObjects;

public class HelloOtus {
private final String name;

    public HelloOtus(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(new HelloOtus("Aleksei"));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("HelloOtus")
                .add("name", name)
                .toString();
    }
}
