package fr.xebia.xke.fp


sealed trait List[+A]{
	def tail:List[A] = drop(1)

	def drop(n: Int): List[A]

	def init: List[A] = this match{
		case Cons(a,Nil) => Nil
		case Cons(a,b)   => Cons(a,b.init)
		case Nil => this
	}

	def foldRight[B](z: B)(f: (A, B) => B): B = this match{
		case Nil => z
		case Cons(x,xs) => f(x,xs.foldRight(z)(f))
	}

	def length: Int = foldRight(0)((a,b) => b + 1)
}

object List{
	def sum(ints: List[Int]): Int = 
		ints.foldRight(0)((i,j) => i + j)

	def product(ints: List[Int]): Int = 
		ints.foldRight(1)((i,j) => i * j)
}

case class Cons[A](a: A, as:List[A]) extends List[A]{
	def drop(n:Int) = if (n < 1) {
		this
	} else as.drop(n-1)
}

case object Nil extends List[Nothing]{
	def drop(n:Int) = this
}