interface Printable {
    public abstract void setPrinterName(String name); // �̸��� ����
    public abstract String getPrinterName(); // �̸��� ���
    public abstract void print(String string); // ���ڿ� ǥ��(����Ʈ �ƿ�)
}

class PrinterProxy implements Printable {
    private String name; // �̸�
    private Printer real; // �����Ρ�
    public PrinterProxy() {
    }
    public PrinterProxy(String name) { // ������
        this.name = name;
    }
    public synchronized void setPrinterName(String name) {  // �̸��� ����
        if (real != null) {
            real.setPrinterName(name);  // �����Ρ����Ե� �����Ѵ�
        }
        this.name = name;
    }
    public String getPrinterName() {  // �̸��� ����
        return name;
    }
    public void print(String string) { // ǥ��
        realize();
        real.print(string);
    }
    private synchronized void realize() { // �����Ρ��� ����
        if (real == null) {            
            real = new Printer(name);
        }                           
    }
}

class Printer implements Printable {
    private String name;
    public Printer() {
        heavyJob("Printer�� �ν��Ͻ��� ���� ��");
    }
    public Printer(String name) { // ������
        this.name = name;
        heavyJob("Printer�� �ν��Ͻ� (" + name + ")�� ���� ��");
    }
    public void setPrinterName(String name) { // �̸��� ����
        this.name = name;
    }
    public String getPrinterName() { // �̸��� ���
        return name;
    }
    public void print(String string) { // �̸��� �ٿ� ǥ��
        System.out.println("=== " + name + " ===");
        System.out.println(string);
    }
    private void heavyJob(String msg) {// ���ſ� ��(�� ����)
        System.out.print(msg);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.print(".");
        }
        System.out.println("�Ϸ�");
    }
}

public class Main {
    public static void main(String[] args) {
        Printable p = new PrinterProxy("Alice");
        System.out.println("�̸��� ���� " + p.getPrinterName() + "�Դϴ�.");
        p.setPrinterName("Bob");
        System.out.println("�̸��� ���� " + p.getPrinterName() + "�Դϴ�.");
        p.print("Hello, world.");
    }
}

