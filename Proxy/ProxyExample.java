interface Image {                      //이미지 인터페이스 생성
    public void displayImage();
}
 
//on System A 
class RealImage implements Image {
 
    private String filename = null;
    // 생성자
    public RealImage(final String FILENAME) { 
        filename = FILENAME;
        loadImageFromDisk();
    }
 
    // 디스크로부터 이미지 로드
    private void loadImageFromDisk() {
        System.out.println("Loading   " + filename);
    }
 
    // 이미지 표시 (오버라이드)
    public void displayImage() { 
        System.out.println("Displaying " + filename); 
    }
}
 
// B 시스템에서
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
 
    // 이미지 출력 (오버라이드)
    public void displayImage() {
        if (image == null) {   //이미지객체에 값이 없으면 
           image = new RealImage(filename);  //A시스템(본인)에게 저장
        } 
        image.displayImage();
    }
 
}
 
class ProxyExample {
 
   // 테스트 메소드
   public static void main(String[] args) {
        final Image IMAGE1 = new ProxyImage("HiRes_10MB_Photo1");
        final Image IMAGE2 = new ProxyImage("HiRes_10MB_Photo2");     
 
        IMAGE1.displayImage(); // loading necessary  
        IMAGE1.displayImage(); // loading unnecessary  // 두번째로 넘기면 값이 있기때문에
        IMAGE2.displayImage(); // loading necessary
        IMAGE2.displayImage(); // loading unnecessary
        IMAGE1.displayImage(); // loading unnecessary
    }
}