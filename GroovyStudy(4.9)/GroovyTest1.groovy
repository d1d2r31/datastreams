def x = 1..10         // 1~10����
println x.contains(5) // 5�� ���Ե̳�?
println x.contains(15) == false //15�� ���Ե��� �ʾҴ�.
println x.size() == 10  //����� 10�̳�
println x.from == 1     //1����
println x.to == 10      //10����
println x.reverse() == 10..1 //������ 10~1����
println (x)

=================================================================

if ( false ) assert false //����¥�� if
 
// if else ��
def k = 0
if ( k == 0 ) { assert true }
else { assert false }
 
// �������� while
def i = 0
while ( i < 10) { i++ }
assert i == 10
 
// ������ for ����
def clinks = 0
for ( remainingGuests in 0..9 ) { clinks += remainingGuests }  //0 ~ 9 ���� for�����鼭 clinks �� ���ض� 0~9���� ���ϱ�
assert clinks == (10*9)/2   //0~9���� ���ϴ� ����
println(clinks)
 
// ����Ʈ�� for
def list = [0,1,2,3,4,5,6,7,8,9]
for ( j in list ) { println j ; println list[j] }  //J == 0 ���� list�����ŭ �����ϸ鼭 ���ƶ� 
 
// Ŭ������ each �޼���

list.each() { item -> assert item == list[item] } 

 
// �������� switch
switch (3) { 
    case 1 : assert false; break
    case 3 : assert true; break
    default : assert false
}
=============================================================
class Book {
   String title
}

Book.metaClass.titleInUpperCase << {-> title.toUpperCase() }
// �ҹ��ڸ� �빮�ڷ� �ٲ��ִ� �޼ҵ带 (Ŭ����) ��ŸŬ���� ���� titleInupperCase �޼ҵ�� ����

def b = new Book(title:"The Stand")

assert "THE STAND" == b.titleInUpperCase()  //��ŸŬ������ �����س��� �޼ҵ� ȣ��

=============================================================
class Book {
   String title
}

Book.metaClass.author = "Stephen King"
//��ŸŬ���� ���� author ������ ������ 
def b = new Book()

assert "Stephen King" == b.author //�̷��� �ҷ��͵� ����

*************************************************************

class Book {
  String title
}
Book.metaClass.getAuthor << {-> "Stephen King" } 
//��ŸŬ������ getAuthor�޼ҵ� �� Ŭ������ �־�θ� readOnly�� �� �� �ִ� author������ �����Ǵ°Ű���.

def b = new Book()

assert "Stephen King" == b.author
=============================================================

// String author �� �����൵ ��� ������ �Ȱ�ó�� ���δ� . . �ܿ�� 

class Book {
  String title
}

def properties = Collections.synchronizedMap([:])

Book.metaClass.setAuthor = { String value -> 
   properties[System.identityHashCode(delegate) + "author"] = value
}        
// author ������ ���� ������ �ߺ��� ���ϱ� ���� �ؽ��ڵ� ��� 

Book.metaClass.getAuthor = {->
   properties[System.identityHashCode(delegate) + "author"]
}

def b = new Book()

b.author = "kikiki"

println ( b.getAuthor() )