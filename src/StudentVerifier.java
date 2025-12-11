public class StudentVerifier {


        // Public RSA data (can be known by students) ---
        // These must match the teacher side parameters.
        private static final int N = 3233;   // modulus for RSA
        private static final int E = 17;     // public KEY exponent

        // TODO: Exercise 1
        // STUDENT TASK (Exercise 1):
        // Implement a simple hash function:
        //  - Start with hash = 0
        //  - For each character: hash = (hash + c) % 1000
        //  - Return hash
        // public static int simpleHash(String message) { ... }



        // RSA: modular exponentiation (base^exponent mod mod)
        // You may assume this method is correct and just use it later
        // when verifying the digital signature.
        public static int modPow(int base, int exponent, int mod) {
            long result = 1;
            long b = base % mod;
            int e = exponent;

            while (e > 0) {
                if ((e & 1) == 1) {
                    result = (result * b) % mod;
                }
                b = (b * b) % mod;
                e = e >> 1;
            }

            return (int) result;
        }

        // TODO: In a later exercise you will implement:
        // public static boolean verifyMessage(String message, int signature) {
        // 1. Recompute the hash from the received message

        // 2. Recover the hash from the signature using public key

        // 3. Compare both hashes

        // }

        public static void main(String[] args) {
            String receivedMessage = "Exam will be on Friday at 10:00.";
            int receivedSignature = 330; // teacher's signature for the message above

            System.out.println("=== STUDENT SIDE ===");
            System.out.println("Received message  : " + receivedMessage);
            System.out.println("Received signature: " + receivedSignature);
            // In a later exercise, you will:

            //  - call verifyMessage
            //boolean isValid = verifyMessage(receivedMessage, receivedSignature);
            //  - print true/false
        }
    }

