import java.util.ArrayList;

interface Observer {
    public void update(float temperature, float humidity, float pressure);
}

interface  Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

interface DisplayElement {
    public void display();
}

class WeatherData implements Subject {
	 private ArrayList observers;    //ArrayList�� import
	 private float temperature;
	 private float humidity;
	 private float pressure;
	 
	 public WeatherData(){
	   observers = new ArrayList();    //�����ڿ��� ���������� ���� ArrayList ��ü ����
	 }
	 
	 public void registerObserver(Observer o){   // Observer o = new (Subject(new CurrentConditionsDisplay(new WeatherData()));
	   observers.add(o);						 //�������� ����� �ϸ� ��� �ǵڿ� �߰����ش�.          
	 }
	 
	 public void removeObserver(Observer o){   //�������� Ż�� ��û�ϸ� ��Ͽ��� ����.
	   int idx = observers.indexOf(o);
	   if(idx >=0 ){
	     observers.remove(idx);
	   }
	 }
	 
	 public void notifyObservers(){  // ���������� ���� �����ٰ� �˸��� �޼ҵ� 
	   for(int i = 0 ; i < observers.size(); i++){ // ArrayList�� ����ص� ���������� �ҷ���
	     Observer observer = (Observer)observers.get(i);  // �װ� �ϳ��� �о ������Ÿ������ �ְ� 
	     observer.update(temperature, humidity, pressure);  //�������� ������Ʈ��
	   }
	 }
	 
	 public void measurementsChanged(){  //���� �����ٰ� �˸��� �޼ҵ�
	   notifyObservers();    // ���������� �˸��� �޼ҵ� 
	 }
	 public void setMeasurements(float temperature , float humidity, float pressure){  //main���� �������� ���� �������ذ����� �Ѿ�´�.
	  
	   this.temperature = temperature ; 
	   this.humidity = humidity;
	   this.pressure = pressure;
	   measurementsChanged();      //���� �����ٰ� �˸��� �޼ҵ�
	 }
	//��Ÿ�޼ҵ� or �߰� �޼ҵ�
	}

class CurrentConditionsDisplay implements Observer, DisplayElement {
	  /*
	   * WeatherData��ü�κ��� ��������� �ޱ����� Observer��ü�� ����.
	   * ������ ��� ���÷��� �׸񿡼� DisplayElement�� �����ϱ�� �߱� ������ 
	   * DisplayElement�������̽��� �����մϴ�.
	   */
	  private float temperature;
	  private float humidity;
	  private float pressure;
	  private Subject weatherData;
	  
	  public CurrentConditionsDisplay(Subject weatherData){
	    /*
	     * �����ڿ� weatherData��� ������ü�� ���޵Ǹ� , �� ��ü�� �Ἥ ���÷��̸� �������� ���
	     */
	    this.weatherData = weatherData;       // Subject wetherData = new CurrentConditionsDisplay(new WeatherData());
	    weatherData.registerObserver(this);   
	  }
	  
	  public void update(float temperature, float humidity , float pressure){
	    /*update()�� ȣ��Ǹ� ��°� ���� �з��� �����ϰ� display()�� ȣ�� �Ѵ� .*/
	    this.temperature = temperature;
	    this.humidity = humidity;
	    this.pressure = pressure;
	    display();
	  }
	  
	  public void display(){
	    System.out.println("���� ���:" + temperature + " | ���� ����:" + humidity + " | ���� ���:" + pressure);
	  }
	}

public class ObserverTest {
	
    public static void main(String[] args) {
 
    	WeatherData weatherData = new WeatherData(); 
        //�켱 ���� ������ ��ü�� ����  (���޹��� ���� �������鿡�� �����ϴ� ����)
        
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        //���簪�� �����ִ� Ŭ������ CurrentConditionsDisplay ��ü�� ���� ���ڷ� �̸� ������ ���������� ��ü�� ����(�������ε���ϱ�����)
        
        weatherData.setMeasurements(80, 65, 30.5f);
        weatherData.setMeasurements(10, 20, 88.7f);
        weatherData.setMeasurements(65, 23, 10.5f);
        //���ο� ��� ���� ����ó�� ����� 

    }
}