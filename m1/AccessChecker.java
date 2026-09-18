import java.util.LinkedHashMap;
import java.util.Map;

public class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("SAME_CLASS".equals(accessorContext)) {
            return "ALLOWED";
        }
        
        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "protected":
            case "default":
                return "SAME_PACKAGE".equals(accessorContext) ? "ALLOWED" : "DENIED";
            case "private":
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        // Initialize maps to preserve fixed order and handle modifiers with zero attempts
        Map<String, Integer> allowedCounts = new LinkedHashMap<>();
        Map<String, Integer> deniedCounts = new LinkedHashMap<>();

        String[] modifiers = {"private", "default", "protected", "public"};
        for (String mod : modifiers) {
            allowedCounts.put(mod, 0);
            deniedCounts.put(mod, 0);
        }

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt == null || attempt.length < 2) continue;
                String mod = attempt[0];
                String context = attempt[1];

                if (!allowedCounts.containsKey(mod)) continue;

                String result = classifyAccess(mod, context);
                if ("ALLOWED".equals(result)) {
                    allowedCounts.put(mod, allowedCounts.get(mod) + 1);
                } else {
                    deniedCounts.put(mod, deniedCounts.get(mod) + 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < modifiers.length; i++) {
            String mod = modifiers[i];
            sb.append(mod)
              .append(": ")
              .append(allowedCounts.get(mod))
              .append(" allowed / ")
              .append(deniedCounts.get(mod))
              .append(" denied");
            if (i < modifiers.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}

class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember() {}
}