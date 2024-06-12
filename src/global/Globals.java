package global;

public class Globals {
    private static boolean isCurrentUserAdmin = false;

    public static boolean isAdmin() {
        return isCurrentUserAdmin;
    }

    public static void setAdmin(boolean isAdmin) {
        Globals.isCurrentUserAdmin = isAdmin;
    }
}
