public class StudentVerifierFull {


        // Public RSA data (can be known by students)
        // These must match the teacher side.
        private static final int N = 3233;   // modulus
        private static final int E = 17;     // public key exponent

        // STUDENT TASK (Exercise 1):
        // Implement a simple hash function:
        //  - Start with hash = 0
        //  - For each character: hash = (hash + c) % 1000
        //  - Return hash
        public static int simpleHash(String message) {
            int hash = 0;
            for (int i = 0; i < message.length(); i++) {
                char c = message.charAt(i);
                hash = (hash + c) % 1000;   // educational hash
            }
            return hash;
        }

        // Support: modular exponentiation (base^exponent mod mod) RSA
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

        // STUDENT TASK (Exercise 2):
        // Verify a message and its signature using only the public key (E, N)
        public static boolean verifyMessage(String message, int signature) {
            // 1. Recompute the hash from the received message
            // 1. Gelen mesajdan hash'i kendimiz hesaplıyoruz
            int hashFromMessage = simpleHash(message);

            // 2. Recover the hash from the signature using public key
            int hashFromSignature = modPow(signature, E, N);

            // 3. Compare both hashes
            return hashFromMessage == hashFromSignature;
        }

        public static void main(String[] args) {
            // These values are provided by the teacher in the lab
            String receivedMessage = "Exam will be on Friday at 10:00.";
            int receivedSignature = 330; // teacher's signature for the message above

            System.out.println("=== STUDENT SIDE ===");
            System.out.println("Received message  : " + receivedMessage);
            System.out.println("Received signature: " + receivedSignature);

            boolean isValid = verifyMessage(receivedMessage, receivedSignature);

            System.out.println("\nIs the message valid (from the real teacher)? " + isValid);

            // OPTIONAL: you can also test what happens if the message is changed
            String tamperedMessage = "Exam will be on Monday at 10:00.";
            boolean isValidTampered = verifyMessage(tamperedMessage, receivedSignature);
            System.out.println("\nIf someone changes the message to:");
            System.out.println(tamperedMessage);
            System.out.println("Is it still valid with the same signature? " + isValidTampered);
        }
    }

