
// 1. 반복형태의 클로저 사용
log = ''
(1..10).each{ counter -> log += counter }
// 1-1 클로저 결과값 확인
assert log == '12345678910'
println(log)
  
log = ''
(1..10).each{ log += it }
assert log == '12345678910'
println(log)
 
log = ''
def closure = { log += it  }
(1..10).each closure
assert log == '12345678910'
println(log)

//결과 12345678910
================================================================

// 2.할당 형태의 클로저 사용
def c = { num -> return num + 1 }
// 2-1. 기본적인 클로저 호출 방법
assert 3 == c(2)
assert 3 == c.call(2)

================================================================

// 3. 메서드를 클로저 형태로 "reference.&" 연산자를 사용하면 가능하다.
// 3-1 일단 테스트를 위한 클래스와 메소드를 하나 생성후 호출
class MethodClosureSample {
   int sum(int num1, int num2){
      return num1 + num2
   }
}
MethodClosureSample sample = new MethodClosureSample();
Closure closure = sample.&sum
assert 3 == closure(2,1)

// 3-2 자체 메소드를 생성후 호출
def sumNum(num1, num2){
    return num1 + num2
}
def closure2 =  this.&sumNum
assert 3 == closure2(1,2)

================================================================

// 인자 수에 반응하는 기능을 가진 getParameterTypes 
def caller(Closure closure){
    closure.getParameterTypes().size()
}
assert caller{ one -> } == 1
assert caller{ one, two -> } == 2

================================================================

// 클로저 인자수를 고정시키는 커리(curry) 사용하기
def addr = { x, y, t -> return x+y+t }
def addOne = addr.curry(1).curry(2)
assert addOne(5) == 8

//클로저 인자수를 고정시키는 커리(curry) 사용하기 
//커리(curry)란 인자를 여러 개 받는 함수에서 인자 몇 개를 고정시킨 또 다른 함수를 만들어 낸다는 것이다. 
//클로저의 curry 메서드를 이용해 한 개 혹은 그 이상의 인자가 특정 값으로 거정된 복사본 클로저를 만들 수 있다.인자를 고정시킬때는 왼쪽부터 고정된다. 

================================================================
직원 클래스(이름, 급여, 직책-매니져 여부) 를 가지고 있고 4명의 직원이 있다.
----------------------------------------------------------------
class Employee {
    def name, salary
    boolean manager
    String toString() { return name }
}
 
def emps = [new Employee(name:'Guillaume', manager:true, salary:200),
    new Employee(name:'Graeme', manager:true, salary:200),
    new Employee(name:'Dierk', manager:false, salary:151),
    new Employee(name:'Bernd', manager:false, salary:50)]

//1.매니져 여부

def managers(emps) {
    emps.findAll { e -> e.isManager() }
}
println( managers(emps) ) // [Guillaume, Graeme]
 
list = []
emps.each { if( it.manager ) list += it  }
println ( list ) // [Guillaume, Graeme]

//2. 급여가 150 이상인 직원 확인

def highPaid(emps) {
	threshold = 150
	emps.findAll { e -> e.salary > threshold }
}
assert emps[0..2] == highPaid(emps) // [Guillaume, Graeme, Dierk]
println (highPaid(emps))
 
def methodHighPaid(emps){
	return emps.salary > 150 ? true : false
}
def closulre = this.&methodHighPaid
assert emps[0..2] ==  emps.findAll(closulre)
println (emps.findAll(closulre))

//3.급여를 직접 입력해서 그 금액보다 큰직원을 확인

def paidMore(amount) {
	{ e -> e.salary > amount}
}
def highPaid = paidMore(150)
 
assert highPaid instanceof Closure
assert highPaid(emps[0]) // true
assert emps[0..2] == emps.findAll(highPaid)

================================================================

================================================================