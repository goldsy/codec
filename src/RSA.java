/**
 * This class is the RSA class. It doesn't do much except hide the creation
 * of the BigInt classes from the caller and call the powMod function.
 * 
 * @author Jeff Goldsworthy
 *
 */
public class RSA {
    /**
     * 
     * @param message
     * This is the value that will be raised to some power then mod'ed.
     * 
     * @param fmtExponent
     * This is the power by which the base value will be raised.
     * 
     * @param fmtModulus
     * This is the value to mod the result of the power.
     * 
     * @return
     * This method returns the result of the base value raised to the specified
     * power and mod'ed by the mod by value as a string representation of the
     * bits.
     */
	public static String crypto(String message, String fmtExponent, String fmtModulus) {
        BigInt binMsg = new BigInt(message);
        BigInt exponent = new BigInt(fmtExponent);
        BigInt modulus = new BigInt(fmtModulus);
        
        String returnValue = binMsg.powMod(exponent, modulus).toString();
        
		return returnValue;
	}
    
}
