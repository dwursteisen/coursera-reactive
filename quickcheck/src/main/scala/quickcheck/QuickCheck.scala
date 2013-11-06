package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min1") = forAll {
    a: Int =>
      val h = insert(a, empty)
      findMin(h) == a
  }

  property("min2") = forAll {
    (h: H) =>
      val m = if (isEmpty(h)) 0 else findMin(h)
      findMin(insert(m, h)) == m
  }

  property("min3") = forAll {
    (a: Int, b: Int) =>
      val h = insert(a, insert(b, empty))
      findMin(h) == Math.min(a, b)
  }

  property("empty") = forAll {
    (a: Int) =>
      val h = deleteMin(insert(a, empty))
      h == empty
  }

  property("order") = forAll {
    (h: H) =>
      def order(heap: H): Seq[A] = {
        if (isEmpty(heap)) {
          Seq()
        } else {
          val min = findMin(heap)
          min +: order(deleteMin(heap))
        }
      }
      def _isOrdered(seq: Seq[A], previous: Int): Boolean = seq match {
        case Nil => true
        case head :: tail => previous <= head && _isOrdered(tail, head)
      }
      def isOrdered(seq: Seq[A]): Boolean = seq match {
        case Nil => true
        case head::tail => _isOrdered(seq, head)
      }
      isOrdered(order(h)) == true
  }

  lazy val genHeap: Gen[H] = for {
    elt <- arbitrary[Int]
    heap <- oneOf(value(empty), genHeap)
  } yield insert(elt, heap)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
