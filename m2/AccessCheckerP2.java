public class AccessCheckerP2 {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("SAME_CLASS".equals(accessorContext)) {
            return "ALLOWED";
        }

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "protected":
                if ("SAME_PACKAGE".equals(accessorContext) || 
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "default":
                return "SAME_PACKAGE".equals(accessorContext) ? "ALLOWED" : "DENIED";
            case "private":
            default:
                return "DENIED";
        }
    }

    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts == null) {
            return "None Denied";
        }

        for (int i = 0; i < attempts.length; i++) {
            if (attempts[i] == null || attempts[i].length < 2) continue;
            String mod = attempts[i][0];
            String context = attempts[i][1];

            if ("DENIED".equals(classifyAccess(mod, context))) {
                return mod + " via " + context + " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }
}
