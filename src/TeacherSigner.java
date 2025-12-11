public class TeacherSigner {


        // RSA toy parameters
        // These are hard-coded for the lab. Students should NOT see D for the phase
        private static final int N = 3233;   // modulus Hem öğretmen hem öğrenci tarafından bilinir.
        private static final int E = 17;     // public Key exponent (public key)
        private static final int D = 2753;   // private Key exponent (private key) Bu sayı sadece öğretmende bulunur.
        // Öğrenciler bu sayıyı görmez. İmza bu gizli sayı ile oluşturulur.

        // Simple hash function (educational, not cryptographic)
        // Students are gonna create the same hash function for their sides
        public static int simpleHash(String message) {//m=AB
            int hash = 0;
            for (int i = 0; i < message.length(); i++) {
                char c = message.charAt(i); //A=65 B=66
                hash = (hash + c) % 1000; // (0+65)%1000=65 // (65+66)%1000 = 131    keep hash in range 0–999 (< N)
            }
            return hash;
        }

        // Modular exponentiation: base-exponent mod mod
        public static int modPow(int base, int exponent, int mod) {
            long result = 1;
            long b = base % mod;
            int e = exponent;

            while (e > 0) {
                if ((e & 1) == 1) {       // if e is odd
                    result = (result * b) % mod;
                }
                b = (b * b) % mod;        // square
                e = e >> 1;               // divide by 2
            }

            return (int) result;
        }

        // Sign the hash with the private key D
        public static int signHash(int hashValue) {

            return modPow(hashValue, D, N);
        }

        // Convenience method: sign the whole message
        public static int signMessage(String message) {
            int h = simpleHash(message);
            return signHash(h);
        }

        public static void main(String[] args) {
            // You can change this message if you want
            String message = "Exam will be on Friday at 10:00.";

            int hashValue = simpleHash(message);
            int signature = signMessage(message);

            System.out.println("=== TEACHER SIDE ===");
            System.out.println("Message to send to students:");
            System.out.println(message);
            System.out.println();
            System.out.println("Internal hash (for teacher info): " + hashValue);
            System.out.println("Signature to send to students  : " + signature);

            System.out.println();
            System.out.println("Public key (students can know this) (N:modulus E:Public key):");
            System.out.println("N = " + N + ", E = " + E);

            // For this exact message and this toy RSA:
            // hash   = 564
            // signature = 330
        }
    }


