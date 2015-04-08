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
	 private ArrayList observers;    //ArrayList를 import
	 private float temperature;
	 private float humidity;
	 private float pressure;
	 
	 public WeatherData(){
	   observers = new ArrayList();    //생성자에서 옵저버들을 담을 ArrayList 객체 생성
	 }
	 
	 public void registerObserver(Observer o){   // Observer o = new (Subject(new CurrentConditionsDisplay(new WeatherData()));
	   observers.add(o);						 //옵저버가 등록을 하면 목록 맨뒤에 추가해준다.          
	 }
	 
	 public void removeObserver(Observer o){   //옵저버가 탈퇴를 요청하면 목록에서 제거.
	   int idx = observers.indexOf(o);
	   if(idx >=0 ){
	     observers.remove(idx);
	   }
	 }
	 
	 public void notifyObservers(){  // 옵저버에게 값이 변경됬다고 알리는 메소드 
	   for(int i = 0 ; i < observers.size(); i++){ // ArrayList에 등록해둔 옵저버들을 불러와
	     Observer observer = (Observer)observers.get(i);  // 그걸 하나씩 읽어서 옵져버타입으로 넣고 
	     observer.update(temperature, humidity, pressure);  //옵저버에 업데이트해
	   }
	 }
	 
	 public void measurementsChanged(){  //값이 변경됬다고 알리는 메소드
	   notifyObservers();    // 옵저버에게 알리는 메소드 
	 }
	 public void setMeasurements(float temperature , float humidity, float pressure){  //main에서 가상으로 값을 지정해준값들이 넘어온다.
	  
	   this.temperature = temperature ; 
	   this.humidity = humidity;
	   this.pressure = pressure;
	   measurementsChanged();      //값이 변경됬다고 알리는 메소드
	 }
	//기타메소드 or 추가 메소드
	}

class CurrentConditionsDisplay implements Observer, DisplayElement {
	  /*
	   * WeatherData객체로부터 변경사항을 받기위해 Observer객체를 구현.
	   * 구조상 모든 디스플레이 항목에서 DisplayElement를 구현하기로 했기 때문에 
	   * DisplayElement인터페이스도 구현합니다.
	   */
	  private float temperature;
	  private float humidity;
	  private float pressure;
	  private Subject weatherData;
	  
	  public CurrentConditionsDisplay(Subject weatherData){
	    /*
	     * 생성자에 weatherData라는 주제객체가 전달되며 , 그 객체를 써서 디스플레이를 옵저버로 등록
	     */
	    this.weatherData = weatherData;       // Subject wetherData = new CurrentConditionsDisplay(new WeatherData());
	    weatherData.registerObserver(this);   
	  }
	  
	  public void update(float temperature, float humidity , float pressure){
	    /*update()가 호출되면 기온과 습도 압력을 저장하고 display()를 호출 한다 .*/
	    this.temperature = temperature;
	    this.humidity = humidity;
	    this.pressure = pressure;
	    display();
	  }
	  
	  public void display(){
	    System.out.println("현재 기온:" + temperature + " | 현재 습도:" + humidity + " | 현재 기압:" + pressure);
	  }
	}

public class ObserverTest {
	
    public static void main(String[] args) {
 
    	WeatherData weatherData = new WeatherData(); 
        //우선 웨더 데이터 객체를 생성  (전달받은 값을 옵저버들에게 전달하는 역할)
        
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        //현재값을 보여주는 클래스인 CurrentConditionsDisplay 객체를 생성 인자로 미리 생성한 웨더데이터 객체를 전달(옵저버로등록하기위해)
        
        weatherData.setMeasurements(80, 65, 30.5f);
        weatherData.setMeasurements(10, 20, 88.7f);
        weatherData.setMeasurements(65, 23, 10.5f);
        //세로운 기상 값이 들어간것처럼 만든다 

    }
}