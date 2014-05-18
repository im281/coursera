package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println(countChange(4, List(1, 2)))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c > r) {
      0
    } else if (c == 0 || r == 0) {
      1
    } else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercise 2
   *
   * (if (zero? x) max (/ 1 x))
   */
  def balance(chars: List[Char]): Boolean = {

    def balance(opens: Integer, chars: List[Char]): Boolean = {
      if (chars.isEmpty) {
        opens == 0
      } else if (chars.head == '(') {
        balance(opens + 1, chars.tail)
      } else if (chars.head == ')') {
        if (opens > 0) {
          balance(opens - 1, chars.tail)
        } else {
          false
        }
      } else {
        balance(opens, chars.tail)
      }
    }

    balance(0, chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (money < 0 || coins.isEmpty) {
      0
    } else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}
