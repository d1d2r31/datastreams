def x = 1..10         // 1~10까지
println x.contains(5) // 5가 포함됫냐?
println x.contains(15) == false //15가 포함되지 않았다.
println x.size() == 10  //사이즈가 10이냐
println x.from == 1     //1부터
println x.to == 10      //10까지
println x.reverse() == 10..1 //역으로 10~1까지
println (x)

=================================================================

if ( false ) assert false //한줄짜리 if
 
// if else 문
def k = 0
if ( k == 0 ) { assert true }
else { assert false }
 
// 고전적인 while
def i = 0
while ( i < 10) { i++ }
assert i == 10
 
// 범위의 for 루프
def clinks = 0
for ( remainingGuests in 0..9 ) { clinks += remainingGuests }  //0 ~ 9 까지 for문돌면서 clinks 에 더해라 0~9까지 더하기
assert clinks == (10*9)/2   //0~9까지 더하는 공식
println(clinks)
 
// 리스트의 for
def list = [0,1,2,3,4,5,6,7,8,9]
for ( j in list ) { println j ; println list[j] }  //J == 0 부터 list사이즈만큼 증가하면서 돌아라 
 
// 클로저와 each 메서드

list.each() { item -> assert item == list[item] } 

 
// 고전적인 switch
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
// 소문자를 대문자로 바꿔주는 메소드를 (클로저) 메타클래스 내의 titleInupperCase 메소드로 정의

def b = new Book(title:"The Stand")

assert "THE STAND" == b.titleInUpperCase()  //메타클래스에 정의해놓은 메소드 호출

=============================================================
class Book {
   String title
}

Book.metaClass.author = "Stephen King"
//메타클래스 내에 author 변수에 값저장 
def b = new Book()

assert "Stephen King" == b.author //이렇게 불러와도 같다

*************************************************************

class Book {
  String title
}
Book.metaClass.getAuthor << {-> "Stephen King" } 
//메타클래스에 getAuthor메소드 에 클로저를 넣어두면 readOnly만 할 수 있는 author변수가 생성되는거같다.

def b = new Book()

assert "Stephen King" == b.author
=============================================================

// String author 를 안해줘도 어떻게 생성이 된것처럼 보인다 . . 외우기 

class Book {
  String title
}

def properties = Collections.synchronizedMap([:])

Book.metaClass.setAuthor = { String value -> 
   properties[System.identityHashCode(delegate) + "author"] = value
}        
// author 변수에 값을 넣을때 중복을 피하기 위해 해시코드 사용 

Book.metaClass.getAuthor = {->
   properties[System.identityHashCode(delegate) + "author"]
}

def b = new Book()

b.author = "kikiki"

println ( b.getAuthor() )