import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LibraryMember implements java.io.Serializable {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public LibraryMember() {
        // Public no-argument constructor required for JavaBeans
    }

    public String getMembershipId() {
        return membershipId;
    }

    // Write-once property implementation
    public void setMembershipId(String id) {
        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Standard JavaBean boolean getter naming convention
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    // Write-only property implementation (No getter exists anywhere)
    public void setSecurityAnswer(String answer) {
        if (answer == null) {
            this.securityAnswerHash = null;
            return;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(answer.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            this.securityAnswerHash = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            this.securityAnswerHash = String.valueOf(answer.hashCode());
        }
    }
}
