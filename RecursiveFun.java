// https://codingbat.com/java/Recursion-1
public class RecursiveFun{
public int close10(int a, int b) {
		int result;
		if (Math.abs(a - 10) < Math.abs(b - 10)) {
			result = a;
		} else {
			result = b;
		}
		return result % 2 == 0 ? result : 0;
	}

	public boolean stringE(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'e') {
				count++;
			}
		}
		if (count == 1 || count == 3) {
			return true;
		}
		return false;
	}

	public boolean lastDigit(int a, int b) {
		return a < b ? String.valueOf(a).equals(String.valueOf(b).substring(String.valueOf(b).length() - 1))
				: String.valueOf(b).equals(String.valueOf(a).substring(String.valueOf(a).length() - 1));
	}

	public String endUp(String str) {
		if (str.length() < 3) {
			str.toUpperCase();
		} else {

		}
		return str.length() < 3 ? str.toUpperCase()
				: str.substring(0, str.length() - 3) + str.substring(str.length() - 3).toUpperCase();
	}

	public String everyNth(String str, int n) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (i % n == 0) {
				b.append(String.valueOf(str.charAt(i)));
			}
		}
		return b.toString();
	}

	public String front3(String str) {
		return str.substring(0, 3) + str.substring(0, 3) + str.substring(0, 3);
	}

	int countXX(String str) {
		int c = 0;
		for (int i = 0; i < str.length() - 1; i++) {
			if (str.charAt(i) == 'x' && str.charAt(i + 1) == 'x') {
				c++;
			}
		}
		return c;
	}

	public int factorial(int n) {
		if (n == 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}

	public int fibonacci(int n) {

		if (n == 0) {
			return 1;
		}
		return 2 * fibonacci(n - 1) - 1;
	}

	public int bunnyEars2(int bunnies) {
		if (bunnies == 0) {
			return 0;
		}
		if (bunnies % 2 != 0) {
			return bunnyEars2(bunnies - 1) + 2;
		}
		if (bunnies % 2 == 0) {
			return bunnyEars2(bunnies - 1) + 3;
		}
		return 0;
	}

	public int sumDigits(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return n % 10 + sumDigits(n / 10);
	}

	public int count7(int n) {
		if (n % 10 == 7) {
			return count7(n / 10) + 1;
		}
		if (n == 0) {
			return 0;
		}
		return count7(n / 10);
	}

	public int count8(int n) {
		if (n == 0) {
			return 0;
		} else if (n % 100 == 88) {
			return count8(n / 10) + 2;
		} else if (n % 10 == 8) {
			return count8(n / 10) + 1;
		}
		return count8(n / 10);
	}

	public int powerN(int base, int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return base;
		}
		return powerN(base, n - 1) * base;
	}

	public int countX(String str) {
		if ("".equals(str)) {
			return 0;
		} else if ("x".equals(str)) {
			return 1;
		} else if (str.charAt(0) == 'x') {
			return countX(str.substring(1)) + 1;
		}
		return countX(str.substring(1));
	}

	public int countHi(String str) {
		if ("".equals(str)) {
			return 0;
		} else if ("hi".equals(str)) {
			return 1;
		} else if (str.length() > 2 && "hi".equals(str.substring(0, 2))) {
			return countHi(str.substring(2)) + 1;
		}
		return countHi(str.substring(1));
	}

	public String changeXY(String str) {
		if ("".equals(str)) {
			return "";
		} else if ("x".equals(str)) {
			return "y";
		} else if (str.charAt(0) == 'x') {
			return "y" + changeXY(str.substring(1));
		}
		return str.charAt(0) + changeXY(str.substring(1));
	}

	public String changePi(String str) {
		if ("".equals(str)) {
			return "";
		} else if ("pi".equals(str)) {
			return "3.14";
		} else if (str.length() > 2 && "pi".equals(str.substring(0, 2))) {
			return "3.14" + changePi(str.substring(2));
		}
		return str.charAt(0) + changePi(str.substring(1));
	}

	public String noX(String str) {
		if ("".equals(str)) {
			return "";
		} else if ("x".equals(str)) {
			return "";
		} else if ('x' == str.charAt(0)) {
			return "" + noX(str.substring(1));
		}
		return str.charAt(0) + noX(str.substring(1));
	}

	public boolean array6(int[] nums, int index) {
		if (nums.length == index || nums.length == 0) {
			return false;
		} else if (nums[index] == 6) {
			return true;
		}

		return array6(nums, ++index);
	}

	public int array11(int[] nums, int index) {
		if (nums.length == index || nums.length == 0) {
			return 0;
		} else if (nums[index] == 11) {
			return array11(nums, ++index) + 1;
		}

		return array11(nums, ++index);
	}

	public boolean array220(int[] nums, int index) {

		if (nums.length == index || nums.length == 0) {
			return false;
		} else if (nums.length - 1 > index && nums.length > 1 && nums[index + 1] % 10 == 0
				&& nums[index + 1] / 10 == nums[index]) {
			return true;
		}
		return array220(nums, ++index);
	}

	public String allStar(String str) {
		if ("".equals(str)) {
			return "";
		}
		if (str.length() == 1) {
			return str;
		}
		return str.charAt(0) + "*" + allStar(str.substring(1));
	}

	public String pairStar(String str) {
		if ("".equals(str)) {
			return "";
		} else if (str.length() == 1) {
			return str;
		} else if (str.length() > 1 && str.charAt(0) == str.charAt(1)) {
			return str.charAt(0) + "*" + pairStar(str.substring(1));
		}

		return str.charAt(0) + pairStar(str.substring(1));
	}

	public String endX(String str) {
		if ("".equals(str)) {
			return "";
		} else if (str.length() == 1) {
			return str;
		} else if (str.length() > 1 && str.charAt(0) == 'x') {
			return endX(str.substring(1)) + "x";
		} else if (str.length() > 1 && str.charAt(0) != 'x') {
			return str.charAt(0) + endX(str.substring(1));
		}
		return endX(str.substring(1));
	}

	public int countPairs(String str) {
		if (str.length() < 3) {
			return 0;
		} else if (str.length() == 3 && str.charAt(0) == str.charAt(2)) {
			return 1;
		} else if (str.length() > 3 && str.charAt(0) == str.charAt(2)) {
			return countPairs(str.substring(1)) + 1;
		}
		return countPairs(str.substring(1));
	}

	public int countAbc(String str) {
		if (str.length() < 3) {
			return 0;
		} else if (str.length() == 3 && ("abc".equals(str.substring(0, 3)) || "aba".equals(str.substring(0, 3)))) {
			return 1;
		} else if (str.length() > 3 && ("abc".equals(str.substring(0, 3)) || "aba".equals(str.substring(0, 3)))) {
			return countAbc(str.substring(1)) + 1;
		}
		return countAbc(str.substring(1));
	}

	public int count11(String str) {

		if (str.length() < 2) {
			return 0;
		} else if (str.length() == 2 && "11".equals(str.substring(0, 2))) {
			return 1;
		} else if (str.length() > 2 && "11".equals(str.substring(0, 2))) {
			return count11(str.substring(2)) + 1;
		}
		return count11(str.substring(1));
	}

	public String stringClean(String str) {
		if ("".equals(str)) {
			return "";
		} else if (str.length() == 1) {
			return str;
		} else if (str.length() > 1 && str.charAt(0) != str.charAt(1)) {
			return str.charAt(0) + stringClean(str.substring(1));
		}
		return stringClean(str.substring(1));
	}

	public int countHi2(String str) {
		if ("".equals(str) || str.length() < 2 || "xhi".equals(str)) {
			return 0;
		} else if ("hi".equals(str)) {
			return 1;
		} else if (str.length() > 2 && "xhi".equals(str.substring(0, 3))) {
			return countHi2(str.substring(2));
		} else if (str.length() > 1 && "hi".equals(str.substring(0, 2))) {
			return countHi2(str.substring(2)) + 1;
		}
		return countHi2(str.substring(1));
	}

	public String parenBit(String str) {
		if ("()".equals(str)) {
			return "()";
		} else if (str.length() > 1 && "(".equals(str.charAt(0))) {
			return "(" + parenBit(str.substring(1));
		} else if (str.length() > 1 && ")".equals(str.charAt(0))) {
			return ")";
		}
		return str.charAt(0) + parenBit(str.substring(1));
	}

	public static void main(String... aa) {

		System.out.println(new CheckTypeEnum().count11("11abc11"));
		System.out.println("Test".substring(0, 3));

	}
}
