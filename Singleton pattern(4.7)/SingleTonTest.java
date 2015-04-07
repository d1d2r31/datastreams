class SingleTon {
	private static SingleTon instance = null; //private ��������� �ڽ�Ŭ�������� �ν��Ͻ��� ������
	private int SingleTonCount = 0;			  
	private SingleTon(){                     //private �����ڸ� �����Ͽ� �ܺο��� �������� ���ϰ� ���´�
		System.out.println("�ν��Ͻ� ����");
		
	}
	public static SingleTon getInstance(){ //getInstace �޼ҵ带 ���ؼ��� static �� �̿��� ��ü�� ����Ҽ� �ְ��Ѵ�.
		if( instance == null){
			instance = new SingleTon();
		}
		return instance;
	}
	public int getCount(){
		return ++SingleTonCount;
		
	}
}


public class SingleTonTest {

	public static void main(String[] args) {
         SingleTon s1 = SingleTon.getInstance();
         System.out.println("s1���� ȣ��" + s1.getCount());
         s1.getInstance();
         System.out.println("s1���� ȣ��" + s1.getCount());
         SingleTon s2 = SingleTon.getInstance();
         System.out.println("s2���� ȣ��" + s1.getCount());
         s2.getInstance();
         System.out.println("s2���� ȣ��" + s1.getCount());
	}

}

// ��� 
/*�ν��Ͻ� ����    < �������� ������ �ѹ��� ����Ǿ��� 
s1���� ȣ��1      
s1���� ȣ��2
s2���� ȣ��3      < �ν��Ͻ� ������ �ص� ���� �ν��Ͻ��� ��� 
s2���� ȣ��4*/