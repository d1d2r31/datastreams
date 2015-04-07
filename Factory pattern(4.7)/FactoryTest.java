interface Call {
    public void print();
}



class CallFactory {
    public static Call create(String callName){   //��ü�� �����ϱ� ���� Ŭ������ �̸��� �޾ƿ�
    											  //��ü�� �������ָ� �ȴ�.
        if (callName == null) {
            throw new IllegalArgumentException("null�� �ȵ�");
        }
        if (callName.equals("���丮1")) {
            return new Call1();
        }else if (callName.equals("���丮2")) {
            return new Call2();
        }else if (callName.equals("���丮3")) {
            return new Call3();
        }else{
            return null;
        }
    }
}



class Call1 implements Call {
    public void print() {
        System.out.println("���丮1 �ҷ���");
    }
}



class Call2 implements Call {
    public void print() {
        System.out.println("���丮2 �ҷ���");
    }
}



class Call3 implements Call {
    public void print() {
        System.out.println("���丮3 �ҷ���");
    }
}


public class FactoryTest {
    public static void main(String[] args) {
    	Call c1= CallFactory.create("���丮1");//static factory method
    										 //Call1~Call100 �������� new call1()~100 ���� ���°ͺ���
    	                                     //Call create()�޼ҵ忡���� �����ϸ�ȴ�.
        c1.print();  //��� : ���丮1 �ҷ���.
        Call c2= CallFactory.create("���丮2");
        c2.print();  //��� : ���丮2 �ҷ���.
        Call c3= CallFactory.create("���丮3");
        c3.print();  //��� : ���丮3 �ҷ���.
    }
}
