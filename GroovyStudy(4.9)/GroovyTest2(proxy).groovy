class Book {
  String title
  
  def Book(title){
      println("책제목은"+title+"입니다.");
  }
}
Book.metaClass.ProxyTest << {-> new Book("Proxy?") }

def b = new Book("ss")
b.ProxyTest()