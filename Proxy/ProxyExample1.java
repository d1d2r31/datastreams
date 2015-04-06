interface Printable {
    public abstract void setPrinterName(String name); // 이름의 설정
    public abstract String getPrinterName(); // 이름의 취득
    public abstract void print(String string); // 문자열 표시(프린트 아웃)
}

class PrinterProxy implements Printable {
    private String name; // 이름
    private Printer real; // 「본인」
    public PrinterProxy() {
    }
    public PrinterProxy(String name) { // 생성자
        this.name = name;
    }
    public synchronized void setPrinterName(String name) {  // 이름의 설정
        if (real != null) {
            real.setPrinterName(name);  // 「본인」에게도 설정한다
        }
        this.name = name;
    }
    public String getPrinterName() {  // 이름의 설정
        return name;
    }
    public void print(String string) { // 표시
        realize();
        real.print(string);
    }
    private synchronized void realize() { // 「본인」을 생성
        if (real == null) {            
            real = new Printer(name);
        }                           
    }
}

class Printer implements Printable {
    private String name;
    public Printer() {
        heavyJob("Printer의 인스턴스를 생성 중");
    }
    public Printer(String name) { // 생성자
        this.name = name;
        heavyJob("Printer의 인스턴스 (" + name + ")을 생성 중");
    }
    public void setPrinterName(String name) { // 이름의 설정
        this.name = name;
    }
    public String getPrinterName() { // 이름의 취득
        return name;
    }
    public void print(String string) { // 이름을 붙여 표시
        System.out.println("=== " + name + " ===");
        System.out.println(string);
    }
    private void heavyJob(String msg) {// 무거운 일(의 예정)
        System.out.print(msg);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.print(".");
        }
        System.out.println("완료");
    }
}

public class Main {
    public static void main(String[] args) {
        Printable p = new PrinterProxy("Alice");
        System.out.println("이름은 현재 " + p.getPrinterName() + "입니다.");
        p.setPrinterName("Bob");
        System.out.println("이름은 현재 " + p.getPrinterName() + "입니다.");
        p.print("Hello, world.");
    }
}

