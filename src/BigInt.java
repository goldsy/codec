/**
 * This class represents a big integer.
 * 
 * @author Jeff Goldsworthy
 *
 */
public class BigInt {
    // Data members
	int[] digits;
    
	// Positive shift indicates a reduce, a negative shift indicates a shift.
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
            
        	// Increment the source index.
        	++sourceIndex;
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
     * This method is a unary add that adds the rValue into this BigInt (the 
     * lValue). It add the bits contained in each big integer.
     * 
     * Precondition: The size of this BigInt must be large enough to handle 
     * having the lValue added into it including the possibility of a final 
     * carry.
     * 
     * @param rValue
     * The container in which to place the result.  This may already have a
     * value, such as an accumulator.
     * 
     * @return
     * This method returns a BigInt representing the sum of the two BigInts.
     */
	public BigInt add(BigInt rValue) {
//        if (result == null) {
//        	result = new BigInt(Math.max(size(), rValue.size()) + 1);
//        }
        if (rValue.size() > size()) {
        	System.out.println("Possible buffer overflow. This BigInt must"
        			+ " be larger in size than the value being added in.");
            System.exit(1);
        }
        
		boolean activeCarry = false;
        
        // Iterate over all bits of the two numbers, but we need to go one
		// beyond because there could be one last carry bit.  The for loop
		// could have checked for <= max(size), but checking for < Max(size)+1
		// is more intuitive.
		for (int i = 0; i < (size()); ++i) {
			// Check if this is the most significant bit iteration.
			// The lValue is always larger than the rValue and therefore will
			// never have more than a carry bit added in.
			if (i == size()) {
				// Check the carry only.
				if (activeCarry) {
                    if (getBit(i) == 1) {
                        // Uh oh, we got a buffer overflow.
                        System.out.println("BUFFER OVERFLOW. This BigInt isn't large enough to add in the rValue.");
                        System.exit(1);
                    }
                    
					// Set the most significant bit to one.
					setBit(i, 1);
				}
				else {
// TODO: (goldsy) REMOVE AFTER TESTING.
//					// The most significant bit was not needed.
//					// Create a new result array with on less bit, copy the
//					// result bits to the new array and set it in the resulting
//					// big int.
//					int[] temp = new int[result.size() - 1];
//
//					for (int index = 0; index < temp.length; ++index) {
//						temp[index] = result.getBit(index);
//					}
//
//					// Finally set the result to the temp.
//					result.setArray(temp);
				}
			}
            // TODO: (goldsy) REMOVE AFTER TESTING.  The lValue is always larger than the rValue.
			// Check if the index has gone beyond the last bit of the lValue.
//			else if (i > (size() - 1)) {
//				if (rValue.getBit(i) == 1) {
//					if (activeCarry) {
//						// Leave the carry active.
//						result.setBit(i, 0);
//					}
//					else {
//						// Clear the carry active flag.
//						activeCarry = false;
//						result.setBit(i, 1);
//					}
//				}
//				// The bit is a zero.
//				else {
//					if (activeCarry) {
//						result.setBit(i, 1);
//					}
//					else {
//						result.setBit(i, 0);
//					}
//
//					// Always clear the carry active flag.
//					activeCarry = false;
//				}
//			}
			// Check if the index has gone beyond the last bit of the rValue.
			else if (i > (rValue.size() - 1)) {
				if (getBit(i) == 1) {
					if (activeCarry) {
						// Leave the carry active.
						setBit(i, 0);
					}
					else {
                        // Leave lValue bit[i] == 1.
						// Clear the carry active flag.
						activeCarry = false;
					}
				}
				// The bit is a zero.
				else {
					if (activeCarry) {
						setBit(i, 1);
					}
                    
					// If no carry leave lValue bit[i] == 0.

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
                        // Leave lValue bit[i] == 1.
					}
					else {
						setBit(i, 0);

						// Set the carry bit flag.
						activeCarry = true;
					}
				}
				// Check if only one of the lValue or rValue bits are one.
				else if (getBit(i) + rValue.getBit(i) == 1) {
					if (activeCarry) {
						// Leave the carry flag set.
						// Set the bit to zero. We don't know which is zero.
						setBit(i, 0);
					}
					else {
						// Leave the carry flag cleared.
						// Set the bit to zero. We don't know which is 1.
						setBit(i, 1);
					}
				}
				// Both bits must be zero.
				else {
					if (activeCarry) {
						setBit(i, 1);

						// Clear the carry flag.
						activeCarry = false;
					}
                    
                    // If there is no active carry:
					// Leave the carry flag cleared.
					// Leave the bit lValue[i] == 0.
				}
			}
		}
    
		return this;
	}
    
	
	
    /**
     * This is the add method for BigInts. It add the bits contained in each
     * big integer.
     * 
     * @param rValue
     * The right value in the addition.
     * 
     * @param result
     * The container in which to place the result.  This may already have a
     * value, such as an accumulator.
     * 
     * @return
     * This method returns a BigInt representing the sum of the two BigInts.
     */
	public BigInt basicAdd(BigInt rValue, BigInt result) {
        if (result == null) {
        	result = new BigInt(Math.max(size(), rValue.size()) + 1);
        }
        
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
					result.setArray(temp);
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

    
	/**
	 * This is the subtract method. It subtracts the right value from this
	 * BigInt which is the left value.
     * 
	 * @param rValue
     * The right value in the equation.
     * 
	 * @return
     * This method returns the result of the subtraction.
	 */
	public BigInt subtract(BigInt rValue) { 
        // Find the most significant digit where the lValue is 1 and rValue
		// is 0.
        
        int maxIndex = -1;
        
        // We only have to check the lValue because we know that the lValue must
		// be greater than the rValue. We won't have negatives in our 
        // implementation.
		for (int index = 0; index < size(); ++index) {
			if (getBit(index) == 1 && rValue.getBit(index) == 0) {
				maxIndex = index;
			}
		}
        
        // Check if that case doesn't exist.
		if (maxIndex == -1) {
            // TODO: (goldsy) REMOVE AFTER TESTING.
            System.out.println("HOLD IT! WE GOT TWO NUMBERS THAT WILL RESULT IN A NEGATIVE OR ZERO (WHICH WE MIGHT HAVE).");
            // All bits must be considered.
			maxIndex = size() - 1;
		}
        
        // The result will never be any more than the largest value.
		BigInt result = new BigInt(Math.max(size(), rValue.size()));
        
		// Now that we found that bit. Borrow from it... Set maxIndex bit to 0.
        setBit(maxIndex, 0);
        
		// Add 1 to every bit less than maxIndex.
        for (int index = 0; index < maxIndex; ++index) {
        	// Note: Add 1 to bits that are already 1 will result in an int of 2
        	//		which isn't binary, but this is a non-std subtraction algorithm.
        	digits[index] += 1;
        }
        
        // Set the carry bit flag.
        boolean carryFlag = true;
        
        // Do subtraction.
        for (int index = 0; index < size(); ++index) {
        	result.setBit(index, (digits[index] - rValue.getBit(index)));
        }
        
        // Now account for the carry flag from the borrow and carry for any
        // remaining 2s.
        for (int index = 0; index < result.size(); ++index) {
        	if (carryFlag) {
        		if (result.getBit(index) == 0) {
                    // Add 1 to the bit.
        			result.setBit(index, 1);
                    
        			// Reset the carry flag.
        			carryFlag = false;
        		}
        		else if (result.getBit(index) == 1) {
        			// Set the result bit to zero and keep the carry.
                    result.setBit(index, 0);
        		}
        		else {
        			// The value must have been a 2 so set the value to 1 and
        			// keep the carry.
                    result.setBit(index, 1);
        		}
        	}
        	else {
        		// There is no carry. A two has an implicit carry. The other
        		// two cases won't change the value.
                if (result.getBit(index) == 2) {
                    // A 2 is a binary 10.
                	result.setBit(index, 0);
                	
                	// Set the carry flag.
                	carryFlag = true;
                }
        	}
        }
        
        // TODO: (goldsy) Not sure if we need to but resize the result to the
        // 		exact size.
        result.compact();
        
        return result;
	}
    
	
    /**
     * This method returns the product of this BigInt with the right value.
     * 
     * @param rValue
     * The right value of the multiplication equation.
     * 
     * @return
     * This method returns the product of the multiplication.
     */
	public BigInt multiply(BigInt rValue) {
        // The accumulator needs to be the size of the product of the size of
		// the two numbers.
		// TODO: (goldsy) NEED TO REWRITE THE ADD TO HANDLE PASSING THE ACCUMULATOR. NO MEMORY REALLOCATION.
		BigInt accumulator = new BigInt(size() + rValue.size());
        
        // Break up rValue into linear combination and add the shifted lValue
		// pieces into the accumulator.
        for (int index = 0; index < rValue.size(); ++index) {
            // If the rValue bit is 1 add the lValue otherwise 0 x anything =
        	// zero so skip it.
        	if (rValue.getBit(index) == 1) {
        		accumulator.add(this);
        	}
            
        	// Shift the lValue. Shifted after the first add because lValue is
        	// already 2^0.
        	shifts -= 1;
        }
        
        // After multiple restore the shift value to zero.
        shifts = 0;
		
		return accumulator;
	}
    
	
	private BigInt mod(String modulus) {
		int numBits = size();
		
		int precomputed[] = new int[numBits - 1];
	}
    
	
	public BigInt powMod(String exponent, String modulus) {
		
	}
    
	
	private precomputeMods()
    
    
	/**
	 * This method returns the size of the BigInt.
     * 
	 * @return
     * This method returns the size of this BigInt.
	 */
	public int size() {
        // We need to adjust the size by the shifts. If we did a shift then
		// we added digits by adding least signification bits. The least
		// significant bit is initially at the zero index in the array.
		return (digits.length - shifts);
	}
	
	
    /**
     * This method gives access to the internal representation of the int.
     * 
     * @return
     * This method returns a reference to the internal bit array.
     */
	public int[] getArray() {
		return digits;
	}
    
    
    /**
     * This method sets the bit array to the source value.
     * 
     * @param source
     * This source arrays to get the digits array to.
     */
	public void setArray(int[] source) {
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
        // Any value more significant than size() has to be zero.
        if ((index >= size()) || (adjustedIndex(index) < 0)) {
        	return 0;
        }
        else {
        	return digits[adjustedIndex(index)];
        }
	}
    
	
    /**
     * This method determines what the actual index is into the underlying 
     * array.  It is the responsibility of the caller not to access outside the
     * bounds of the underlying array.
     * 
     * @param index
     * The requested index.
     * 
     * @return
     * This method returns the requested index adjusted by the number of shifts.
     * 
     */
	public int adjustedIndex(int index) {
		return (index + shifts);
	}
    
	
    /**
     * This method compacts the BigInt by reducing the underlying array by
     * the size of any most significant zeros. In other words 00111 will be
     * compacted by 2 resulting in an underlying array of three locations
     * instead of 5. If the most significant bit is a one then this method
     * does nothing.
     */
	public void compact() {
        int targetSize = -1;
        
        for (int index = (size() - 1); (index > 0) && (targetSize == -1); --index) {
        	if (getBit(index) == 1) {
        		targetSize = index + 1;
        	}
        }
        
        if (targetSize == size()) {
            // There is nothing to do.
        	return;
        }
        
        // Create a new array of the new size.
        int[] temp = new int[targetSize];
        
        // Copy the bits from the original into this one.
        for (int index = 0; index < targetSize; ++index) {
        	temp[index] = digits[index];
        }
        
        digits = temp;
	}
    
	
	public String toString() {
        StringBuilder temp = new StringBuilder(size());
        
		for (int i = (size() - 1); i >= 0; --i) {
			temp.append(digits[i]);
		}
        
		return temp.toString();
	}
}
