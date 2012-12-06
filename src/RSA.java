
public class RSA {
    /**
     * 
     * @param message
     * This is the value that will be raised to some power then mod'ed.
     * 
     * @param exponent
     * This is the power by which the base value will be raised.
     * 
     * @param modulus
     * This is the value to mod the result of the power.
     * 
     * @return
     * This method returns the result of the base value raised to the specified
     * power and mod'ed by the mod by value as a string representation of the
     * bits.
     */
	public static String crypto(String message, String exponent, String modulus) {
        BigInt binMsg = new BigInt(message);
        
        String returnValue = binMsg.powMod(exponent, modulus).toString();
        
		return returnValue;
	}
    
}
