
// 1. �ݺ������� Ŭ���� ���
log = ''
(1..10).each{ counter -> log += counter }
// 1-1 Ŭ���� ����� Ȯ��
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

//��� 12345678910
================================================================

// 2.�Ҵ� ������ Ŭ���� ���
def c = { num -> return num + 1 }
// 2-1. �⺻���� Ŭ���� ȣ�� ���
assert 3 == c(2)
assert 3 == c.call(2)

================================================================

// 3. �޼��带 Ŭ���� ���·� "reference.&" �����ڸ� ����ϸ� �����ϴ�.
// 3-1 �ϴ� �׽�Ʈ�� ���� Ŭ������ �޼ҵ带 �ϳ� ������ ȣ��
class MethodClosureSample {
   int sum(int num1, int num2){
      return num1 + num2
   }
}
MethodClosureSample sample = new MethodClosureSample();
Closure closure = sample.&sum
assert 3 == closure(2,1)

// 3-2 ��ü �޼ҵ带 ������ ȣ��
def sumNum(num1, num2){
    return num1 + num2
}
def closure2 =  this.&sumNum
assert 3 == closure2(1,2)

================================================================

// ���� ���� �����ϴ� ����� ���� getParameterTypes 
def caller(Closure closure){
    closure.getParameterTypes().size()
}
assert caller{ one -> } == 1
assert caller{ one, two -> } == 2

================================================================

// Ŭ���� ���ڼ��� ������Ű�� Ŀ��(curry) ����ϱ�
def addr = { x, y, t -> return x+y+t }
def addOne = addr.curry(1).curry(2)
assert addOne(5) == 8

//Ŭ���� ���ڼ��� ������Ű�� Ŀ��(curry) ����ϱ� 
//Ŀ��(curry)�� ���ڸ� ���� �� �޴� �Լ����� ���� �� ���� ������Ų �� �ٸ� �Լ��� ����� ���ٴ� ���̴�. 
//Ŭ������ curry �޼��带 �̿��� �� �� Ȥ�� �� �̻��� ���ڰ� Ư�� ������ ������ ���纻 Ŭ������ ���� �� �ִ�.���ڸ� ������ų���� ���ʺ��� �����ȴ�. 

================================================================
���� Ŭ����(�̸�, �޿�, ��å-�Ŵ��� ����) �� ������ �ְ� 4���� ������ �ִ�.
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

//1.�Ŵ��� ����

def managers(emps) {
    emps.findAll { e -> e.isManager() }
}
println( managers(emps) ) // [Guillaume, Graeme]
 
list = []
emps.each { if( it.manager ) list += it  }
println ( list ) // [Guillaume, Graeme]

//2. �޿��� 150 �̻��� ���� Ȯ��

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

//3.�޿��� ���� �Է��ؼ� �� �ݾ׺��� ū������ Ȯ��

def paidMore(amount) {
	{ e -> e.salary > amount}
}
def highPaid = paidMore(150)
 
assert highPaid instanceof Closure
assert highPaid(emps[0]) // true
assert emps[0..2] == emps.findAll(highPaid)

================================================================

================================================================