public class Client {

    public static void main(String[] args) {
        // Create a new client
        Controller controller = new Controller();

        // Connect to the server
        controller.boot();
    }
}