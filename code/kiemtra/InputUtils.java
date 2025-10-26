package code.kiemtra;

public class InputUtils {
    public static boolean ThoatNeuEnter(String input) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println("Quay lai!");
            return true;
        }
        return false;
    }
}
