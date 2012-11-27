
public class TestRSA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// This is a general idea on how to get the bits from a string.
		String s = "foo";
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		
		for (byte b : bytes)
		{
			int val = b;
			
			for (int i = 0; i < 8; i++)
			{
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			
			binary.append(' ');
		}
		
		System.out.println("'" + s + "' to binary: " + binary);
	}

}
