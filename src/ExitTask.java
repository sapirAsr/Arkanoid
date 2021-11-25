/**
 * The type Exit task.
 */
public class ExitTask implements Task {
    @Override
    public Object run() {
        System.exit(2);
        return null;
    }
}
