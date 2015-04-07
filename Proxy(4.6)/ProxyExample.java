interface Image {                      //�̹��� �������̽� ����
    public void displayImage();
}
 
//on System A 
class RealImage implements Image {
 
    private String filename = null;
    // ������
    public RealImage(final String FILENAME) { 
        filename = FILENAME;
        loadImageFromDisk();
    }
 
    // ��ũ�κ��� �̹��� �ε�
    private void loadImageFromDisk() {
        System.out.println("Loading   " + filename);
    }
 
    // �̹��� ǥ�� (�������̵�)
    public void displayImage() { 
        System.out.println("Displaying " + filename); 
    }
}
 
// B �ý��ۿ���
class ProxyImage implements Image {
 
    private RealImage image = null;
    private String filename = null;
    /**
     * Constructor
     * @param FILENAME
     */
    public ProxyImage(final String FILENAME) { 
        filename = FILENAME; 
    }
 
    // �̹��� ��� (�������̵�)
    public void displayImage() {
        if (image == null) {   //�̹�����ü�� ���� ������ 
           image = new RealImage(filename);  //A�ý���(����)���� ����
        } 
        image.displayImage();
    }
 
}
 
class ProxyExample {
 
   // �׽�Ʈ �޼ҵ�
   public static void main(String[] args) {
        final Image IMAGE1 = new ProxyImage("HiRes_10MB_Photo1");
        final Image IMAGE2 = new ProxyImage("HiRes_10MB_Photo2");     
 
        IMAGE1.displayImage(); // loading necessary  
        IMAGE1.displayImage(); // loading unnecessary  // �ι�°�� �ѱ�� ���� �ֱ⶧����
        IMAGE2.displayImage(); // loading necessary
        IMAGE2.displayImage(); // loading unnecessary
        IMAGE1.displayImage(); // loading unnecessary
    }
}