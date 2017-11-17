//java.lang.Integer

/*
    java.lang 
    Class Integer

    java.lang.Object
      |
      +-java.lang.Number
            |
            +-java.lang.Integer

    All Implemented Interfaces: 
    Serializable, Comparable<Integer> 
*/

package java.lang;

public final class Integer extends Number implements Comparable<Integer>
{
    //常量
    public static final int MIN_VALUE = 0x80000000;      //整数最小值，-2^31
    public static final int MAX_VALUE = 0x7fffffff;      //整数最大值，2^31-1

    public static final Class<Integer> TYPE//The Class instance representing the primitive type int. 
    public static final int SIZE//The number of bits used to represent an int value in two's complement binary form. Since: 1.5 

    private final int value;
    
    //构造方法
    public Integer(int value) 
    {
        this.value = value;
    }

    public Integer(String s) throws NumberFormatException 
    {
        this.value = parseInt(s, 10);
    }
    
    public boolean equals(Object obj)    //覆盖Object类中方法
    {
        if (obj instanceof Integer) 
        {
            return value == ((Integer)obj).intValue();
        }
        return false;
    }
    
    public String toString()             //覆盖Object类中方法
    {
        return String.valueOf(value);
    }
    
    public int hashCode()                //覆盖Object类中方法
    {
        return value;
    }
    
    //对象取值方法，覆盖父类Number中方法
    public byte   byteValue()
    public short  shortValue()

    //对象取值方法，实现父类Number中的抽象方法
    public int    intValue()
    public long   longValue()
    public float  floatValue()
    public double doubleValue()

    //静态数据转换方法
    public static String toString(int i)           //int转换成十进制字符串
    public static String toString(int i,int radix)

    public static int parseInt(String s,int radix) throws NumberFormatException
    public static int parseInt(String s) throws NumberFormatException  //字符串转成int
    {
        return parseInt(s,10);
    }
    
    public static Integer valueOf(int i)
    public static Integer valueOf(String s) throws NumberFormatException
    public static Integer valueOf(String s,int radix) throws NumberFormatException
    
    public static String toBinaryString(int i)     //int转换成二进制字符串
    public static String toOctalString(int i)      //int转换成八进制字符串
    public static String toHexString(int i)        //int转换成十六进制字符串

    public static Integer getInteger(String nm)
    public static Integer getInteger(String nm,int val)
    public static Integer getInteger(String nm,Integer val)
                                 
    public static Integer decode(String nm) throws NumberFormatException
                      
    public int compareTo(Integer anotherInteger) 
    {
        int thisVal = this.value;
        int anotherVal = anotherInteger.value;
        return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
    }

    //Since 1.5
    public static int highestOneBit(int i)
    public static int lowestOneBit(int i)

    public static int numberOfLeadingZeros(int i)   
    public static int numberOfTrailingZeros(int i)

    public static int bitCount(int i)

    public static int rotateLeft(int i,int distance)
                             
    public static int rotateRight(int i,int distance)
    public static int reverse(int i)
    public static int signum(int i)
    public static int reverseBytes(int i)
}







/*
 * @(#)Integer.java 1.90 04/05/11
 *
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.lang;

public final class Integer extends Number implements Comparable<Integer> 
{
    public static final int   MIN_VALUE = 0x80000000;
    public static final int   MAX_VALUE = 0x7fffffff;

    public static final Class<Integer>  TYPE = (Class<Integer>) Class.getPrimitiveClass("int");

    final static char[] digits = 
    {
    '0' , '1' , '2' , '3' , '4' , '5' ,
    '6' , '7' , '8' , '9' , 'a' , 'b' ,
    'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
    'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
    'o' , 'p' , 'q' , 'r' , 's' , 't' ,
    'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    public static String toString(int i, int radix) 
    {

        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
        radix = 10;

        if (radix == 10) 
        {
            return toString(i);
        }

        char buf[] = new char[33];
        boolean negative = (i < 0);
        int charPos = 32;

        if (!negative) 
        {
            i = -i;
        }

        while (i <= -radix) 
        {
            buf[charPos--] = digits[-(i % radix)];
            i = i / radix;
        }
        buf[charPos] = digits[-i];

        if (negative) 
        {
            buf[--charPos] = '-';
        }

         return new String(buf, charPos, (33 - charPos));
    }

    public static String toHexString(int i) 
    {
        return toUnsignedString(i, 4);
    }

    public static String toOctalString(int i) 
    {
        return toUnsignedString(i, 3);
    }

    public static String toBinaryString(int i) 
    {
        return toUnsignedString(i, 1);
    }

    private static String toUnsignedString(int i, int shift) 
    {
        char[] buf = new char[32];
        int charPos = 32;
        int radix = 1 << shift;
        int mask = radix - 1;
        do 
        {
            buf[--charPos] = digits[i & mask];
            i >>>= shift;
        } while (i != 0);

        return new String(buf, charPos, (32 - charPos));
    }


    final static char [] DigitTens = {
    '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
    '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
    '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
    '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
    '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
    '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
    '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
    '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
    '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
    '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
    } ; 

    final static char [] DigitOnes = { 
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    } ;

    public static String toString(int i) 
    {
        if (i == Integer.MIN_VALUE)
            return "-2147483648";
        int size = (i < 0) ? stringSize(-i) + 1 : stringSize(i);
        char[] buf = new char[size];
        getChars(i, size, buf);
        return new String(0, size, buf);
    }

    static void getChars(int i, int index, char[] buf) 
    {
        int q, r;
        int charPos = index;
        char sign = 0;

        if (i < 0) 
        { 
            sign = '-';
            i = -i;
        }

        // Generate two digits per iteration
        while (i >= 65536) 
        {
            q = i / 100;
        // really: r = i - (q * 100);
            r = i - ((q << 6) + (q << 5) + (q << 2));
            i = q;
            buf [--charPos] = DigitOnes[r];
            buf [--charPos] = DigitTens[r];
        }

        // Fall thru to fast mode for smaller numbers
        // assert(i <= 65536, i);
        for (;;) 
        { 
            q = (i * 52429) >>> (16+3);
            r = i - ((q << 3) + (q << 1));  // r = i-(q*10) ...
            buf [--charPos] = digits [r];
            i = q;
            if (i == 0) break;
        }
        if (sign != 0) 
        {
            buf [--charPos] = sign;
        }
    }

    final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
                                      99999999, 999999999, Integer.MAX_VALUE };

    // Requires positive x
    static int stringSize(int x) 
    {
        for (int i=0; ; i++)
            if (x <= sizeTable[i])
                return i+1;
    }
    
    public static int parseInt(String s, int radix)
        throws NumberFormatException
    {
        if (s == null) 
        {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) 
        {
            throw new NumberFormatException("radix " + radix +
                        " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) 
        {
            throw new NumberFormatException("radix " + radix +
                        " greater than Character.MAX_RADIX");
        }

        int result = 0;
        boolean negative = false;
        int i = 0, max = s.length();
        int limit;
        int multmin;
        int digit;

        if (max > 0) 
        {
            if (s.charAt(0) == '-') 
            {
                negative = true;
                limit = Integer.MIN_VALUE;
                i++;
            }
            else 
            {
                limit = -Integer.MAX_VALUE;
            }
            multmin = limit / radix;
            if (i < max) 
            {
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) 
                {
                    throw NumberFormatException.forInputString(s);
                }
                else
                {
                    result = -digit;
                }
            }
            while (i < max) 
            {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) 
                {
                    throw NumberFormatException.forInputString(s);
                }
                if (result < multmin) 
                {
                    throw NumberFormatException.forInputString(s);
                }
                result *= radix;
                if (result < limit + digit) 
                {
                    throw NumberFormatException.forInputString(s);
                }
                result -= digit;
            }
        }
        else 
        {
            throw NumberFormatException.forInputString(s);
        }
        if (negative) 
        {
            if (i > 1) 
            {
                return result;
            }
            else
            {    /* Only got "-" */
                throw NumberFormatException.forInputString(s);
            }
        }
        else
        {
            return -result;
        }
    }

    public static int parseInt(String s) throws NumberFormatException 
    {
        return parseInt(s,10);
    }

    public static Integer valueOf(String s, int radix) throws NumberFormatException 
    {
        return new Integer(parseInt(s,radix));
    }

    public static Integer valueOf(String s) throws NumberFormatException
    {
        return new Integer(parseInt(s, 10));
    }

    private static class IntegerCache 
    {
        private IntegerCache(){}

        static final Integer cache[] = new Integer[-(-128) + 127 + 1];

        static 
        {
            for(int i = 0; i < cache.length; i++)
            cache[i] = new Integer(i - 128);
        }
    }

    public static Integer valueOf(int i) 
    {
        final int offset = 128;
        if (i >= -128 && i <= 127) 
        { // must cache 
            return IntegerCache.cache[i + offset];
        }
        return new Integer(i);
    }

    /**
     * The value of the <code>Integer</code>.
     *
     * @serial
     */
    private final int value;

    public Integer(int value) 
    {
        this.value = value;
    }

    public Integer(String s) throws NumberFormatException 
    {
        this.value = parseInt(s, 10);
    }

    /**
     * Returns the value of this <code>Integer</code> as a
     * <code>byte</code>.
     */
    public byte byteValue() 
    {
        return (byte)value;
    }

    /**
     * Returns the value of this <code>Integer</code> as a
     * <code>short</code>.
     */
    public short shortValue() {
    return (short)value;
    }

    /**
     * Returns the value of this <code>Integer</code> as an
     * <code>int</code>.
     */
    public int intValue() {
    return value;
    }

    /**
     * Returns the value of this <code>Integer</code> as a
     * <code>long</code>.
     */
    public long longValue() {
    return (long)value;
    }

    /**
     * Returns the value of this <code>Integer</code> as a
     * <code>float</code>.
     */
    public float floatValue() {
    return (float)value;
    }

    /**
     * Returns the value of this <code>Integer</code> as a
     * <code>double</code>.
     */
    public double doubleValue() {
    return (double)value;
    }

    /**
     * Returns a <code>String</code> object representing this
     * <code>Integer</code>'s value. The value is converted to signed
     * decimal representation and returned as a string, exactly as if
     * the integer value were given as an argument to the {@link
     * java.lang.Integer#toString(int)} method.
     *
     * @return  a string representation of the value of this object in
     *          base&nbsp;10.
     */
    public String toString() 
    {
        return String.valueOf(value);
    }

    public int hashCode() 
    {
        return value;
    }

    public boolean equals(Object obj) 
    {
        if (obj instanceof Integer) 
        {
            return value == ((Integer)obj).intValue();
        }
        return false;
    }

    public static Integer getInteger(String nm) 
    {
        return getInteger(nm, null);
    }

    public static Integer getInteger(String nm, int val) 
    {
        Integer result = getInteger(nm, null);
        return (result == null) ? new Integer(val) : result;
    }

    public static Integer getInteger(String nm, Integer val) 
    {
        String v = null;
        try 
        {
            v = System.getProperty(nm);
        }
        catch (IllegalArgumentException e) 
        {
        }
        catch (NullPointerException e) 
        {
        }
        
        if (v != null) 
        {
            try 
            {
                return Integer.decode(v);
            }
            catch (NumberFormatException e) 
            {
            }
        }
        return val;
    }

    public static Integer decode(String nm) throws NumberFormatException 
    {
        int radix = 10;
        int index = 0;
        boolean negative = false;
        Integer result;

        // Handle minus sign, if present
        if (nm.startsWith("-")) {
            negative = true;
            index++;
        }

        // Handle radix specifier, if present
        if (nm.startsWith("0x", index) || nm.startsWith("0X", index)) {
        index += 2;
            radix = 16;
        }
        else if (nm.startsWith("#", index)) {
        index ++;
            radix = 16;
        }
        else if (nm.startsWith("0", index) && nm.length() > 1 + index) {
        index ++;
            radix = 8;
        }

        if (nm.startsWith("-", index))
            throw new NumberFormatException("Negative sign in wrong position");

        try {
            result = Integer.valueOf(nm.substring(index), radix);
            result = negative ? new Integer(-result.intValue()) : result;
        } catch (NumberFormatException e) {
            // If number is Integer.MIN_VALUE, we'll end up here. The next line
            // handles this case, and causes any genuine format error to be
            // rethrown.
            String constant = negative ? new String("-" + nm.substring(index))
                                       : nm.substring(index);
            result = Integer.valueOf(constant, radix);
        }
        return result;
    }

    public int compareTo(Integer anotherInteger) 
    {
        int thisVal = this.value;
        int anotherVal = anotherInteger.value;
        return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
    }


    public static final int SIZE = 32;
 
    public static int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        return i - (i >>> 1);
    }
 
    /**
     * Returns an <tt>int</tt> value with at most a single one-bit, in the
     * position of the lowest-order ("rightmost") one-bit in the specified
     * <tt>int</tt> value.  Returns zero if the specified value has no
     * one-bits in its two's complement binary representation, that is, if it
     * is equal to zero.
     *
     * @return an <tt>int</tt> value with a single one-bit, in the position
     *     of the lowest-order one-bit in the specified value, or zero if
     *     the specified value is itself equal to zero.
     * @since 1.5
     */
    public static int lowestOneBit(int i) {
        // HD, Section 2-1
        return i & -i;
    }
 
    /**
     * Returns the number of zero bits preceding the highest-order
     * ("leftmost") one-bit in the two's complement binary representation
     * of the specified <tt>int</tt> value.  Returns 32 if the
     * specified value has no one-bits in its two's complement representation,
     * in other words if it is equal to zero.
     *
     * <p>Note that this method is closely related to the logarithm base 2.
     * For all positive <tt>int</tt> values x:
     * <ul>
     * <li>floor(log<sub>2</sub>(x)) = <tt>31 - numberOfLeadingZeros(x)</tt>
     * <li>ceil(log<sub>2</sub>(x)) = <tt>32 - numberOfLeadingZeros(x - 1)</tt>
     * </ul>
     *
     * @return the number of zero bits preceding the highest-order
     *     ("leftmost") one-bit in the two's complement binary representation
     *     of the specified <tt>int</tt> value, or 32 if the value
     *     is equal to zero.
     * @since 1.5
     */
    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
    }
 
    /**
     * Returns the number of zero bits following the lowest-order ("rightmost")
     * one-bit in the two's complement binary representation of the specified
     * <tt>int</tt> value.  Returns 32 if the specified value has no
     * one-bits in its two's complement representation, in other words if it is
     * equal to zero.
     *
     * @return the number of zero bits following the lowest-order ("rightmost")
     *     one-bit in the two's complement binary representation of the
     *     specified <tt>int</tt> value, or 32 if the value is equal
     *     to zero.
     * @since 1.5
     */
    public static int numberOfTrailingZeros(int i) {
        // HD, Figure 5-14
    int y;
    if (i == 0) return 32;
    int n = 31;
    y = i <<16; if (y != 0) { n = n -16; i = y; }
    y = i << 8; if (y != 0) { n = n - 8; i = y; }
    y = i << 4; if (y != 0) { n = n - 4; i = y; }
    y = i << 2; if (y != 0) { n = n - 2; i = y; }
    return n - ((i << 1) >>> 31);
    }
 
    /**
     * Returns the number of one-bits in the two's complement binary
     * representation of the specified <tt>int</tt> value.  This function is
     * sometimes referred to as the <i>population count</i>.
     *
     * @return the number of one-bits in the two's complement binary
     *     representation of the specified <tt>int</tt> value.
     * @since 1.5
     */
    public static int bitCount(int i) {
        // HD, Figure 5-2
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
    }
 
    /**
     * Returns the value obtained by rotating the two's complement binary
     * representation of the specified <tt>int</tt> value left by the
     * specified number of bits.  (Bits shifted out of the left hand, or
     * high-order, side reenter on the right, or low-order.)
     *
     * <p>Note that left rotation with a negative distance is equivalent to
     * right rotation: <tt>rotateLeft(val, -distance) == rotateRight(val,
     * distance)</tt>.  Note also that rotation by any multiple of 32 is a
     * no-op, so all but the last five bits of the rotation distance can be
     * ignored, even if the distance is negative: <tt>rotateLeft(val,
     * distance) == rotateLeft(val, distance & 0x1F)</tt>.
     *
     * @return the value obtained by rotating the two's complement binary
     *     representation of the specified <tt>int</tt> value left by the
     *     specified number of bits.
     * @since 1.5
     */
    public static int rotateLeft(int i, int distance) {
        return (i << distance) | (i >>> -distance);
    }

    /**
     * Returns the value obtained by rotating the two's complement binary
     * representation of the specified <tt>int</tt> value right by the
     * specified number of bits.  (Bits shifted out of the right hand, or
     * low-order, side reenter on the left, or high-order.)
     *
     * <p>Note that right rotation with a negative distance is equivalent to
     * left rotation: <tt>rotateRight(val, -distance) == rotateLeft(val,
     * distance)</tt>.  Note also that rotation by any multiple of 32 is a
     * no-op, so all but the last five bits of the rotation distance can be
     * ignored, even if the distance is negative: <tt>rotateRight(val,
     * distance) == rotateRight(val, distance & 0x1F)</tt>.
     *
     * @return the value obtained by rotating the two's complement binary
     *     representation of the specified <tt>int</tt> value right by the
     *     specified number of bits.
     * @since 1.5
     */
    public static int rotateRight(int i, int distance) {
        return (i >>> distance) | (i << -distance);
    }
 
    /**
     * Returns the value obtained by reversing the order of the bits in the
     * two's complement binary representation of the specified <tt>int</tt>
     * value.
     *
     * @return the value obtained by reversing order of the bits in the
     *     specified <tt>int</tt> value.
     * @since 1.5
     */
    public static int reverse(int i) {
        // HD, Figure 7-1
    i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
    i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
    i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
    i = (i << 24) | ((i & 0xff00) << 8) |
        ((i >>> 8) & 0xff00) | (i >>> 24);
    return i;
    }
 
    /**
     * Returns the signum function of the specified <tt>int</tt> value.  (The
     * return value is -1 if the specified value is negative; 0 if the
     * specified value is zero; and 1 if the specified value is positive.)
     *
     * @return the signum function of the specified <tt>int</tt> value.
     * @since 1.5
     */
    public static int signum(int i) {
        // HD, Section 2-7
        return (i >> 31) | (-i >>> 31);
    }
 
    /**
     * Returns the value obtained by reversing the order of the bytes in the
     * two's complement representation of the specified <tt>int</tt> value.
     *
     * @return the value obtained by reversing the bytes in the specified
     *     <tt>int</tt> value.
     * @since 1.5
     */
    public static int reverseBytes(int i) {
        return ((i >>> 24)           ) |
               ((i >>   8) &   0xFF00) |
               ((i <<   8) & 0xFF0000) |
               ((i << 24));
    }

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 1360826667806852920L;
}
  