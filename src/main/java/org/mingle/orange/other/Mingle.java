package org.mingle.orange.other;

public class Mingle {
	
	/**
	 * �������0��100�����������е�������������������ܱ�3����������������ּ���*����ǣ�
	 * ����������ܱ�5����������������ּ���#����ǣ�
	 * ��������ּ��ܱ�3�������ܱ�5����������������ּ���*#����ǡ�
	 * @param n
	 */
	public static void printNumber(int n) {
		int add[] = {3,2,1,3,1,2,3};
	    char c[] = {'*','#','*','*','#','*'};
	    String all = "*#";
	    int i = 0;
	    int index = 0;
	    while((i += add[index]) <= 100)
	    {
	        if(index != 6)
	        {
	        	System.out.println(i + "" + c[index]);;
	            index++;
	        }
	        else
	        {
	        	System.out.println(i + all);
	            index = 0;
	        }
	    }
/*		
		for (int i = 0; i <= n; i++) {
			if (0 == (i % 3)) {
				
				if (0 == (i % 5)) {
					System.out.println(i + "*#");
				}
				
				System.out.println(i + "*");
			} else if (0 == (i % 5)) {
				System.out.println(i + "#");
			}
		}
*/	
	}
	

	public static String testString(String str) {
		return str += "world!";
	}
	
	// http://www.snowpeak.org/testjs.htm?a1=v1&a2=v2&a3=v3#anchor �нس� a1=v1&a2=&a3=v3��
	public static void parseURL(String url) {
		url = url.substring(url.indexOf('?') + 1);
		String[] array = url.split("&");
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	public static void main(String[] args) {
//		Mingle.parseURL("http://www.snowpeak.org/testjs.htm?a1=v1&a2=v2&a3=v3#anchor");
		
/*		File file = new File("D://test.txt");
		
		try {
			PrintWriter p = new PrintWriter(file);
			p.println("hello,world");
			p.flush();
			p.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
*/
		Mingle.printNumber(100);
	}

}
