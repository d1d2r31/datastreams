class SingleTon {
	private static SingleTon instance = null; //private 멤버변수로 자신클래스에서 인스턴스를 가진다
	private int SingleTonCount = 0;			  
	private SingleTon(){                     //private 생성자를 지정하여 외부에서 생성하지 못하게 막는다
		System.out.println("인스턴스 생성");
		
	}
	public static SingleTon getInstance(){ //getInstace 메소드를 통해서만 static 을 이용해 객체를 사용할수 있게한다.
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
         System.out.println("s1에서 호출" + s1.getCount());
         s1.getInstance();
         System.out.println("s1에서 호출" + s1.getCount());
         SingleTon s2 = SingleTon.getInstance();
         System.out.println("s2에서 호출" + s1.getCount());
         s2.getInstance();
         System.out.println("s2에서 호출" + s1.getCount());
	}

}

// 결과 
/*인스턴스 생성    < 생성자의 실행이 한번만 실행되었고 
s1에서 호출1      
s1에서 호출2
s2에서 호출3      < 인스턴스 생성을 해도 같은 인스턴스를 사용 
s2에서 호출4*/