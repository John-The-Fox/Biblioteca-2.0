package global;

public class Globals {
    private static boolean isCurrentUserAdmin = false;
    private static int currentUserId = -1;

    public static boolean isAdmin() {
        return isCurrentUserAdmin;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setAdmin(boolean isAdmin) {
        Globals.isCurrentUserAdmin = isAdmin;
    }

    public static void setCurrentUserId(int currentUserId) {
        Globals.currentUserId = currentUserId;
    }
}
