package org.mingle.orange.arithmetic.base;


public class Mingle {
	
	public static boolean parentheses(String s) {
		Stack<Character> stack = new Stack<Character>();
		int length = s.length();
		int count = 0;
	
		for (int i = length - 1; i >= 0;) {
			if (s.charAt(i) == ')') {
				stack.push(')');

				i--;
			} else if (Character.isDigit(s.charAt(i))) {
				stack.push(s.charAt(i));

				i--;
			} else {
				stack.push(s.charAt(i));

				if (Character.isDigit(s.charAt(i - 1))) {
					stack.push(s.charAt(i - 1));
					stack.push('(');

					count++;
					i -= 2;
				} else {
					if (i > 3) {
						stack.push(s.charAt(i - 1));
						stack.push(s.charAt(i - 2));
						stack.push(s.charAt(i - 3));
						stack.push(s.charAt(i - 4));

						stack.push('(');
						stack.push('(');

						i -= 5;
						count += 2;
					}
				}
			}
		}

		for (int i = 0; i < s.length() + count; i++) {
			System.out.print(stack.pop());
		}

        return stack.isEmpty();
}
	
	public static void main(String[] args) {
//		System.out.println(Mingle.parentheses("1+2)*3-4)*5-6)))"));
		
		Stack<String> stack = new Stack<String>();
		Stack<String> tempStack = new Stack<String>();
		stack.push("jin");
		stack.push("ming");
		stack.push("lei");
		stack.push("wang");
		stack.push("jian");
		stack.push("zong");
		
//		tempStack = stack.copy(stack);
		
		for (int i = 0; i < tempStack.size(); i++) {
//			tempStack.pop();
		}
		
		
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = {3, 5, 7, 9, 11, 12, 13, 14, 15, 18, 19};
		int i = 0;
		int j = 0;
		int count = 0;
		
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				i++;
			} else if (a[i] > b[j]) {
				j++;
			} else {
				count++;
				i++;
				j++;
			}
		}
		
		System.out.println(count);
	}

}
