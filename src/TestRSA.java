import java.math.BigInteger;


/**
 * This class tests the RSA project code.
 * 
 * @author Jeff Goldsworthy
 *
 */
public class TestRSA {
    /**
     * Entry point for the RSA project code.
     * 
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        
//        BigInt bi1 = new BigInt("1011");
//        BigInt bi2 = new BigInt("110");
//        
//        System.out.println("Adding " + bi1.toString() + " + " + bi2.toString());
//        
//        BigInt result = bi1.basicAdd(bi2, null);
//        result.compact();
//        System.out.println(result.toString());
//        System.out.println();
//        
//        System.out.println("Multiplying " + bi1.toString() + " x " + bi2.toString());
//        result = bi1.multiply(bi2);
//        System.out.println(result.toString());
//        System.out.println();
//        
//        System.out.println("Subtracting " + bi1.toString() + " - " + bi2.toString());
//        result = bi1.subtract(bi2);
//        System.out.println(result.toString());
//        System.out.println();
//        
//        System.out.println("Mod " + bi1.toString() + " mod " + bi2.toString());
//        bi1.precomputeMods(bi2);
//        result = bi1.mod(bi2);
//        System.out.println(result.toString());
//        System.out.println();
//        
//        System.out.println("FULL CRYPTO TEST");
//        String myA = "1011111";
//        //String myB = "1011";
//        //String b = "11111101010";
//        String myB = "11111101010";
//        String myC = "110101";
//        
//        System.out.println("  I say a^b mod c = " + RSA.crypto(myA,myB,myC));
//        
//        BigInteger  aBig1 = new BigInteger(myA,2);
//        BigInteger bBig1 = new BigInteger(myB,2);
//        BigInteger cBig1 = new BigInteger(myC,2);
//
//        System.out.println("  Java says a^b mod c = " + aBig1.modPow(bBig1,cBig1).toString(2));
//        
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        System.out.println("MY UNIT TESTS");
//        // REMOVE AFTER TESTING.
//        //System.exit(0);

        System.out.println("COMPARE TO JAVA'S BIGINTEGER METHODS:");

        String a = "1011111";
        String b = "11111101010";
        String c = "110101";

        BigInteger  aBig = new BigInteger(a,2);
        BigInteger bBig = new BigInteger(b,2);
        BigInteger cBig = new BigInteger(c,2);

        System.out.println("  Java says a^b mod c = " + aBig.modPow(bBig,cBig).toString(2));
        System.out.println("  I say a^b mod c = " + RSA.crypto(a,b,c));
        System.out.println("CREATE KEY (Grandma, what big primes you have!):");

        String p = "11011011000001100111110011010010101111100001100100101111111100001101110010000001110111000101011111001000100111011110101101101000101100011110111011111011111110000011000110101001010110001011011000111001";
        String q = "11111110011110110101011111001101011110001010100110110100111110111101011101100000001011110011011101000000010110011000011101001101100111100011011110000001100001011000001111101001000011010110010100010111";
        String N = "1101100110111001111101110001011000010001101110000010001110101011000001001001100110100100111000011001010111101011010100110011111000001111001101011100000101110111100010011001110111101111101111110011111111111110010101111110010010101101000001011011110011111100110101110001101001101000001110101111010100001011010111111110100111100010011000111000000000000000000101111100110001011000110000011101110000011111";
        String e = "111";
        String phi = "1101100110111001111101110001011000010001101110000010001110101011000001001001100110100100111000011001010111101011010100110011111000001111001101011100000101110111100010011001110111101111101111110011111000100100110101100001000000001100110011101111101000010111111010100110011010000110001011110110011000000010011010000111011100101100000100110101100110000010100110100001011011000110010110111100000011010000";
        String d = "101110101001111101100110000100101110101010011101110101010110111000000011111100010110100011000001010110111110111001000111010110011100001111100100111011101111100010111111000110011010100011101101000100001011000111011100000011011100000111010101111110101110111111101101011111000111001100000100000011100100101100110100111110000110111011101100000000111001010010000100000100111000010101110011001101111000111";

        System.out.println("  p = " + p);
        System.out.println("  q = " + q);
        System.out.println("  N = " + N);
        System.out.println("  e = " + e);
        System.out.println("  phi = " + phi);
        System.out.println("  d = " + d);
        System.out.println("TEST ENCRYPTION/DECRYPTION:");

        String message = "Attack at dawn.";

        System.out.println("  Original Message = " + message);

        String clearBits = "1" + convertStringToBits(message);
        String encryptedBits = RSA.crypto(clearBits,e,N);
        String decryptedBits = RSA.crypto(encryptedBits,d,N);

        System.out.println("  Clear bits = " + clearBits);
        System.out.println("  Encrypted bits = " + encryptedBits);
        System.out.println("  Decrypted bits = " + decryptedBits);

        message = convertBitsToString(decryptedBits.substring(1));
        System.out.println("  Decrypted Message = " + message);
    }

    
    /**
     * This method converts characters to bits.
     * 
     * @param c
     * The target character to convert to bits.
     * 
     * @return
     * This method returns a string representation of the bits that make up the
     * target character. 
     */
    public static String convertCharToBits(char c) {
        String result = "";
        int n = (int)c;

        for (int bit=15; bit>=0; bit--) {
            if ( n>=Math.pow(2,bit) ) {
            	result += "1";
            	n -= Math.pow(2,bit); 
            }
            else { 
            	result += "0"; 
            }
        }
        
        return result;
    }
    

    /**
     * This method converts the specified string to a bit representation as
     * a string.
     * 
     * @param s
     * Target string to convert to bits.
     * 
     * @return
     * This method returns a string representing the bits of the specified
     * string.
     */
    public static String convertStringToBits(String s) {
        String result = "";
        
        for (int i=0; i<s.length(); i++) {
            result += convertCharToBits(s.charAt(i));
        }
        
        return result;
    }

    
    /**
     * This method converts the bits represented in the string back to the
     * character that they represent.
     * 
     * @param bits
     * Source bits to convert back to a character.
     * 
     * @return
     * This method returns a character which was presented by the source.
     */
    public static char convertBitsToChar(String bits) {
        int result = 0;
        for (int bit=0; bit<16; bit++) {
            if ( bits.charAt(bit)=='1' ) { result += Math.pow(2,15-bit); }
        }
        return (char)result;
    }

    
    /**
     * This method converts the bits represented in the source string back to
     * the string of characters that they represent.
     * 
     * @param bits
     * Source bits to convert back to a string.
     * 
     * @return
     * This method returns a string which is the series of characters 
     * represented by the source string of bits.
     */
    public static String convertBitsToString(String bits) {
        String result = "";
        String block;
        
        while (bits.length()!=0) {
            block = bits.substring(0,16);
            bits = bits.substring(16);
            result += convertBitsToChar(block);
        }
        
        return result;
    }
}
