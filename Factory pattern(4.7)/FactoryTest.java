interface Call {
    public void print();
}



class CallFactory {
    public static Call create(String callName){   //객체를 생성하기 위한 클래스의 이름을 받아와
    											  //객체만 리턴해주면 된다.
        if (callName == null) {
            throw new IllegalArgumentException("null은 안됨");
        }
        if (callName.equals("팩토리1")) {
            return new Call1();
        }else if (callName.equals("팩토리2")) {
            return new Call2();
        }else if (callName.equals("팩토리3")) {
            return new Call3();
        }else{
            return null;
        }
    }
}



class Call1 implements Call {
    public void print() {
        System.out.println("팩토리1 불렀다");
    }
}



class Call2 implements Call {
    public void print() {
        System.out.println("팩토리2 불렀다");
    }
}



class Call3 implements Call {
    public void print() {
        System.out.println("팩토리3 불렀다");
    }
}


public class FactoryTest {
    public static void main(String[] args) {
    	Call c1= CallFactory.create("팩토리1");//static factory method
    										 //Call1~Call100 많아지면 new call1()~100 까지 쓰는것보다
    	                                     //Call create()메소드에서만 수정하면된다.
        c1.print();  //결과 : 팩토리1 불렀다.
        Call c2= CallFactory.create("팩토리2");
        c2.print();  //결과 : 팩토리2 불렀다.
        Call c3= CallFactory.create("팩토리3");
        c3.print();  //결과 : 팩토리3 불렀다.
    }
}
