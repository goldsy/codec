/**
 * This class represents a big integer.
 * 
 * @author Jeff Goldsworthy
 *
 */
public class BigInt {
    // Data members
	int[] digits;
    
	// Positive shift indicates a shift, a negative shift indicates a reduce.
	int shifts = 0;
	
    /**
     * This ctor constructs a BigInt from the source string. The size of the
     * resulting BigInt is the length of the source string. A precondition
     * of this ctor is that all characters of the string are either a 1 or 0.
     * 
     * @param source
     * This is the source string to construct the big integer from.
     * 
     */
    public BigInt(String source) {
        // Construct a big int from the provided source string.
        digits = new int[source.length()];
        int sourceIndex = 0;
        
        for(int i = (source.length() - 1); i >= 0; --i) {
        	if (source.charAt(sourceIndex) == '1') {
        		digits[i] = 1;
        	}
        	else if (source.charAt(sourceIndex) == '0') {
        		digits[i] = 0;
        	}
        	else {
        		System.out.println("Invalid digit character at source index " 
        				+ sourceIndex + " in string: " + source);
                System.exit(1);
        	}
        }
    }
    
    
    /**
     * This ctor constructs a BigInt of the specified size and inits it to zero.
     * 
     * @param size
     * The target size.
     * 
     */
    public BigInt(int size) {
    	// Construct a big int of the specified size of value 0.
        digits = new int[size];
        
        // Init the digits array with zeros.
        for (int i = 0; i < digits.length; ++i) {
        	digits[i] = 0;
        }
    }

    
    /**
     * 
     * @param power
     * This is the power to raise this big integer by.
     * 
     * @param modBy
     * This is the value to mod the result of the power of this big integer by.
     * 
     * @return
     * This method returns the result of raising this big integer by the power
     * and mod'ing it by the mod value.
     */
	public BigInt modPow(BigInt power, BigInt modBy) {
		return null;
	}
    
	
	
    /**
     * This is the add method for BigInts. It add the bits contained in each
     * big integer.
     * 
     * @param rValue
     * The right value in the addition.
     * 
     * @return
     * This method returns a BigInt representing the sum of the two BigInts.
     */
	public BigInt add(BigInt rValue) {
		BigInt result = new BigInt(Math.max(size(), rValue.size()) + 1);
        
		boolean activeCarry = false;
        
        // Iterate over all bits of the two numbers, but we need to go one
		// beyond because there could be one last carry bit.  The for loop
		// could have checked for <= max(size), but checking for < Max(size)+1
		// is more intuitive.
		for (int i = 0; i < (Math.max(this.size(), rValue.size()) + 1); ++i) {
			// Check if this is the most significant bit iteration.
			if (i == (Math.max(size(), rValue.size()) + 1)) {
				// Check the carry only.
				if (activeCarry) {
					// Set the most significant bit to one.
					result.setBit(i, 1);
				}
				else {
					// The most significant bit was not needed.
					// Create a new result array with on less bit, copy the
					// result bits to the new array and set it in the resulting
					// big int.
					int[] temp = new int[result.size() - 1];

					for (int index = 0; index < temp.length; ++index) {
						temp[index] = result.getBit(index);
					}

					// Finally set the result to the temp.
					result.set(temp);
				}
			}
			// Check if the index has gone beyond the last bit of the lValue.
			else if (i > (size() - 1)) {
				if (rValue.getBit(i) == 1) {
					if (activeCarry) {
						// Leave the carry active.
						result.setBit(i, 0);
					}
					else {
						// Clear the carry active flag.
						activeCarry = false;
						result.setBit(i, 1);
					}
				}
				// The bit is a zero.
				else {
					if (activeCarry) {
						result.setBit(i, 1);
					}
					else {
						result.setBit(i, 0);
					}

					// Always clear the carry active flag.
					activeCarry = false;
				}
			}
			// Check if the index has gone beyond the last bit of the rValue.
			else if (i > (rValue.size() - 1)) {
				if (getBit(i) == 1) {
					if (activeCarry) {
						// Leave the carry active.
						result.setBit(i, 0);
					}
					else {
						// Clear the carry active flag.
						activeCarry = false;
						result.setBit(i, 1);
					}
				}
				// The bit is a zero.
				else {
					if (activeCarry) {
						result.setBit(i, 1);
					}
					else {
						result.setBit(i, 0);
					}

					// Always clear the carry active flag.
					activeCarry = false;
				}
			}
			// The two bits from each number plus the potential for a carry
			// must be accounted for.
			else {
				// Check if both the lValue and rValue bits are one.
				if (getBit(i) + rValue.getBit(i) == 2) {
					if (activeCarry) {
						// Leave the carry flag set.
						result.setBit(i, 1);
					}
					else {
						result.setBit(i, 0);

						// Set the carry bit flag.
						activeCarry = true;
					}
				}
				// Check if only one of the lValue or rValue bits are one.
				else if (getBit(i) + rValue.getBit(i) == 1) {
					if (activeCarry) {
						// Leave the carry flag set.
						result.setBit(i, 0);
					}
					else {
						// Leave the carry flag cleared.
						result.setBit(i, 1);
					}
				}
				// Both bits must be zero.
				else {
					if (activeCarry) {
						// Leave the carry flag set.
						result.setBit(i, 1);

						// Clear the carry flag.
						activeCarry = false;
					}
					else {
						// Leave the carry flag cleared.
						result.setBit(i, 0);
					}
				}
			}
		}
    
		return result;
	}
    
	public BigInt subtract(BigInt rValue) { 
        // Find the most significant digit where the lValue is 1 and rValue
		// is 0.
        
        int maxIndex = -1;
        
        // We can only check the lValue because we know that the lValue must
		// be greater than the rValue.
		for (int index = 0; index < size(); ++index) {
			if (getBit(index) == 1 && rValue.getBit(index) == 0) {
				maxIndex = index;
			}
		}
        
        // Check if that case doesn't exist.
		if (maxIndex == -1) {
            // All bits must be considered.
			maxIndex = size() - 1;
		}
	}
    
	/**
	 * This method returns the size of the BigInt.
     * 
	 * @return
     * This method returns the size of this BigInt.
	 */
	public int size() {
		return digits.length;
	}
	
	
    /**
     * This method gives access to the internal representation of the int.
     * 
     * @return
     * This method returns a reference to the internal bit array.
     */
	public int[] get() {
		return digits;
	}
    
    
    /**
     * This method sets the bit array to the source value.
     * 
     * @param source
     * This source arrays to get the digits array to.
     */
	public void set(int[] source) {
		digits = source;
	}
	
    
    /**
     * This method sets the specified bit to the specified value.
     * 
     * @param index
     * Target bit index.
     * 
     * @param value
     * Value to set the bit to.
     */
	public void setBit(int index, int value) {
		digits[index] = value;
	}
    
	
    /**
     * This method returns the bit value at the specified index.
     * 
     * @param index
     * The target index to get.
     * 
     * @return
     * This method returns the value found in the bit array at the specified
     * index.
     */
	public int getBit(int index) {
		return digits[index];
	}
}
