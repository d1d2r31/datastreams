class Book {
  String title
  
  def Book(title){
      println("å������"+title+"�Դϴ�.");
  }
}
Book.metaClass.ProxyTest << {-> new Book("Proxy?") }

def b = new Book("ss")
b.ProxyTest()