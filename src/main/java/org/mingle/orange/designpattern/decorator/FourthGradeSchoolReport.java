package org.mingle.orange.designpattern.decorator;

public class FourthGradeSchoolReport extends SchoolReport {

	@Override
	public void report() {
		//�ɼ����ĸ�ʽ��������ӵ�
		System.out.println("�𾴵�XXX�ҳ�:");
		System.out.println(" ......");
		System.out.println(" ���� 62 ��ѧ65 ���� 98 ��Ȼ 63");
		System.out.println(" .......");
		System.out.println(" �ҳ�ǩ���� ");
	}

	@Override
	public void sign(String name) {
		System.out.println("�ҳ�ǩ��Ϊ��" + name);
	}

}
